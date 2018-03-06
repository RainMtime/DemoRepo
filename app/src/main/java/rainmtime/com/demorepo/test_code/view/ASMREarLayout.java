package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private static final int VELOCITY_MAX_LIMIT = 1000;

    private static final int VELOCITY_SCALE_TIMES = 200;

    private static ScaleDragView mUserCover;

    private ASMREarHandler mEarHandler;


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

        mEarHandler = new ASMREarHandler(this);

        mUserCover.setControlEarListener(new ScaleDragView.ControlEarListener() {
            @Override
            public void onControlEar(float[] xyVelocity) {
                float xVelocity = xyVelocity[0];
                float yVelocity = xyVelocity[1];

                Log.i("lalala", "xVelocity:" + xVelocity + "\t yVelocity:" + yVelocity);


                if (Math.abs(xVelocity) > VELOCITY_MIN_LIMIT && Math.abs(yVelocity) > VELOCITY_MIN_LIMIT) {
                    Message msg = Message.obtain();
                    msg.what = ASMREarHandler.MESSAGE_MOVE_EAR;
                    msg.obj = new float[]{xVelocity, yVelocity};
                    mEarHandler.removeMessages(ASMREarHandler.MESSAGE_MOVE_EAR);
                    mEarHandler.sendMessage(msg);
                } else {
                    mEarHandler.removeMessages(ASMREarHandler.MESSAGE_MOVE_EAR);
                }
            }
        });


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


                    int left = (int) (mEarLayout.getLeft() + xyVelocity[0] / VELOCITY_SCALE_TIMES);
                    int top = (int) (mEarLayout.getTop() + xyVelocity[1] / VELOCITY_SCALE_TIMES);

                    if (mEarLayout.isLeftOutSideFromParent()) {
                        left = 0;
                        xyVelocity[0] = -xyVelocity[0];
                    }

                    if (mEarLayout.isTopOutSideFromParent()) {
                        top = 0;
                        xyVelocity[1] = -xyVelocity[1];
                    }


                    if (mEarLayout.isBottomOutSideFromParent()) {
                        top = mEarLayout.getParentHeight() - mEarLayout.getHeight();
                        xyVelocity[1] = -xyVelocity[1];
                    }

                    if (mEarLayout.isRightOutSideFromParent()) {
                        left = mEarLayout.getParentWidth() - mEarLayout.getWidth();
                        xyVelocity[0] = -xyVelocity[0];
                    }


                    layoutParams.leftMargin = left;
                    layoutParams.topMargin = top;
                    mEarLayout.setLayoutParams(layoutParams);

                    Message message = Message.obtain();
                    message.what = MESSAGE_MOVE_EAR;
                    message.obj = xyVelocity;

                    sendMessageDelayed(message, 16);
                }

            }
        }
    }

    public boolean isLeftOutSideFromParent() {
        int left = getLeft();
        if (left < 0) {
            return true;
        }
        return false;
    }

    public boolean isRightOutSideFromParent() {
        int right = getRight();
        ViewGroup parentView = (ViewGroup) getParent();
        if (parentView != null) {
            int parentWidth = parentView.getWidth();
            if (right > parentWidth) {
                return true;
            }
        }
        return false;
    }

    public boolean isTopOutSideFromParent() {
        int top = getTop();
        if (top < 0) {
            return true;
        }
        return false;
    }

    public boolean isBottomOutSideFromParent() {
        int bottom = getBottom();
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

}
