package rainmtime.com.demorepo.component.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by chunyu on 2019/1/10 7:22 PM.
 * Email: 746431278@qq.com
 */
public class IconFontTextView extends android.support.v7.widget.AppCompatTextView {

    public IconFontTextView(Context context) {
        super(context);
        init(context);
    }

    public IconFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IconFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
     Typeface iconfont = Typeface.createFromAsset(context.getAssets(),"iconfont.ttf");
     setTypeface(iconfont);
    }
}
