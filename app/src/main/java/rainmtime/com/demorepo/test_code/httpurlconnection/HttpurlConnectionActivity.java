package rainmtime.com.demorepo.test_code.httpurlconnection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import rainmtime.com.demorepo.R;

/**
 * Created by 雨时光 on 2019-07-19 11:39
 */
public class HttpurlConnectionActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection);
        button = findViewById(R.id.upload);
    }
}
