package rainmtime.com.demorepo.utils;

import rainmtime.com.demorepo.global.GlobalContext;

/**
 * Created by chunyu on 2018/3/5 下午7:07.
 * Email: 746431278@qq.com
 */

public final class DisplayMetricsUtil {
    private static float sDensity = 0f;// 根据GOOGLE IO文档中提到的，对density作缓存能起来省电的作用

    public static float getDensity() {
        if (sDensity == 0f) {
            sDensity = GlobalContext.get().getApplication().getResources().getDisplayMetrics().density;
        }
        return sDensity;
    }

    public static int dip2px(float dipValue) {
        return (int) (dipValue * getDensity() + 0.5f);
    }

}
