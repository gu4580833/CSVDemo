package com.example.user.myapplication.helper1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.view.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements SlidingMenu.SlidingMenuAdapter{


    private ArrayList<String> list;
    private Context mContext;
    private SlidingMenu mOpenMenu;
    private SlidingMenu mScrollingMenu;

    public SlidingMenu getScrollingMenu() {
        return mScrollingMenu;
    }

    public void setScrollingMenu(SlidingMenu scrollingMenu) {
        mScrollingMenu = scrollingMenu;
    }

    public void holdOpenMenu(SlidingMenu slidingMenu) {

        mOpenMenu = slidingMenu;
    }

    public  void closeOpenMenu() {

        if (mOpenMenu != null && mOpenMenu.isOpen()) {

            mOpenMenu.closeMenu();
            mOpenMenu = null;
        }
    }

    public Boolean isOpen(){
       // Log.e("zzzz","isOpen");
        if (mOpenMenu != null && mOpenMenu.isOpen()) {
            Log.e("zzzz","isOpen");
            return false;
            //mOpenMenu = null;
        }
        return true;
    }
    private RecyclerView mRecyclerView;
    private ItemTouchHelper itemTouchHelper;

    public MyAdapter(ArrayList<String> data, Context context,RecyclerView mRecyclerView, ItemTouchHelper itemTouchHelper) {
        this.list = data;
        mContext = context;
        this.mRecyclerView=mRecyclerView;
        this.itemTouchHelper=itemTouchHelper;


    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("sad","ACTION_DOWN1111");


                        // return false;
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        // Log.e("sad","ACTION_HOVER_MOVE11111");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Log.e("sad","ACTION_MOVE11111");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("sad","ACTION_UP11111");
                        return false;
                }
                return false;
            }
        });
//        view. setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("zzzz","sdasdsadasd3333333333333");
//                closeOpenMenu();
//            }
//        });
         return viewHolder;
    }


    int p=0;
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        //holder.itemView.setTag(position);
       // holder.imageView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorTopItem));
        holder.menuText.setText(list.get(position));
        p=position;

        holder. ccc.setText(list.get(position));
        holder.slidingMenu.setSlidingMenuAdapter(this);
        holder.menuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zzzz","sdasdsadasd2222222");
                closeOpenMenu();
                if (mOnClickListener != null) {
                    mOnClickListener.onMenuClick(holder, "");
                }
            }
        });

       closeOpenMenu();
//        holder. slidingMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                closeOpenMenu();
//                Log.e("zzzz","sdasdsadasdvvvv");
//                if (mOnClickListener != null) {
//                    mOnClickListener.onContentClick(holder);
//                }
//            }
//        });
        holder.slidingMenuzzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("zzzz","sdasdsadasd5555:"+isOpen());
                    if (mOnClickListener != null&&isOpen()) {
                        mOnClickListener.onContentClick(holder);
                    }
                closeOpenMenu();
            }
        });
        holder.slidingMenu.setCustomOnClickListener(new SlidingMenu.CustomOnClickListener() {
            @Override
            public void onClick() {
                Log.e("zzzz","sdasdsadasd");
                closeOpenMenu();

                if (mOnClickListener != null) {
                    mOnClickListener.onContentClick(holder);
                }
            }
        });
    }



    public SlidingMenu.OnClickListener mOnClickListener;

    public void setOnClickListener(SlidingMenu.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView menuText,ccc,zzzz;
        ImageView imageView;
        RelativeLayout content;
        SlidingMenu slidingMenu;
        LinearLayout ccccc,slidingMenuzzz;



        @SuppressLint("ClickableViewAccessibility")
        MyViewHolder(View itemView) {
            super(itemView);
          //  zzzz= (TextView) itemView.findViewById(R.id.zzzz);
            ccc= (TextView) itemView.findViewById(R.id.ccc);
            ccccc=itemView.findViewById(R.id.ccccc);
            slidingMenuzzz=itemView.findViewById(R.id.slidingMenuzzz);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            content = (RelativeLayout) itemView.findViewById(R.id.content);
            menuText = (TextView) itemView.findViewById(R.id.menuText);
            slidingMenu = (SlidingMenu) itemView.findViewById(R.id.slidingMenu);
//            slidingMenuzzz.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
////                    if(event.getAction()==MotionEvent.ACTION_DOWN){
////                        closeOpenMenu();
////                        return true;
////                    }
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN:
//                            Log.e("sad","ACTION_DOWN333333");
//
//
//                            // return false;
//                            return false;
////                        case MotionEvent.ACTION_HOVER_MOVE:
////                           // Log.e("sad","ACTION_HOVER_MOVE11111");
////                            break;
////                        case MotionEvent.ACTION_MOVE:
////                           // Log.e("sad","ACTION_MOVE11111");
////                            break;
//                        case MotionEvent.ACTION_UP:
//                            Log.e("sad","ACTION_UP33333");
//                            return false;
//                    //return false;
//                }
//                    return true;
//            }});
//            zzzz.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    int action = event.getAction();
//                    switch (action) {
//                        case MotionEvent.ACTION_DOWN: {
//                            //helperCallback.startDrag(MyViewHolder.this);
//                            itemTouchHelper.startDrag(MyViewHolder.this);
//                            break;
//                        }
//                    }
//                    return false;
//                }
//            });
        }
    }
}
