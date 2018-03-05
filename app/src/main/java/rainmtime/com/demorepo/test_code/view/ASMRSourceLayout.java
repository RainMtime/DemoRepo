package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import rainmtime.com.demorepo.R;

/**
 * Created by chunyu on 2018/3/2 下午2:42.
 * Email: 746431278@qq.com
 */

public class ASMRSourceLayout extends FrameLayout {


    private static final String TAG = "ASMRSourceLayout";

    private float mScaleFactor = 1.0f;


    private float mTranlationX = 0;
    private float mTranlationY = 0;
    //音频光晕背景
    private ImageView mHalo;
    //音频圆形背景
    private ImageView mCircleBackground;
    //音频图标
    private ImageView mMusicIcon;


    private GestureDetector mGestureDetector;

    private ScaleGestureDetector mScaleGestureDetector;

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.asmr_music_source_layout, this, true);
        mHalo = rootView.findViewById(R.id.asmr_source_halo);
        mCircleBackground = rootView.findViewById(R.id.asmr_source_circle);
        mMusicIcon = rootView.findViewById(R.id.asmr_source_icon);


        mGestureDetector = new GestureDetector(context, new GestureDetectorListenerImpl());

        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleDetectorListenerImpl());
    }


    public int getCircleMargin() {
        final int width = getWidth();
        final int circleBackgroundWidth = mCircleBackground.getWidth();
        return (width - circleBackgroundWidth) / 2;
    }

    public int getBgCircleRadiu() {
        return mCircleBackground.getWidth() / 2;
    }


    private class GestureDetectorListenerImpl extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mTranlationX += distanceX;
            mTranlationY += distanceY;
//
//            setTranslationX(-mTranlationX);
//            setTranslationY(-mTranlationY);

            Log.i(TAG, "distanceX:" + distanceX + "distanceY:" + distanceY);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    private class ScaleDetectorListenerImpl extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            mScaleFactor *= detector.getScaleFactor();
            mCircleBackground.setScaleX(mScaleFactor);
            mCircleBackground.setScaleY(mScaleFactor);
            Log.i(TAG, "scaleFactor:" + detector.getScaleFactor());
            return true;
        }
    }


    public void setCircleBackgroundScale(float scale) {
        if (mCircleBackground != null) {
            mCircleBackground.setScaleY(scale);
            mCircleBackground.setScaleX(scale);
        }
    }


}
