package rainmtime.com.demorepo.test_code.asmr_test.viewdraghelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rainmtime.com.demorepo.R;
import rainmtime.com.demorepo.test_code.asmr_test.view.ASMREarLayout;
import rainmtime.com.demorepo.utils.ThreadUtils;

/**
 * Created by chunyu on 2018/3/1 上午10:51.
 * Email: 746431278@qq.com
 */

public class ViewDragHelperActivity extends AppCompatActivity {

    ASMREarLayout mASMREarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdraghelper);
        mASMREarLayout = findViewById(R.id.asmr_ear_layout);
        ThreadUtils.postDelay(new Runnable() {
            @Override
            public void run() {
                mASMREarLayout.startMoveDefault();
            }
        }, 1000);
    }
}
