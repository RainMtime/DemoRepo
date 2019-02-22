package rainmtime.com.demorepo.test_code.inentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by chunyu on 2019/1/11 10:18 AM.
 * Email: 746431278@qq.com
 */
public class TestIntentService extends IntentService {

    public TestIntentService() {
        super("TestIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
         //todosomething

         //广播，EventCenter 告知外界
    }

}
