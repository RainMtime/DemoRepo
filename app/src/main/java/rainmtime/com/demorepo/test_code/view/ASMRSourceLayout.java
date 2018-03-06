package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import rainmtime.com.demorepo.R;
import rainmtime.com.demorepo.utils.DisplayUtils;

/**
 * Created by chunyu on 2018/3/2 下午2:42.
 * Email: 746431278@qq.com
 */

public class ASMRSourceLayout extends FrameLayout {


    private static final String TAG = "ASMRSourceLayout";


    private static final int MIN_WIDTH = DisplayUtils.dip2px(80);

    private static final int MAX_WIDTH = DisplayUtils.dip2px(150);

    //音频光晕背景
    private ImageView mHalo;
    //音频圆形背景
    private ScaleDragView mCircleBackground;
    //音频图标
    private ImageView mMusicIcon;

    private ScaleDragView.ScaleListener mScaleListener;

    private float mCircleWidth = 0;
    private float mCircleHeight = 0;

    public ASMRSourceLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ASMRSourceLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ASMRSourceLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        final View rootView = LayoutInflater.from(context).inflate(R.layout.asmr_music_source_layout, this, true);
        mHalo = rootView.findViewById(R.id.asmr_source_halo);
        mCircleBackground = rootView.findViewById(R.id.asmr_source_circle);
        mMusicIcon = rootView.findViewById(R.id.asmr_source_icon);

        mCircleBackground.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("chunyu-onclick", "circleBackground:");
            }
        });

        mCircleBackground.setDragListener(new ScaleDragView.DragListener() {
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


        mCircleBackground.setScaleListener(new ScaleDragView.ScaleListener() {
            @Override
            public void onScale(float scaleFactor) {

                Log.i(TAG, "onScale:" + scaleFactor);

                ViewGroup.LayoutParams layoutParams = mCircleBackground.getLayoutParams();

                int toWidth = (int) (mCircleWidth * scaleFactor);

                int toHeight = (int) (mCircleHeight * scaleFactor);

                if (toWidth < MIN_WIDTH) {
                    toWidth = MIN_WIDTH;
                }
                if (toHeight < MIN_WIDTH) {
                    toHeight = MIN_WIDTH;
                }

                if (toWidth > MAX_WIDTH) {
                    toWidth = MAX_WIDTH;
                }

                if (toHeight > MAX_WIDTH) {
                    toHeight = MAX_WIDTH;
                }

                layoutParams.width = toWidth;
                layoutParams.height = toHeight;
                mCircleBackground.setLayoutParams(layoutParams);
            }

            @Override
            public void resetCurrentCircleSize() {
                mCircleWidth = mCircleBackground.getWidth();
                mCircleHeight = mCircleBackground.getHeight();
            }
        });


        mCircleBackground.setBringViewToFrontListener(new ScaleDragView.BringViewToFrontListener() {
            @Override
            public void bringViewToFront() {
                bringToFront();
            }
        });
    }

}
