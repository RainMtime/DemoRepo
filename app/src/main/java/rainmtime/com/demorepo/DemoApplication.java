package rainmtime.com.demorepo;

import android.app.Application;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.webkit.WebView;

import com.facebook.stetho.Stetho;
import com.tencent.droidthreadprofiler.ThreadCreationCallback;
import com.tencent.droidthreadprofiler.ThreadProfiler;

import org.litepal.LitePal;

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
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        LitePal.initialize(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
        //开启debug webview 的能力
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        //全局功能对象
        if (sInitialized.compareAndSet(false, true)) {
            GlobalContext.set(new GlobalContext(this));
        }

        //对线程监控
        ThreadProfiler.init(this, new ThreadCreationCallback() {
            @Override
            public void onThreadCreating(@Nullable Thread creationThread, long creationThreadTid, long childThreadTid, @Nullable String javaStack, @Nullable String nativeStack) {
                //  通过调用new Thread (或者native层) 创建线程时回调
                String msg = "createing thread childThreadTid:" + childThreadTid + "javaStack：" + javaStack + "nativeStack:" + nativeStack;
                Log.i("chunyu-test", msg);
            }

            @Override
            public void onThreadCreated(long currentThreadTid, @Nullable String currentThreadName, @Nullable String creationThreadName, @Nullable String javaStack, @Nullable String nativeStack) {
                Log.w("chunyu-test", "created thread:" + currentThreadTid);
            }

            @Override
            public void onThreadExiting(long currentThreadTid, @Nullable String currentThreadName, @Nullable String creationThreadName, @Nullable String javaStack, @Nullable String nativeStack) {
                Log.e("chunyu-test", "thread exited:" + currentThreadTid);
            }
        });
    }
}
