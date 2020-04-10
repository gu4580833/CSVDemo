package com.example.user.myapplication.helper1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.RecycleActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Myadapter1 extends RecyclerView.Adapter  {
  private ArrayList<String> list;
    private Context context;
    private ItemTouchHelperDelete helperDelete;
          public Myadapter1(Context context,ArrayList<String> list,ItemTouchHelperDelete helperDelete){
              this.context=context;
              this.list=list;
              this.helperDelete=helperDelete;
          }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_item, parent, false);
        return new recycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        recycleViewHolder viewHolder= (recycleViewHolder) holder;
        viewHolder.item_textView.setText( list.get(position));

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

//    @Override
//    public void onItemDelete(final int positon) {
//        AlertDialog.Builder warnDialog = new AlertDialog.Builder(context);
//            warnDialog.setMessage("是否删除").
//                    setPositiveButton("否",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //notifyAll();
//                            notifyDataSetChanged();
//                            dialog.dismiss();
//
//                        }
//                    }).setNegativeButton("是", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    helperDelete.delete(positon,list.get(positon));
//                    list.remove(positon);
//                    notifyItemRemoved(positon);
//                    dialog.dismiss();
//                }
//            });
//
//            warnDialog.create().show();
//
//    }



//    @Override
//    public void onMove(int fromPosition, int toPosition) {
//        Collections.swap(list,fromPosition,toPosition);//交换数据
//        notifyItemMoved(fromPosition,toPosition);
//    }


    public class recycleViewHolder extends RecyclerView.ViewHolder{
        TextView item_textView;
        public recycleViewHolder(View itemView) {
            super(itemView);
            item_textView = (TextView) itemView.findViewById(R.id.item_textView);
        }
    }


}
