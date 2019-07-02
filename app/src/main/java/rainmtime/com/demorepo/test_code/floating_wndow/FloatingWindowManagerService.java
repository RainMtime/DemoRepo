package rainmtime.com.demorepo.test_code.floating_wndow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import rainmtime.com.demorepo.R;

/**
 * Created by 雨时光 on 2019-06-21 16:34
 */
public class FloatingWindowManagerService extends Service {

    private FrameLayout mFloatingLayout;

    @Override
    public void onCreate() {
        super.onCreate();
        createFloatingWindow();
    }


    private void createFloatingWindow() {
        if (mFloatingLayout != null) {
            return;
        }
        Log.i("chunyu-test","createFloatingLayout");
        WindowManager windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        if (windowManager != null) {
            mFloatingLayout = (FrameLayout) LayoutInflater.from(getApplication()).inflate(R.layout.floating_layout, null);
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            params.format = PixelFormat.RGBA_8888;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            params.gravity = Gravity.START | Gravity.TOP;

            DisplayMetrics dm = new DisplayMetrics();

            windowManager.getDefaultDisplay().getMetrics(dm);

            int screenWidth = dm.widthPixels;
            int screenHeight = dm.heightPixels;

            params.x = 500;
            params.y = 300;

            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            mFloatingLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("chunyu-test", "click FloatingLayout");
                }
            });
            mFloatingLayout.setLayoutParams(params);
            windowManager.addView(mFloatingLayout, params);
        }

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (mFloatingLayout!=null){
            WindowManager windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
            windowManager.removeViewImmediate(mFloatingLayout);
            Log.i("chunyu-test","removeFloatingLayout");
        }
        super.onDestroy();
    }

}
