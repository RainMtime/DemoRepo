package rainmtime.com.demorepo.test_code.asmr_test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import rainmtime.com.demorepo.utils.DisplayMetricsUtil;

/**
 * Created by chunyu on 2018/3/7 下午3:51.
 * Email: 746431278@qq.com
 */

public class ASMRFollowLightView extends View {

    private Paint mFirstPaint;

    private float xCenter = 0;
    private float yCenter = 0;

    private static final int RADIUS = DisplayMetricsUtil.dip2px(250);


    private Matrix mMatrix;

    private RadialGradient mShader;

    private PorterDuffXfermode mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

    public ASMRFollowLightView(Context context) {
        super(context);
        init(context);
    }

    public ASMRFollowLightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ASMRFollowLightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mFirstPaint = new Paint();
        mFirstPaint.setAntiAlias(true);
        mFirstPaint.setStyle(Paint.Style.FILL);
        mMatrix = new Matrix();
        mShader = new RadialGradient(0, 0, RADIUS, Color.TRANSPARENT, Color.argb(200, 0, 0, 0), Shader.TileMode.CLAMP);
        mFirstPaint.setShader(mShader);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mMatrix.reset();
        mMatrix.setTranslate(xCenter, yCenter);
        mShader.setLocalMatrix(mMatrix);
        canvas.drawRect(0, 0, getRight(), getBottom(), mFirstPaint);
    }

    public void moveFollowLight(float x, float y) {
        xCenter = x;
        yCenter = y;
        invalidate();
    }

}
