package rainmtime.com.demorepo.nestedRecyclerView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rainmtime.com.demorepo.R;
import rainmtime.com.demorepo.movies.adapter.MovieRecyclerViewAdapter;
import rainmtime.com.demorepo.movies.data.Subject;

/**
 * Created by chunyu on 2018/4/28 下午2:55.
 * Email: 746431278@qq.com
 */

public class NestedActivity extends AppCompatActivity {
    @BindView(R.id.scroll_recyclerview)
    public RecyclerView mRecyclerView;

    private MovieRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nested);
        ButterKnife.bind(this);
        mAdapter = new MovieRecyclerViewAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);


        ArrayList<Subject> rowDatas = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Subject subject = new Subject();
            rowDatas.add(subject);
        }

        mAdapter.setData(rowDatas);
    }
}
