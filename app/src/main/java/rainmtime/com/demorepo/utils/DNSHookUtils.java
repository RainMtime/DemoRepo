package rainmtime.com.demorepo.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by 雨时光 on 2019-08-25 14:10
 */
public class DNSHookUtils {

    private DNSHookUtils() {

    }

    private AtomicBoolean hasInit = new AtomicBoolean(false);

    private static DNSHookUtils sInstance;


    public static DNSHookUtils getInstance() {

        if (sInstance == null) {
            synchronized (DNSHookUtils.class) {
                if (sInstance == null) {
                    sInstance = new DNSHookUtils();
                }
            }
        }
        return sInstance;
    }

    public void init() {
        if (hasInit.compareAndSet(false, true)) {
            doDnsHook();
        }
    }

    private void doDnsHook() {

        try {
            //获取InetAddress中的impl
            Field impl = InetAddress.class.getDeclaredField("impl");
            impl.setAccessible(true);
            //获取accessFlags
            Field modiersField = Field.class.getDeclaredField("accessFlags");
            modiersField.setAccessible(true);
            //去final 操作
            modiersField.setInt(impl, impl.getModifiers() & ~java.lang.reflect.Modifier.FINAL);
            //获取原始InetAddressImpl对象
            final Object originalImpl = impl.get(null);
            //构建动态代理InetAddressImpl 对象
            Object dynamicImpl = Proxy.newProxyInstance(originalImpl.getClass().getClassLoader(), originalImpl.getClass().getInterfaces(), new InvocationHandler() {

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Class<?> returnType = method.getReturnType();

                    Class<?>[] paramsTpye = method.getParameterTypes();

                    boolean isReturnValid = false;
                    boolean isArg1Valid = false;
                    boolean isArg2Valid = false;
                    int argSize = -1;
                    isReturnValid = returnType != null && "InetAddress[]".equals(returnType.getSimpleName());
                    if (paramsTpye != null) {
                        if (paramsTpye.length == 2) {
                            isArg1Valid = "String".equals(paramsTpye[0].getSimpleName());
                            isArg2Valid = "int".equals(paramsTpye[1].getSimpleName());
                            argSize = paramsTpye.length;
                        } else {
                            argSize = paramsTpye.length;
                        }
                    }

                    Log.w("chunyu-test", "argSize:" + argSize + "isReturnValid:" + isReturnValid + "\t isArg1Valid:" + isArg1Valid + "isArg2Valid:" + isArg2Valid);

                    if (method.getName().equals("lookupAllHostAddr") && isReturnValid && isArg1Valid && isArg2Valid && argSize == 2) {
                        Log.i("chunyu-test", "lookupAllHostAddr:" + args[0]);

                        InetAddress[] result = (InetAddress[]) method.invoke(originalImpl, args);

                        List<InetAddress> arrayList = new ArrayList<>(Arrays.asList(result));

                        Iterator<InetAddress> iterator = arrayList.iterator();
                        while (iterator.hasNext()) {
                            InetAddress item = iterator.next();

                            if (item != null && "180.101.49.12".equals(item.getHostAddress())) {
                                Log.i("chunyu-test", "删除地址成功：180.101.49.12");
                                iterator.remove();
                            }
                        }
                        int length = arrayList.size();

                        return arrayList.toArray(new InetAddress[length]);
                    }

                    return method.invoke(originalImpl, args);
                }
            });

            //偷梁换柱
            impl.set(null, dynamicImpl);
            // 还原final
            modiersField.setInt(impl, impl.getModifiers() & java.lang.reflect.Modifier.FINAL);
            Log.i("chunyu-test", "hook suc!");

        } catch (Exception e) {
            Log.e("chunyu-test", e.getMessage());
            e.printStackTrace();
        }

    }

}
