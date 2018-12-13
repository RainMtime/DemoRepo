package rainmtime.com.demorepo.test_code.proxy_test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chunyu on 2018/12/13 3:37 PM.
 * Email: 746431278@qq.com
 */
public class DynamicProxy implements InvocationHandler {

    private Object mObject;

    public DynamicProxy(Object object) {
        mObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(mObject, args);
    }
}
