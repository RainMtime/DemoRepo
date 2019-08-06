package rainmtime.com.demorepo.ndk;

/**
 * Created by 雨时光 on 2019-08-06 11:22
 */
public class NDKTools {

    static {
        System.loadLibrary("test-jni");
    }

    public static  native  String getStringFromNDK();
}


