package rainmtime.com.demorepo.utils;

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
}
