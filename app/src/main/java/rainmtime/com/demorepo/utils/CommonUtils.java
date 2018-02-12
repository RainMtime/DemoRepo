package rainmtime.com.demorepo.utils;

import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;

import rainmtime.com.demorepo.movies.annotation.NotContainsNull;

/**
 * Created by chunyu on 2018/2/11 下午5:06.
 * Email: 746431278@qq.com
 */

public final class CommonUtils {


    /**
     * remove null item
     *
     * @param collection collection to remove null item
     */
    @NotContainsNull
    public static <T, V extends Collection<T>> V removeNullItems(@Nullable V collection) {
        if (collection == null) {
            return null;
        }

        final Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                it.remove();
            }
        }
        return collection;
    }


    public static boolean isCollectionEmpty(@Nullable Collection collection) {
        return collection == null ? false : collection.isEmpty();
    }

}
