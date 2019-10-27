package rainmtime.com.demorepo.utils;

import android.os.SystemClock;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
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

    public final static String TAG = "chunyu-test";

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
            Object dynamicImpl = Proxy.newProxyInstance(originalImpl.getClass().getClassLoader(), originalImpl.getClass().getInterfaces(),
            new ProxyImpl(originalImpl));

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

    public static class ProxyImpl implements InvocationHandler {

        private Object target;

        public ProxyImpl(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                //Note：此函数实现中万万不要有任何发起网络操作，要不然死循环！
                try {
                        // 校验函数名
                        long now = SystemClock.currentThreadTimeMillis();
                        if (method.getName().equals("lookupAllHostAddr")) {
                            //校验参数和返回值类型
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
                            //如果返回值类型符合预期，函数参数符合预期，参数个数符合预期，证明此反射对应的源码，还可以信赖。则进行相关逻辑hook。
                            //否则交由原来的逻辑处理（因为此时已经函数已经修改过了）。
                            if (isReturnValid && isArg1Valid && isArg2Valid && argSize == 2) {
                                InetAddress[] result = (InetAddress[]) method.invoke(target, args);
                                List<InetAddress> arrayList = new ArrayList<>(Arrays.asList(result));
                                String hostName = (String) args[0];
                                int length = arrayList.size();
                                Log.i(TAG,"dnsHookerCost:"+ (SystemClock.currentThreadTimeMillis() -now) +"\t hostName:"+hostName);
                                return arrayList.toArray(new InetAddress[length]);
                            }
                        }


                } catch (InvocationTargetException e) {
                    //动态代理机制，会包装（method.invoke内部抛出的excetion）相关Exception类型成InvocationTargetException 类型。(该exception 应该把内部的exception 及时抛出，逻辑才等价)
                    Throwable throwable = e.getCause();
                    if (throwable!=null){
                        Log.e(TAG, "invocationException0:" + throwable.getMessage());
                        throw throwable;
                    }
                } catch (Throwable e) {
                    String errorContent = "hook dns logic Fail:" + e.getMessage();
                    Log.e(TAG, errorContent);
                }
                return method.invoke(target, args);
            } catch (InvocationTargetException e) {
                //动态代理机制，会包装（method.invoke内部抛出的excetion）相关Exception类型成InvocationTargetException 类型。(该exception 应该把内部的exception 及时抛出，逻辑才等价)
                Throwable throwable = e.getCause();
                Log.e(TAG, "invocationException1:" + (throwable != null ? throwable.getMessage() : "null"));
                throw throwable;
            }
        }
    }

}
