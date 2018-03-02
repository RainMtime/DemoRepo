package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
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

    private ViewDragHelper mViewDragHelper = null;

    private ASMRSourceLayout mSource1;


    public ASMRLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public ASMRLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ASMRLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ASMRViewDragCallbackImp());
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        mViewDragHelper.processTouchEvent(event);
//        return true;
//    }
//
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
        mSource1 = findViewById(R.id.asmr_source1);
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

}
