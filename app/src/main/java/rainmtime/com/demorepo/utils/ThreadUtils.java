package rainmtime.com.demorepo.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by chunyu on 2018/2/11 下午4:11.
 * Email: 746431278@qq.com
 */

public class ThreadUtils {

    private static Thread sMainThread = Looper.getMainLooper().getThread();
    private static Handler sMainHandle = new Handler(Looper.getMainLooper());


    public static void post(Runnable runnable) {
        sMainHandle.post(runnable);
    }

    public static void postDelay(Runnable runnable, long delayMillis) {
        sMainHandle.postDelayed(runnable, delayMillis);
    }

    public static boolean isMainThread() {
        return sMainThread == Thread.currentThread();
    }

    public static void removeCallbacks(Runnable runnable) {
        sMainHandle.removeCallbacks(runnable);
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }
}
