package rainmtime.com.demorepo.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chunyu on 2019/1/11 5:24 PM.
 * Email: 746431278@qq.com
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface BindingView {
    /**
     * view资源id
     */
    @LayoutRes int[] value();

}
