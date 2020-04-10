package com.example.user.myapplication.helper1;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.zip.Inflater;

public class RecycleItemTouchHelper1 extends ItemTouchHelper.Callback {
    private static final String TAG="RecycleItemTouchHelper1";


//    public RecycleItemTouchHelper1(ItemTouchHelperCallback helperCallback) {
//        this.helperCallback = helperCallback;
//    }
    boolean isLongPressDragEnabled=false;
    public  void setLongPressDragEnabled(boolean isflag){
        this.isLongPressDragEnabled=isflag;
    }
    boolean isItemViewSwipeEnabled=false;
    public  void setItemViewSwipeEnabled(boolean isflag){
        this.isItemViewSwipeEnabled=isflag;
    }
    /**
     * 设置滑动类型标记
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     *          返回一个整数类型的标识，用于判断Item那种移动行为是允许的
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //Log.e(TAG, "getMovementFlags: " );
        //START  右向左 END左向右 LEFT  向左 RIGHT向右  UP向上
        return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.START |ItemTouchHelper.LEFT);
    }
    /**
     * Item是否支持长按拖动
     *
     * @return
     *          true  支持长按操作
     *          false 不支持长按操作
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return isLongPressDragEnabled;
    }
    /**
     * Item是否支持滑动
     *
     * @return
     *          true  支持滑动操作
     *          false 不支持滑动操作
     */

    @Override
    public boolean isItemViewSwipeEnabled() {
        return isItemViewSwipeEnabled;
    }
    /**
     * 拖拽切换Item的回调
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     *          如果Item切换了位置，返回true；反之，返回false
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //Log.e(TAG, "onMove: " );

        return helperCallback.onMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
    }

    /**
     * 滑动Item
     *
     * @param viewHolder
     * @param direction
     *           Item滑动的方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
       // Log.e(TAG, "onSwiped: ");
//        if(){
//
//        }
         helperCallback.onItemDelete(viewHolder.getAdapterPosition());
    }
    /**
     * Item被选中时候回调
     *
     * @param viewHolder
     * @param actionState
     *          当前Item的状态
     *          ItemTouchHelper.ACTION_STATE_IDLE   闲置状态
     *          ItemTouchHelper.ACTION_STATE_SWIPE  滑动中状态
     *          ItemTouchHelper#ACTION_STATE_DRAG   拖拽中状态
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //super.onSelectedChanged(viewHolder, actionState);
        helperCallback.onSelectedChanged(viewHolder,actionState);
    }
 public void setHelperCallback(ItemTouchHelperCallback helperCallback){
        this.helperCallback=helperCallback;
 }
    private  ItemTouchHelperCallback helperCallback;



}
