package rainmtime.com.demorepo.global;

import android.app.Application;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicReference;

import rainmtime.com.demorepo.utils.AssertUtils;


/**
 * Created by chunyu on 2018/2/9 下午3:56.
 * Email: 746431278@qq.com
 */

public class GlobalContext {


    private static final AtomicReference<GlobalContext> sInstance;


    static {
        sInstance = new AtomicReference<>();
    }

    private static final String TAG = "GlobalContext";

    private final Application mApplication;

    public GlobalContext(@NonNull Application application) {
        mApplication = application;
    }

    public static void set(GlobalContext globalContext) {
        AssertUtils.assertTrue(globalContext != null, "Invalid context");
        if (!sInstance.compareAndSet(null, globalContext)) {
            throw new IllegalStateException("GlobalContext can only be set once");
        }
    }

    public static GlobalContext get() {
        GlobalContext globalContext = sInstance.get();
        AssertUtils.assertTrue(globalContext != null, "GlobalContext not set");
        return globalContext;
    }

    public Resources getResources() {
        return mApplication.getResources();
    }

}
