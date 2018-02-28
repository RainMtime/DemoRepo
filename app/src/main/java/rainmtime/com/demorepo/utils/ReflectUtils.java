package rainmtime.com.demorepo.utils;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by chunyu on 2018/2/26 下午2:38.
 * Email: 746431278@qq.com
 */

public class ReflectUtils {

    private static final String TAG = "ReflectUtils";

    public static void testFileld() {
        try {
            Class<?> claz = Class.forName("rainmtime.com.demorepo.orm.litepal.model.Album");
            Field[] fields = claz.getDeclaredFields();
            for (Field field : fields) {
                Log.i(TAG, "getname:" + field.getName()
                        + "\n getType:" + field.getType()
                        + "\n getnametype:" + field.getType().getName()
                        + "\n getGenericTypeName:" + field.getGenericType());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
