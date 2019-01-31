package rainmtime.com.demorepo.movies.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import rainmtime.com.demorepo.R;
import rainmtime.com.demorepo.component.view.drag_recyclerview.ItemTouchHelperCallback;
import rainmtime.com.demorepo.movies.adapter.MovieRecyclerViewAdapter;
import rainmtime.com.demorepo.movies.data.MoviesRsp;
import rainmtime.com.demorepo.movies.network.NetworkService;
import rainmtime.com.demorepo.utils.ThreadUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chunyu on 2018/2/9 下午2:58.
 * Email: 746431278@qq.com
 */

public class MovieFragment extends BaseFragment {

    private static final String TAG = "MovieFragment";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private MovieRecyclerViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.movie_fragment, container, false);
        ButterKnife.bind(this, rootView);

        ThreadUtils.postDelay(new Runnable() {
            @Override
            public void run() {
                requestData();
            }
        }, 2000);
        initViewAndAdapter();
        requestData();
        return rootView;
    }


    private void initViewAndAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new MovieRecyclerViewAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(ItemTouchHelperCallback.UP_DOWN,mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

    }


    private void requestData() {
        Call<MoviesRsp> call = NetworkService.getMovieNetApi().getTop250(0, 10);

        call.enqueue(new Callback<MoviesRsp>() {
            @Override
            public void onResponse(Call<MoviesRsp> call, Response<MoviesRsp> response) {
//                int resultCode = response.code();
//                MoviesRsp moviesRsp = response.body();
//
////                Log.i(TAG, "onResponse:" + resultCode
////                        + "\n" + moviesRsp.getTitle());
//
//                if (!CommonUtils.isCollectionEmpty(moviesRsp.getSubjects())) {
//                    mAdapter.setData(moviesRsp.getSubjects());
//                }
            }

            @Override
            public void onFailure(Call<MoviesRsp> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getMessage());
            }
        });
    }
}
