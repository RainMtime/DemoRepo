package rainmtime.com.demorepo.test_code.okhttp.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 雨时光 on 2019-06-04 13:46
 * Email: 746431278@qq.com
 */
public class Person {
    private int age;
    private String name;
    @SerializedName("ppd")
    private String ppd_name;

    @Override
    public String toString() {
        return "age:" + age + "\t name:" + name + "ppd:"+ppd_name;
    }
}
