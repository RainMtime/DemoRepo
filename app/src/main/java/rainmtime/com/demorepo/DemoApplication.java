package rainmtime.com.demorepo;

import android.app.Application;

import java.util.concurrent.atomic.AtomicBoolean;

import rainmtime.com.demorepo.global.GlobalContext;


/**
 * Created by chunyu on 2018/2/9 下午3:46.
 * Email: 746431278@qq.com
 */

public class DemoApplication extends Application {


    private static final AtomicBoolean sInitialized = new AtomicBoolean(false);

    @Override
    public void onCreate() {
        super.onCreate();

        //全局功能对象
        if (sInitialized.compareAndSet(false, true)) {
            GlobalContext.set(new GlobalContext(this));
        }

    }


}
