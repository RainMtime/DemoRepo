package rainmtime.com.demorepo.movies.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rainmtime.com.demorepo.R;

/**
 * Created by chunyu on 2018/2/9 下午2:58.
 * Email: 746431278@qq.com
 */

public class MovieFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_fragment, container, false);
    }
}
