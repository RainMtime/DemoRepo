package rainmtime.com.demorepo.component.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by chunyu on 2018/5/17 下午5:09.
 * Email: 746431278@qq.com
 */

public class LoadMoreRecyclerView extends RecyclerView {


    private boolean mIsLoading = false;

    private boolean mEnableLoadMore = false;

    private boolean mHasMoreData = true;

    private LoadMoreListener mLoadMoreListener;

    public LoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mHasMoreData && mEnableLoadMore) {
                    if (!canEnabledScrolled(1) && !mIsLoading) {
                        onLoadMore();
                    }
                }
            }
        });
    }


    /**
     * @param hasMoreData 当loadmore请求回来的时候，需要告诉一下LoadMoreRecylerView状态。
     */
    public void setLoadMoreComplete(boolean hasMoreData) {
        mIsLoading = false;
        mHasMoreData = hasMoreData;
    }

    /**
     * @param loadMoreListener 设置loadMoreListener
     */
    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    /**
     * @param direction 1 判断能否还能向上滑，从下面拉出更多东西。 -1 相反
     * @return true 代表还能够滑动，false 代表不能够滑动了。
     */
    private boolean canEnabledScrolled(int direction) {
        return canScrollVertically(direction);
    }


    private void onLoadMore() {
        mIsLoading = true;
        if (mLoadMoreListener != null) {
            mLoadMoreListener.onLoadMore();
        }
    }


    public interface LoadMoreListener {
        boolean onLoadMore();
    }
}
