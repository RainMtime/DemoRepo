package rainmtime.com.demorepo.test_code;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rainmtime.com.demorepo.R;

/**
 * Created by chunyu on 2018/6/28 上午10:35.
 * Email: 746431278@qq.com
 */
public class TestActivity extends AppCompatActivity {

    @BindView(R.id.svg_test1)
    public ImageView mImageView1;

    @BindView(R.id.animate_svg)
    public ImageView mImageView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);
        //测试案例一： Resources$NotFoundException
//         testcase();
//        getResources().getDrawable(R.drawable.ic_check_circle);

//        //测试案例二：
//        showanim();
    }

//    private void showanim() {
//        ((AnimatedVectorDrawableCompat) mImageView2.getDrawable()).start();
//    }


//    private void testcase()  {
////        mImageView1.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle));
////        mImageView1.setImageResource(R.drawable.ic_check_circle);
//        Class cls = Person.class;
//    }

}
