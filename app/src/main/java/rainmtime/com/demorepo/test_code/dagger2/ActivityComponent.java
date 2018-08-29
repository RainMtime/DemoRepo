package rainmtime.com.demorepo.test_code.dagger2;

import dagger.Component;
import rainmtime.com.demorepo.MainActivity;

/**
 * Created by chunyu on 2018/8/29 下午4:12.
 * Email: 746431278@qq.com
 */

@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
