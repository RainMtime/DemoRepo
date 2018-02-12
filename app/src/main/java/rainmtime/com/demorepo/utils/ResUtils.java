package rainmtime.com.demorepo.utils;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

import rainmtime.com.demorepo.global.GlobalContext;

/**
 * Created by chunyu on 2018/2/9 下午3:38.
 * Email: 746431278@qq.com
 */

public final class ResUtils {

    /**
     * @param id 字符串资源id
     * @return 返回字符串资源
     */
    public static String getString(@StringRes int id) {
        return GlobalContext.get().getResources().getString(id);
    }


    /**
     * @param id 颜色资源id
     * @return 返回颜色
     */
    public static int getColor(@ColorRes int id) {
        return GlobalContext.get().getResources().getColor(id);
    }

    /**
     * @param id  字符串资源id
     * @param str 补充的字符串
     * @return 返回的字符串
     */
    public static String getString(@StringRes int id, String str) {
        return String.format(getString(id), str);
    }
}
