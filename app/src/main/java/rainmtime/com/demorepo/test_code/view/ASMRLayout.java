package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import rainmtime.com.demorepo.R;

/**
 * Created by chunyu on 2018/3/2 上午11:39.
 * Email: 746431278@qq.com
 */

public class ASMRLayout extends FrameLayout {

    private static final String TAG = "ASMRLayout";

    public ASMRLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ASMRLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ASMRLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final ASMRSourceLayout source1 = findViewById(R.id.asmr_source1);
        final ASMRSourceLayout source2 = findViewById(R.id.asmr_source2);
        final ASMRSourceLayout source3 = findViewById(R.id.asmr_source3);

        final ASMREarLayout earLayout = findViewById(R.id.asmr_ear_layout);

        earLayout.setLocationChangeListener(new ASMREarLayout.ASMREarLocationChangeListener() {
            @Override
            public void onChangeLocation(int[] centerXY) {

            }
        });

        earLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("chunyu-onclick", "onClick earLayout");
            }
        });
    }

}
