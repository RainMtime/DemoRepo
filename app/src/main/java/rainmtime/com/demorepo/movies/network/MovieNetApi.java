package rainmtime.com.demorepo.movies.network;

import rainmtime.com.demorepo.movies.data.MoviesRsp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chunyu on 2018/2/11 上午10:44.
 * Email: 746431278@qq.com
 */

public interface MovieNetApi {
    
    @GET("top250")
    Call<MoviesRsp> getTop250(@Query("start") int start, @Query("count") int count);
}
