package rainmtime.com.demorepo.movies.designpatterns;

/**
 * Created by chunyu on 2018/2/9 下午3:50.
 * Email: 746431278@qq.com
 * <p>
 * 单例模式模版类
 */

public abstract class Singleton<T, P> {

    private volatile T mInstance;

    public Singleton() {
    }

    protected abstract T create(P var1);


    public final T get(P p) {
        if (mInstance == null) {
            synchronized (this) {
                mInstance = create(p);
            }
        }
        return mInstance;
    }
}
