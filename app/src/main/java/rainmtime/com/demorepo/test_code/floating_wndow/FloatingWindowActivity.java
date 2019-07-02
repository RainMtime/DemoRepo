package rainmtime.com.demorepo.test_code.floating_wndow;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import rainmtime.com.demorepo.R;

/**
 * Created by 雨时光 on 2019-06-21 14:59
 */
public class FloatingWindowActivity extends AppCompatActivity {


    FrameLayout  mFloatingLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
    }

    private void requestPermission(){
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)){
            Intent intent=new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            startActivityForResult(intent, 1);
        }else {
            Intent intent = new Intent(this,FloatingWindowManagerService.class);
            startService(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            Intent intent = new Intent(this,FloatingWindowManagerService.class);
            startService(intent);
        }
    }

}
