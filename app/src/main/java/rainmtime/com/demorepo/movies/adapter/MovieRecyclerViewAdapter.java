package rainmtime.com.demorepo.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;

import rainmtime.com.demorepo.R;
import rainmtime.com.demorepo.component.view.drag_recyclerview.ItemTouchHelperAdapter;
import rainmtime.com.demorepo.movies.data.Subject;
import rainmtime.com.demorepo.movies.viewholder.MovieViewHolder;

/**
 * Created by chunyu on 2018/2/9 下午5:26.
 * Email: 746431278@qq.com
 */

public class MovieRecyclerViewAdapter extends BaseRecyclerViewAdapter<Subject, MovieViewHolder> implements ItemTouchHelperAdapter {


    public MovieRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false), getContext());
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Subject subject = getItemData(position);
        holder.renderView(subject);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPositon) {
        Collections.swap(mDatas,fromPosition,toPositon);
        notifyItemMoved(fromPosition,toPositon);
        return true;
    }
}
