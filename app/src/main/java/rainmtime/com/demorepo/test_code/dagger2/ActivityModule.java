package rainmtime.com.demorepo.test_code.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chunyu on 2018/8/29 下午4:11.
 * Email: 746431278@qq.com
 */
@Module
public class ActivityModule {

    @Provides UserModel provideUserModel(){
        return  new UserModel();
    }
}
