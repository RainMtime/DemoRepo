package rainmtime.com.demorepo.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rainmtime.com.demorepo.utils.CommonUtils;

/**
 * Created by chunyu on 2018/2/11 下午4:52.
 * Email: 746431278@qq.com
 */

public class BaseRecyclerViewAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private ArrayList<T> mDatas = new ArrayList<>();
    private Context mContext;

    public BaseRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    protected T getItemData(int index) {
        return mDatas.get(index);
    }

    public void setData(List<T> datas) {
        if (datas != null) {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyItemRangeChanged(0, mDatas.size());
        }
    }

    public void addData(List<T> datas) {
        if (!CommonUtils.isCollectionEmpty(datas)) {
            int startPos = mDatas.size();
            mDatas.addAll(datas);
            notifyItemRangeChanged(startPos, datas.size());
        }
    }

    public Context getContext() {
        return mContext;
    }
}
