package rainmtime.com.demorepo.test_code.asmr_test.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

/**
 * Created by chunyu on 2018/3/1 上午10:55.
 * Email: 746431278@qq.com
 */

public class ScaleDragView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = "ScaleDragView";

    private static final float DISTANCE_RULE = 100;


    private float mBeginDistance = 0.0f;

    private float mCurrentDistance = 0.0f;

    private ScaleListener mScaleListener;

    private DragListener mDragListener;

    private BringViewToFrontListener mBringViewToFrontListener;

    private ControlEarListener mControlEarListener;

    private static final int VELOCITY_COLLECT_NUM = 3;


    private float[] xVelocityCollects = new float[VELOCITY_COLLECT_NUM];

    private float[] yVelocityCollects = new float[VELOCITY_COLLECT_NUM];


    private int mVelocityIndex = 0;

    private VelocityTracker mVelocityTracker;

    private float mLastX;
    private float mLastY;


    private static final int STATE_IDEL = 0;

    private static final int STATE_CAN_DRAG = 1;

    private static final int STATE_CAN_SCALE = 2;

    private static final int STATE_MULTI_TOUCH = 3;


    private int mState = STATE_IDEL;


    public ScaleDragView(Context context) {
        super(context);
        init(context);
    }

    public ScaleDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        //因为imageView 是不可点击的View ，设置成让其可以点击，这样才能够不漏掉event
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        //把记录的速度，给reset成0.0f
        resetVelocityCollection();
    }


    public void setScaleListener(ScaleListener scaleListener) {
        mScaleListener = scaleListener;
    }

    public void setDragListener(DragListener dragListener) {
        mDragListener = dragListener;
    }


    public void setBringViewToFrontListener(BringViewToFrontListener bringViewToFrontListener) {
        mBringViewToFrontListener = bringViewToFrontListener;
    }

    public void setControlEarListener(ControlEarListener controlEarListener) {
        mControlEarListener = controlEarListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        handleEvent(event);
        return super.dispatchTouchEvent(event);
    }

    private void handleEvent(MotionEvent event) {
        // 判断出当前有几个点
        int pointerCount = event.getPointerCount();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                if (pointerCount == 2) {
                    int[] beginXY = new int[2];
                    int[] endXY = new int[2];
                    getRawXY(event.getX(0), event.getY(0), beginXY);
                    getRawXY(event.getX(1), event.getY(1), endXY);
                    mBeginDistance = computeDistance(beginXY, endXY);
                    mState = STATE_CAN_SCALE;
                    if (mScaleListener != null) {
                        mScaleListener.resetCurrentCircleSize();
                    }
                } else {
                    mState = STATE_MULTI_TOUCH;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - mLastX;
                float dy = event.getRawY() - mLastY;
                if (mState == STATE_CAN_DRAG && mDragListener != null) {
                    mDragListener.onDrag(dx, dy);
                }
                mLastX = event.getRawX();
                mLastY = event.getRawY();
                //测速度
                if (pointerCount == 1 && mControlEarListener != null) {
                    //获得速度追踪对象
                    VelocityTracker velocity = getVelocityTracker();
                    velocity.addMovement(event);
                    //计算速度 并获取计算值
                    velocity.computeCurrentVelocity(1000); //设定一个时间间隔值
                    float xVelocity = velocity.getXVelocity();
                    float yVelocity = velocity.getYVelocity();
                    xVelocityCollects[mVelocityIndex] = xVelocity;
                    yVelocityCollects[mVelocityIndex] = yVelocity;
                    mVelocityIndex = (mVelocityIndex + 1) % VELOCITY_COLLECT_NUM;
                    Log.i("chunyu-veloc", "velocityX:" + xVelocity + "\t velocityY:" + yVelocity);
                }

                if (mState == STATE_CAN_SCALE) {
                    int[] beginXY = new int[2];
                    int[] endXY = new int[2];
                    getRawXY(event.getX(0), event.getY(0), beginXY);
                    getRawXY(event.getX(1), event.getY(1), endXY);
                    mCurrentDistance = computeDistance(beginXY, endXY);
                    if (mScaleListener != null) {
                        Log.i(TAG, "beginDistance:" + mBeginDistance + "\t currentDistance:" + mCurrentDistance);
                        mScaleListener.onScale(computeScaleFactor(mBeginDistance, mCurrentDistance));
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                mState = STATE_IDEL;
                mBeginDistance = 0;
                mCurrentDistance = 0;
                break;

            case MotionEvent.ACTION_DOWN:
                mLastX = event.getRawX();
                mLastY = event.getRawY();
                mState = STATE_CAN_DRAG;
                if (mBringViewToFrontListener != null) {
                    mBringViewToFrontListener.bringViewToFront();
                }
                break;

            case MotionEvent.ACTION_UP:
                mState = STATE_IDEL;
                mLastX = 0;
                mLastY = 0;
                if (mControlEarListener != null) {
                    float[] xyVelocity = new float[2];
                    getVelocity(xyVelocity);
                    mControlEarListener.onControlEar(xyVelocity);
                    resetVelocityCollection();
                }
                break;
        }
    }


    @Override
    protected void onDetachedFromWindow() {

        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
        super.onDetachedFromWindow();
    }

    private VelocityTracker getVelocityTracker() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        return mVelocityTracker;
    }

    private void resetVelocityCollection() {
        for (int i = 0; i < VELOCITY_COLLECT_NUM; i++) {
            xVelocityCollects[i] = 0.0f;
            yVelocityCollects[i] = 0.0f;
        }
    }

    private void getVelocity(float[] xyVelocity) {
        float xVelocity = 0.0f;
        float yVelocity = 0.0f;
        for (int i = 0; i < VELOCITY_COLLECT_NUM; i++) {
            xVelocity += xVelocityCollects[i];
            yVelocity += yVelocityCollects[i];
        }

        xyVelocity[0] = xVelocity / 3;
        xyVelocity[1] = yVelocity / 3;

        Log.i("chunyu-velocity", "xVelocity:" + xyVelocity[0] + "\t yVelocity:" + xyVelocity[1]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    private void getRawXY(float x, float y, int[] xy) {
        int[] screenxy = new int[2];
        getLocationOnScreen(screenxy);
        xy[0] = (int) (screenxy[0] + x);
        xy[1] = (int) (screenxy[1] + y);
    }


    private float computeDistance(int[] startXY, int[] endXY) {
        return (float) Math.sqrt(Math.pow(startXY[0] - endXY[0], 2) + Math.pow(startXY[1] - endXY[1], 2));
    }

    private float computeScaleFactor(float beginDistance, float currentDistance) {
        return 1.0f + ((currentDistance - beginDistance) / 2) / DISTANCE_RULE;
    }

    public interface BringViewToFrontListener {
        public void bringViewToFront();
    }

    public interface DragListener {
        public void onDrag(float dx, float dy);
    }

    public interface ScaleListener {
        public void onScale(float scaleFactor);

        public void resetCurrentCircleSize();
    }


    public interface ControlEarListener {
        public void onControlEar(float[] xyVelocity);
    }


}
