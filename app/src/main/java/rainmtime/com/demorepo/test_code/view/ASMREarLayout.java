package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import rainmtime.com.demorepo.R;

/**
 * Created by chunyu on 2018/3/5 下午8:20.
 * Email: 746431278@qq.com
 */

public class ASMREarLayout extends FrameLayout {


    private static final String TAG = "ASMREarLayout";

    private static ScaleDragView mUserCover;


    public ASMREarLayout(Context context) {
        super(context);

        init(context);
    }

    public ASMREarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.asmr_ear_layout, this, true);
        mUserCover = findViewById(R.id.asmr_ear);

        mUserCover.setDragListener(new ScaleDragView.DragListener() {
            @Override
            public void onDrag(float dx, float dy) {
                //拖拽改变当前View的位置（当前view是一个整体，包含光晕，可缩放环，音频图标）
                int left = (int) (getLeft() + dx);
                int top = (int) (getTop() + dy);

                int width = getWidth();
                int height = getHeight();

                FrameLayout.LayoutParams layoutParams = (LayoutParams) getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = height;
                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                setLayoutParams(layoutParams);
            }
        });
    }

}
