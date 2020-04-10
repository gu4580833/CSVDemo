package com.example.user.myapplication;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.myapplication.dao.User;

import java.util.ArrayList;

public class CSVAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<User> list;
    private LayoutInflater mInflater;
    private ViewHolder viewHolder;

    public CSVAdapter(Context context,ArrayList<User> list){
        this.context=context;
        this.list=list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView=mInflater.inflate(R.layout.csv_item,null);
            viewHolder.textView1=convertView.findViewById(R.id.textview_1);
            viewHolder.textView2=convertView.findViewById(R.id.textview_2);
            viewHolder.textView3=convertView.findViewById(R.id.textview_3);
            convertView.setTag(viewHolder);
        }else {
                viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView1.setText(list.get(position).getName());
        viewHolder.textView2.setText(list.get(position).getAge());
        viewHolder.textView3.setText(list.get(position).getSex());

        return convertView;
    }


  public   class ViewHolder{
        public TextView textView1;
      public TextView textView2;
      public TextView textView3;
//        public void ss(){
//            TextView t1=new TextView(context);
//          LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams .weight=1;
//
//                    t1.setEllipsize(TextUtils.TruncateAt.END);
//            t1.setMaxLines();
//          t1.setLayoutParams();
//        }
    }
}
