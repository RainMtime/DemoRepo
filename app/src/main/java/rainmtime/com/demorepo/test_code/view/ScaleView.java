package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import javax.annotation.Nonnull;

/**
 * Created by chunyu on 2018/3/1 上午10:55.
 * Email: 746431278@qq.com
 */

public class ScaleView extends View {
    private static final String TAG = "ScaleView";

    private float mPosX1;
    private float mPosX2;
    private float mPosY2;
    private float mPosY1;

    private float mOriginDistance;

    private float mLastDistance;

    float mScaleFactor = 1.0f;

    public ScaleView(Context context) {
        super(context);
        init(context);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 判断出当前有几个点
        int pointerCount = event.getPointerCount();

        Log.i(TAG, "pointerCount:" + pointerCount);
        if (pointerCount == 2) {
            Log.i(TAG, "x1:" + event.getX(0) + "\tx2:" + event.getX(1));
        }
        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_POINTER_DOWN:
                if (pointerCount == 2) {
                    mLastDistance = mOriginDistance = computeDistance(event);
                    Log.i(TAG, "Action_Pointer_Down");
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 2) {
                    float distance = computeDistance(event);

                    Log.i(TAG, "distance:" + distance + "\t mLastDistance:" + mLastDistance);
                    if (Math.abs(mLastDistance - distance) > 1.0f) {
                        float scaleFactor = computeScaleFactor(distance);
                        setScaleX(scaleFactor);
                        setScaleY(scaleFactor);
                        mLastDistance = distance;
                        Log.i(TAG, "scaleFactor:" + scaleFactor);
                    }
                }

                break;
        }

        return true;
    }


    private float computeDistance(@Nonnull MotionEvent event) {
        float dx = event.getX(0) - event.getX(1);
        float dy = event.getY(0) - event.getY(1);
//        Log.i(TAG, "x1:" + event.getX(0) + "y1:" + event.getY(0));
//        Log.i(TAG, "x2:" + event.getX(1) + "y2:" + event.getY(1));
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    private float computeScaleFactor(float currentDistance) {

        float scaleFactor = 1.0f + (currentDistance - mOriginDistance) / mOriginDistance;

        if (scaleFactor < 1.0f) {
            scaleFactor = 1.0f;
        }

        if (scaleFactor > 2.0f) {
            scaleFactor = 2.0f;
        }

        return scaleFactor;
    }


}
