package rainmtime.com.demorepo.movies.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chunyu on 2018/2/11 下午3:12.
 * Email: 746431278@qq.com
 */

public class NetworkService {

    private static MovieNetApi sMovieNetApi;

    public static MovieNetApi getMovieNetApi() {

        if (sMovieNetApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.douban.com/v2/movie/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sMovieNetApi = retrofit.create(MovieNetApi.class);
        }
        return sMovieNetApi;
    }
}
