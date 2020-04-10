package com.example.user.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.myapplication.helper1.ItemTouchHelperCallback;
import com.example.user.myapplication.helper1.ItemTouchHelperDelete;
import com.example.user.myapplication.helper1.MyAdapter;
import com.example.user.myapplication.helper1.Myadapter1;
import com.example.user.myapplication.helper1.RecycleItemTouchHelper1;
import com.example.user.myapplication.view.SlidingMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecycleActivity extends Activity implements ItemTouchHelperDelete {
     @BindView(R.id.recycleview)
    RecyclerView recycleview;
     @BindView(R.id.btn_list)
    Button listbnt;
     private Myadapter1 myadapter1;
    ArrayList<String> strings;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        ButterKnife.bind(this);
        Utils.activity =this;
        recycleview.setLayoutManager(new LinearLayoutManager(this));
         strings=new ArrayList<>();
        for (int i=0;i<30;i++){
            strings.add("测试"+i);
        }
        Log.e("GU","11111111strings:"+strings.toString());
//        myadapter1=new Myadapter1(this,strings,this);
//        //recycleview.set
//        recycleview.setAdapter(myadapter1);

        SlidingMenu.RecycleItemTouchHelper callback=new SlidingMenu.RecycleItemTouchHelper();
        callback.setItemViewSwipeEnabled(false);

        callback.setLongPressDragEnabled(false);

        callback.setHelperCallback(new SlidingMenu.ItemTouchHelperCallback() {
            @Override
            public void onItemDelete(int positon) {

            }

            @Override
            public boolean onMove(int fromPosition, int toPosition) {
                if(fromPosition==toPosition){
                    return false;
                }
                Toast.makeText(RecycleActivity.this,"fromPosition:"+fromPosition+",toPosition:"+toPosition,Toast.LENGTH_LONG).show();
                Collections.swap(strings,fromPosition,toPosition);
                myAdapter.notifyItemMoved(fromPosition,toPosition);
                return true;
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if(actionState==ItemTouchHelper.ACTION_STATE_IDLE){//闲置中
                    listbnt.setText("闲置");
                }else if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){//滑动中
                    listbnt.setText("滑动");
                }else if(actionState==ItemTouchHelper.ACTION_STATE_DRAG){//拖拽中
                    listbnt.setText("拖拽");
                    myAdapter.closeOpenMenu();
                }
            }
        });
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        // itemTouchHelper.startDrag();
        itemTouchHelper.attachToRecyclerView(recycleview);

       myAdapter = new MyAdapter(strings, this,recycleview,itemTouchHelper);
       // myAdapter.setScrollingMenu(new SlidingMenu(this,null));
        myAdapter.setOnClickListener(new SlidingMenu.OnClickListener() {
            @Override
            public void onMenuClick(RecyclerView.ViewHolder viewHolder, String top) {
                Toast.makeText(RecycleActivity.this,"positon:"+viewHolder.getAdapterPosition()+",,list:"+top,Toast.LENGTH_LONG).show();
                strings.remove(viewHolder.getAdapterPosition());
                myAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }

            @Override
            public void onContentClick(RecyclerView.ViewHolder viewHolder) {
                Toast.makeText(RecycleActivity.this, "click pos = " + viewHolder.getAdapterPosition()+",strings:"+strings.get(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }


        });

        recycleview.setAdapter(myAdapter);
        recycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                myAdapter.setScrollingMenu(null);
            }
        });
        recycleview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_MOVE: {
                        myAdapter.closeOpenMenu();
                    }
                    break;
                }

                return false;
            }
        });
    }
       @OnClick(R.id.btn_list)
    public void onClick(){
//        for(int i=0;i<strings.size();i++){
//            Log.e("GU","strings:"+i+",,"+strings.get(i));
//        }
           strings.clear();
           for (int i=0;i<20;i++){
               strings.add("测试"+i);
           }
           myAdapter.notifyDataSetChanged();
           Toast.makeText(RecycleActivity.this,strings.size()+"",Toast.LENGTH_LONG).show();
       }

    @Override
    public void delete(int positon, String s) {
        Toast.makeText(RecycleActivity.this,"positon:"+positon+",,list:"+s,Toast.LENGTH_LONG).show();

    }
//
//    @Override
//    public void onItemDelete(int positon) {
//
//    }

//    @Override
//    public boolean onMove(int fromPosition, int toPosition) {
//        if(fromPosition==toPosition){
//            return false;
//        }
//        Toast.makeText(RecycleActivity.this,"fromPosition:"+fromPosition+",toPosition:"+toPosition,Toast.LENGTH_LONG).show();
//        Collections.swap(strings,fromPosition,toPosition);
//        myAdapter.notifyItemMoved(fromPosition,toPosition);
//        return true;
//    }
//
//    @Override
//    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//
//
//    }

//    @Override
//    public void startDrag(RecyclerView.ViewHolder viewHolder) {
//
//    }
}
