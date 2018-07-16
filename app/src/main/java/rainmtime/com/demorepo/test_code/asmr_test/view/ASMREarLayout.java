package rainmtime.com.demorepo.test_code.asmr_test.view;

import android.content.Context;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import javax.annotation.Nonnull;

import rainmtime.com.demorepo.R;

/**
 * Created by chunyu on 2018/3/5 下午8:20.
 * Email: 746431278@qq.com
 */

public class ASMREarLayout extends FrameLayout {


    private static final String TAG = "ASMREarLayout";

    private static final int VELOCITY_MIN_LIMIT = 30;

    private static final int VELOCITY_DEFAULT = 100;

    private static final int VELOCITY_MAX_LIMIT = 1000;

    private static final int VELOCITY_SCALE_TIMES = 200;

    private boolean mIsHasinitFakeRight = false;

    private boolean mIsMoving = false;

    /**
     * 这2个字段有其特殊意义，当ASMREarLayout可见的时候，mFakeLeft等于 getLeft()
     * 当ASMREarLayout 不可见的时候，mFakeLeft 不等于 getLeft()，
     * 自己维护一个mFakeLeft的原因主要在于，当按了home键，ASMR界面不可见的时候,
     * 系统不再绘制ASMREarLayout 本身，这样拿到的getLeft()和getTop()都是不会变的，
     * 这样的话就相当于耳朵的位置在退后台以后不会变，进而音源声音也不会变，这样是不符合需求预期的。
     */

    private int mFakeLeft = 0;

    private int mFakeTop = 0;

    private int mFakeBottom = 0;

    private int mFakeRight = 0;


    private static ScaleDragView mUserCover;

    private ASMREarLocationChangeListener mLocationChangeListener;

    private ASMREarHandler mEarHandler;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (!mIsHasinitFakeRight) {
            mFakeBottom = getBottom();
            mFakeLeft = getLeft();
            mFakeRight = getRight();
            mFakeTop = getTop();
            mIsHasinitFakeRight = true;
        }

        super.onSizeChanged(w, h, oldw, oldh);
    }

    public int getFakeLeft() {
        return mFakeLeft;
    }

    public void setFakeLeft(int fakeLeft) {
        mFakeLeft = fakeLeft;
    }

    public int getFakeTop() {
        return mFakeTop;
    }

    public void setFakeTop(int fakeTop) {
        mFakeTop = fakeTop;
    }


    public int getFakeBottom() {
        return mFakeBottom;
    }

    public void setFakeBottom(int fakeBottom) {
        mFakeBottom = fakeBottom;
    }

    public int getFakeRight() {
        return mFakeRight;
    }

    public void setFakeRight(int fakeRight) {
        mFakeRight = fakeRight;
    }

    public ASMREarLayout(Context context) {
        super(context);
        init(context);
    }

    public ASMREarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setLocationChangeListener(ASMREarLocationChangeListener locationChangeListener) {
        mLocationChangeListener = locationChangeListener;
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

                //记录当前View 对应的假位置（mFakeLeft,mFakeRight,mFakeBottom,mFakeTop)
                setFakeLeft(left);
                setFakeTop(top);
                setFakeRight(left + width);
                setFakeBottom(top + height);
            }
        });

        mEarHandler = new ASMREarHandler(this);

        mUserCover.setControlEarListener(new ScaleDragView.ControlEarListener() {
            @Override
            public void onControlEar(float[] xyVelocity) {
                dispatchMovingMessage(xyVelocity[0], xyVelocity[1]);
            }
        });
    }

    public void startMoveDefault() {
        if (!mIsMoving) {
            dispatchMovingMessage(VELOCITY_DEFAULT, VELOCITY_DEFAULT);
        }
    }

    private void dispatchMovingMessage(float xVelocity, float yVelocity) {
        setFakeLeft(getLeft());
        setFakeTop(getTop());
        setFakeBottom(getBottom());
        setFakeRight(getRight());

        if (Math.abs(xVelocity) > VELOCITY_MIN_LIMIT && Math.abs(yVelocity) > VELOCITY_MIN_LIMIT) {
            Message msg = Message.obtain();
            msg.what = ASMREarHandler.MESSAGE_MOVE_EAR;
            msg.obj = new float[]{xVelocity, yVelocity};
            mEarHandler.removeMessages(ASMREarHandler.MESSAGE_MOVE_EAR);
            mEarHandler.sendMessage(msg);
            mIsMoving = true;
        } else {
            mEarHandler.removeMessages(ASMREarHandler.MESSAGE_MOVE_EAR);
            mIsMoving = false;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        checkParentLayout();

    }

    private void checkParentLayout() {
        ViewParent viewParent = getParent();
        if (!(viewParent instanceof FrameLayout)) {
            throw new IllegalStateException("ASMREarLayout parent is not FrameLayout");
        }
    }

    public boolean isFakeLeftOutSideFromParent() {
        int left = getFakeLeft();
        if (left < 0) {
            return true;
        }
        return false;
    }

    public boolean isFakeRightOutSideFromParent() {
        int right = getFakeRight();
        ViewGroup parentView = (ViewGroup) getParent();
        if (parentView != null) {
            int parentWidth = parentView.getWidth();
            if (right > parentWidth) {
                return true;
            }
        }
        return false;
    }

    public boolean isFakeTopOutSideFromParent() {
        int top = getFakeTop();
        if (top < 0) {
            return true;
        }
        return false;
    }

    public boolean isFakeBottomOutSideFromParent() {
        int bottom = getFakeBottom();
        ViewGroup parentView = (ViewGroup) getParent();
        if (parentView != null) {
            int parentHeight = parentView.getHeight();
            if (bottom > parentHeight) {
                return true;
            }
        }
        return false;
    }

    public int getParentWidth() {
        ViewGroup parentView = (ViewGroup) getParent();

        if (parentView != null) {
            return parentView.getWidth();
        }
        return 0;

    }

    public int getParentHeight() {
        ViewGroup parentView = (ViewGroup) getParent();
        if (parentView != null) {
            return parentView.getHeight();
        }
        return 0;
    }

    public void onChangeLocation() {
        if (mLocationChangeListener != null) {
            int[] xy = new int[2];
            xy[0] += getFakeLeft() + getWidth() / 2;
            xy[1] += getFakeTop() + getHeight() / 2;
            mLocationChangeListener.onChangeLocation(xy);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mEarHandler.removeMessages(ASMREarHandler.MESSAGE_MOVE_EAR);

        super.onDetachedFromWindow();
    }

    public static class ASMREarHandler extends android.os.Handler {


        final ASMREarLayout mEarLayout;

        public ASMREarHandler(@Nonnull ASMREarLayout earLayout) {
            mEarLayout = earLayout;
        }

        public static final int MESSAGE_MOVE_EAR = 10086;

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_MOVE_EAR) {
                float[] xyVelocity = (float[]) msg.obj;

                FrameLayout.LayoutParams layoutParams = (LayoutParams) mEarLayout.getLayoutParams();
                if (layoutParams != null) {

                    int left = mEarLayout.getFakeLeft() + computeDelta(xyVelocity[0]);
                    int top = mEarLayout.getFakeTop() + computeDelta(xyVelocity[1]);


                    Log.i("chunyu-float", "x:" + computeDelta(xyVelocity[0]) + "y:" + computeDelta(-xyVelocity[1]));

                    if (mEarLayout.isFakeLeftOutSideFromParent()) {
                        left = 0;
                        xyVelocity[0] = -xyVelocity[0];
                    }

                    if (mEarLayout.isFakeTopOutSideFromParent()) {
                        top = 0;
                        xyVelocity[1] = -xyVelocity[1];
                    }


                    if (mEarLayout.isFakeBottomOutSideFromParent()) {
                        top = mEarLayout.getParentHeight() - mEarLayout.getHeight();
                        xyVelocity[1] = -xyVelocity[1];
                    }

                    if (mEarLayout.isFakeRightOutSideFromParent()) {
                        left = mEarLayout.getParentWidth() - mEarLayout.getWidth();
                        xyVelocity[0] = -xyVelocity[0];
                    }


                    layoutParams.leftMargin = left;
                    layoutParams.topMargin = top;
                    mEarLayout.setLayoutParams(layoutParams);

                    //关键，维护起来mEarLayout对应的位置,以便于在app退后台的时候，可以实时计算出耳朵的位置。
                    mEarLayout.setFakeLeft(left);
                    mEarLayout.setFakeTop(top);
                    mEarLayout.setFakeBottom(top + mEarLayout.getHeight());
                    mEarLayout.setFakeRight(left + mEarLayout.getWidth());

                    Log.i(TAG, "fakeLeft:" + left + "\t faketop:" + top);
                    mEarLayout.onChangeLocation();

                    Message message = Message.obtain();
                    message.what = MESSAGE_MOVE_EAR;
                    message.obj = xyVelocity;

                    sendMessageDelayed(message, 16);
                }

            }
        }

        private int computeDelta(float value) {
            if (value > 0.0f) {
                return (int) (Math.ceil(value / VELOCITY_SCALE_TIMES));
            } else {
                return (int) (Math.floor(value / VELOCITY_SCALE_TIMES));
            }
        }
    }


    public interface ASMREarLocationChangeListener {
        public void onChangeLocation(int[] centerXYFromParent);
    }

}
