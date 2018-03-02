package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import rainmtime.com.demorepo.R;

/**
 * Created by chunyu on 2018/3/2 上午11:39.
 * Email: 746431278@qq.com
 */

public class ASMRLayout extends FrameLayout {

    private static final String TAG = "ASMRLayout";

    ArrayList<ASMRSourceLayout> mMusicSources = new ArrayList<>();


    private GestureDetector mGestureDetector;

    private ScaleGestureDetector mScaleGestureDetector;

    private float mScaleFactor = 1.0f;
    private float mTranlationX = 0;
    private float mTranlationY = 0;


    private int mSelectIndex = -1;
    private ViewDragHelper mViewDragHelper = null;

    private ASMRSourceLayout mSource1;


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
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ASMRViewDragCallbackImp());

        mGestureDetector = new GestureDetector(context, new GestureDetectorListenerImpl());
        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleDetectorListenerImpl());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        mGestureDetector.onTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }


    private void handleEvent(@Nonnull MotionEvent event) {


    }

    //    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        final int action = ev.getActionMasked();
//
//        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//            mViewDragHelper.cancel();
//            return false;
//        }
//
//        return mViewDragHelper.shouldInterceptTouchEvent(ev);
//    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final ASMRSourceLayout source1 = findViewById(R.id.asmr_source1);
        mMusicSources.add(source1);

    }

    private class ASMRViewDragCallbackImp extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {

            if (child == mSource1) {
                return true;
            }
            return false;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {

            if (child == mSource1) {
                final int topBound = getPaddingTop() - mSource1.getCircleMargin();
                final int bottomBound = getHeight() - mSource1.getCircleMargin();
                Log.i(TAG, "top:" + top + "\t topBound:" + topBound + "\t bottomBound:" + bottomBound);
            }

            return top;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return super.clampViewPositionHorizontal(child, left, dx);
        }

    }

    private class GestureDetectorListenerImpl extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mTranlationX += distanceX;
            mTranlationY += distanceY;
//
//            setTranslationX(-mTranlationX);
//            setTranslationY(-mTranlationY);
            setSelectSourceTranslationX(-mTranlationX, -mTranlationY);


            Log.i(TAG, "distanceX:" + distanceX + "distanceY:" + distanceY);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    private class ScaleDetectorListenerImpl extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            mScaleFactor *= detector.getScaleFactor();
            mSource1.setCircleBackgroundScale(mScaleFactor);
            Log.i(TAG, "scaleFactor:" + detector.getScaleFactor());
            return true;
        }
    }

    private void setSelectSourceTranslationX(float translationX, float translationY) {
        if (mSelectIndex >= 0 && mSelectIndex < mMusicSources.size()) {
            mMusicSources.get(mSelectIndex).setTranslationX(translationX);
            mMusicSources.get(mSelectIndex).setTranslationY(translationY);
        } else {
            Log.e(TAG, "errorSelectIndex:" + mSelectIndex);
        }
    }

}