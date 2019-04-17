package rainmtime.com.demorepo.test_code.stickerbubbleanimtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import rainmtime.com.demorepo.R;
import rainmtime.com.demorepo.utils.ResUtils;

/**
 * Created by chunyu on 2019/4/16 7:19 PM.
 * Email: 746431278@qq.com
 */
public class CanvansView extends View {
    
    private Drawable mDrawable;
    
    
    Paint mPaint, otherPaint, outerPaint, mTextPaint;
    RectF mRectF;
    int mPadding;
    
    float arcLeft, arcTop, arcRight, arcBottom;
    
    Path mPath;
    
    public CanvansView(Context context) {
        super(context);
        init(context);
    }
    
    public CanvansView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    
    public CanvansView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    
    public void init(Context context) {
        mDrawable = ResUtils.getDrawable(R.drawable.ic_asmr_user);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        
        
        mTextPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(pxFromDp(context, 24));
        
        otherPaint = new Paint();
        
        outerPaint = new Paint();
        outerPaint.setStyle(Paint.Style.FILL);
        outerPaint.setColor(Color.YELLOW);
        
        mPadding = 100;
        
        
        DisplayMetrics displayMetrics = new DisplayMetrics();
        
        ((Activity) getContext()).getWindowManager()
                                 .getDefaultDisplay()
                                 .getMetrics(displayMetrics);
        
        
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        
        arcLeft = pxFromDp(context, 0);
        arcTop = pxFromDp(context, 0);
        arcRight = pxFromDp(context, 100);
        arcBottom = pxFromDp(context, 100);
        
        
        Point p1 = new Point((int) pxFromDp(context, 80) + (screenWidth / 2), (int) pxFromDp(context, 40));
        Point p2 = new Point((int) pxFromDp(context, 40) + (screenWidth / 2), (int) pxFromDp(context, 80));
        Point p3 = new Point((int) pxFromDp(context, 120) + (screenWidth / 2), (int) pxFromDp(context, 80));
        
        mPath = new Path();
        mPath.moveTo(p1.x, p1.y);
        mPath.lineTo(p2.x, p2.y);
        mPath.lineTo(p3.x, p3.y);
        mPath.close();
        
        mRectF = new RectF(screenWidth / 4, screenHeight / 3, screenWidth / 6, screenHeight / 2);
      
    }
    
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRoundRect(mRectF, 10, 10, otherPaint);
//        canvas.clipRect(mRectF, Region.Op.DIFFERENCE);
//        canvas.drawPaint(outerPaint);
////        
//        canvas.drawLine(250, 250, 400, 400, mPaint);
//        canvas.drawRect(mPadding, mPadding, getWidth() - mPadding, getHeight() - mPadding, mPaint);
//        canvas.drawArc(arcLeft, arcTop, arcRight, arcBottom, 0, 90, true, mPaint);
////        
//        otherPaint.setColor(Color.GREEN);
//        otherPaint.setStyle(Paint.Style.FILL);
////        
//        canvas.drawRect(
//                getLeft() + (getRight() - getLeft()) / 3,
//                getTop() + (getBottom() - getTop()) / 3,
//                getRight() - (getRight() - getLeft()) / 3,
//                getBottom() - (getBottom() - getTop()) / 3, otherPaint);
////        
////        
//        canvas.drawPath(mPath, mPaint);
//        otherPaint.setColor(Color.BLACK);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, arcLeft, otherPaint);
////        
//        canvas.drawText("Canvas basics", (float) (getWidth() * 0.3), (float) (getHeight() * 0.8), mTextPaint);
        canvas.drawColor(Color.BLACK);
        mDrawable.setBounds(100,100,300,300);
        mDrawable.draw(canvas);
        
        
    }
    
    
    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
