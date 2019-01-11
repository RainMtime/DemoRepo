package rainmtime.com.demorepo.global;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by chunyu on 2019/1/11 11:36 AM.
 * Email: 746431278@qq.com
 */
public class AppExecutors {

     private Executor mDiskIOExecutor;

     private Executor mNetWorkExecutor;


    public AppExecutors() {
      mDiskIOExecutor = Executors.newSingleThreadExecutor();
      mNetWorkExecutor = Executors.newFixedThreadPool(3);
    }

}
