package rainmtime.com.demorepo.test_code.routertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chenenyu.router.annotation.Route;

/**
 * Created by 雨时光 on 2019-06-19 16:41
 */
@Route(value = "test", interceptors = "SampleInterceptor")
public class RouterTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
