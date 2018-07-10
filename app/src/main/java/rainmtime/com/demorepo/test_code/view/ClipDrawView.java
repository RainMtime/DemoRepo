package rainmtime.com.demorepo.test_code.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import rainmtime.com.demorepo.utils.DisplayMetricsUtil;

/**
 * Created by chunyu on 2018/6/28 上午10:37.
 * Email: 746431278@qq.com
 */
public class ClipDrawView extends View {

    private Paint mPaint;

    private Path mPath;
    private float[] mRadiusArray = {DisplayMetricsUtil.dip2px(3), DisplayMetricsUtil.dip2px(3), DisplayMetricsUtil.dip2px(3), DisplayMetricsUtil.dip2px(3), DisplayMetricsUtil.dip2px(3),
            DisplayMetricsUtil.dip2px(3), DisplayMetricsUtil.dip2px(3), DisplayMetricsUtil.dip2px(3)};

    RectF mRect;

    public ClipDrawView(Context context) {
        super(context);
        init();
    }

    public ClipDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClipDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        mPath = new Path();
        mRect = new RectF(0, 0, DisplayMetricsUtil.dip2px(50), DisplayMetricsUtil.dip2px(50));
    }

    @Override
    protected void onDraw(Canvas canvas) {
       mPath.addCircle(30,30,20, Path.Direction.CW);
//       mPath.addRect(mRect, Path.Direction.CW);
       canvas.clipPath(mPath);
//        Log.i("chunyu-test:","clipPath:"+canvas.clipPath(mPath));

        canvas.drawCircle(30, 30, 20, mPaint);
       // canvas.drawColor(Color.BLACK);
    }
}
