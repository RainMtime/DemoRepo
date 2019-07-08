package rainmtime.com.demorepo.test_code.NormalActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import rainmtime.com.demorepo.R;

/**
 * Created by 雨时光 on 2019-07-05 15:13
 */
public class NormalActivty  extends AppCompatActivity {

    private  HeaderDetailLayout mHeaderDetail;
    private ArrayList<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        mHeaderDetail = findViewById(R.id.header_detail);
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈,你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");

        mHeaderDetail.setData(datas);


        for (int i = 0;i<2500;i++){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }


}
