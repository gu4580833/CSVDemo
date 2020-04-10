package com.example.user.myapplication.helper1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;

public class Myadapter2 extends RecyclerView.Adapter{
  private ArrayList<String> list;
    private Context context;
          public Myadapter2(Context context, ArrayList<String> list){
              this.context=context;
              this.list=list;
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
        viewHolder.item_textView.setText( list.get(position)+"zzz"+position);

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }



    public class recycleViewHolder extends RecyclerView.ViewHolder{
        TextView item_textView;
        public recycleViewHolder(View itemView) {
            super(itemView);
            item_textView = (TextView) itemView.findViewById(R.id.item_textView);
        }
    }


}
