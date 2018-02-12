package rainmtime.com.demorepo.movies.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * Created by chunyu on 2018/2/11 下午5:08.
 * Email: 746431278@qq.com
 * </pre>
 * <p>
 * indicate not contains Null Object
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface NotContainsNull {

}
