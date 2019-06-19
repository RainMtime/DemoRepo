package rainmtime.com.demorepo.test_code.routertest;

import android.support.annotation.NonNull;
import android.util.Log;

import com.chenenyu.router.RouteInterceptor;
import com.chenenyu.router.RouteResponse;

/**
 * Created by 雨时光 on 2019-06-19 16:44
 */
public class SimpleInterceptor implements RouteInterceptor {

    @NonNull
    @Override
    public RouteResponse intercept(Chain chain) {
        Log.i("chunyu-test","hahaha");
        return chain.process();
    }
}
