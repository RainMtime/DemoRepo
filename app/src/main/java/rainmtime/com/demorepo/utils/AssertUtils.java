package rainmtime.com.demorepo.utils;

/**
 * Created by chunyu on 2018/2/9 下午4:24.
 * Email: 746431278@qq.com
 */

public class AssertUtils {

    public AssertUtils() {
    }

    public static void assertTrue(boolean cond) {
        if (!cond) {
            throw new AssertionError();
        }
    }

    public static void assertTrue(boolean cond, String msg) {
        if (!cond) {
            throw new AssertionError(msg);
        }
    }
}
