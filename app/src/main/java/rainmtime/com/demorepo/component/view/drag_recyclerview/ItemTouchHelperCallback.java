package rainmtime.com.demorepo.component.view.drag_recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by chunyu on 2018/11/19 4:44 PM.
 * Email: 746431278@qq.com
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    //允许拖动的方向
    public static final int UP_DOWN = 1;
    public static final int LEFT_RIGHT = 2;
    public static final int UP_DOWN_LEFT_RIGHT = 3;

    private int mMovDir = 0;
    private ItemTouchHelperAdapter mItemTouchHelperAdapter;

    public ItemTouchHelperCallback(int mMov,@NonNull ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.mMovDir = mMov;
        this.mItemTouchHelperAdapter = itemTouchHelperAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        switch (mMovDir) {
            case UP_DOWN:
                return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.ACTION_STATE_IDLE);
            case LEFT_RIGHT:
                return makeMovementFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.ACTION_STATE_IDLE);
            case UP_DOWN_LEFT_RIGHT:
                return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.ACTION_STATE_IDLE);
            default:
                return makeMovementFlags(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.ACTION_STATE_IDLE);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return mItemTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
       //do nothing;
    }

}
