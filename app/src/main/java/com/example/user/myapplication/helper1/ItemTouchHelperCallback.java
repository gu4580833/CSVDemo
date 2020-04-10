package com.example.user.myapplication.helper1;

import android.support.v7.widget.RecyclerView;

public interface ItemTouchHelperCallback{
    void onItemDelete(int positon);
    boolean onMove(int fromPosition,int toPosition);
    void  onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState);
}
