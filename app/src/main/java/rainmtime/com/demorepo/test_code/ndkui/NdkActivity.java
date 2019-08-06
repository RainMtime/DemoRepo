package rainmtime.com.demorepo.test_code.ndkui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rainmtime.com.demorepo.ndk.NDKTools;

/**
 * Created by 雨时光 on 2019-08-06 11:25
 */
public class NdkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String str = NDKTools.getStringFromNDK();
        Log.i("chunyu-test","str:"+str);
    }



}
