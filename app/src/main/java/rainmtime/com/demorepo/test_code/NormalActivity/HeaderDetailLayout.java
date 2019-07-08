package rainmtime.com.demorepo.test_code.NormalActivity;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 雨时光 on 2019-07-05 15:40
 */
public class HeaderDetailLayout extends ScrollView {

    private LinearLayout mContainer;
    private Context mContext;

    public HeaderDetailLayout(Context context) {
        super(context);
        init(context);
    }

    public HeaderDetailLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeaderDetailLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mContainer = new LinearLayout(context);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        ScrollView.LayoutParams layoutParams = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContainer.setLayoutParams(layoutParams);
        addView(mContainer);
    }


    public void setData(ArrayList<String> datas) {
        mContainer.removeAllViews();
        for (String item : datas) {
            if (!TextUtils.isEmpty(item)){
                TextView textView = new TextView(mContext);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,9);
                textView.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(layoutParams);
                textView.setText(item);
                mContainer.addView(textView);
            }
        }
    }
}
