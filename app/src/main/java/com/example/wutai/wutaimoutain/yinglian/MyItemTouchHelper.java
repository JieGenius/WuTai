package com.example.wutai.wutaimoutain.yinglian;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class MyItemTouchHelper extends ItemTouchHelper.Callback {
    private OnItemTouchListener mListener;
    private boolean sort = false;

    public MyItemTouchHelper(OnItemTouchListener listener){
        this.mListener = listener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int swipeFlags = ItemTouchHelper.START;//代表左滑删除
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        //其实只有UP、DOWN即可完成排序，加上LEFT、RIGHT只是为了滑动更飘逸
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        return mListener.onMove(fromPosition, toPosition);
    }

    /**
     * 滑动删除
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    /**
     * 长按 拖拽
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return sort;//true 开启拖动排序，false关闭拖动排序
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mListener.onSwiped(viewHolder.getAdapterPosition());
    }
}
