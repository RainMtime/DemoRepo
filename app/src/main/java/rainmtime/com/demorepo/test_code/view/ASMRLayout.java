package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;

import rainmtime.com.demorepo.R;

/**
 * Created by chunyu on 2018/3/2 上午11:39.
 * Email: 746431278@qq.com
 */

public class ASMRLayout extends FrameLayout {

    private static final String TAG = "ASMRLayout";

    private static final int ASMR_SOURCE_NUM = 5;

    private ArrayList<ASMRSourceLayout> mSourceArray = new ArrayList<>(ASMR_SOURCE_NUM);
    private ASMREarLayout mEarLayout;
    private ASMRFollowLightView mASMRFollowLightView;


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
        mSourceArray.add((ASMRSourceLayout) findViewById(R.id.asmr_source1));
        mSourceArray.add((ASMRSourceLayout) findViewById(R.id.asmr_source2));
        mSourceArray.add((ASMRSourceLayout) findViewById(R.id.asmr_source3));

        mEarLayout = findViewById(R.id.asmr_ear_layout);

        mASMRFollowLightView = findViewById(R.id.asmr_follow_light);

        mEarLayout.setLocationChangeListener(new ASMREarLayout.ASMREarLocationChangeListener() {
            @Override
            public void onChangeLocation(int[] centerXYFromParent) {

                int[] centerXYFromScreen = new int[2];

                ASMRLayout.this.getLocationOnScreen(centerXYFromScreen);

                centerXYFromScreen[0] += centerXYFromParent[0];
                centerXYFromScreen[1] += centerXYFromParent[1];
                Log.i(TAG, "centerX:" + centerXYFromScreen[0] + "centerY:" + centerXYFromScreen[1]);

                for (ASMRSourceLayout item : mSourceArray) {
                    if (item != null) {
                        item.onEarLocationChange(centerXYFromScreen);
                    }
                }

                //追光灯效果
                mASMRFollowLightView.moveFollowLight(centerXYFromParent[0], centerXYFromParent[1]);
            }
        });
    }


}
