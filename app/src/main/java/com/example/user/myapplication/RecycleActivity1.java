//package com.example.user.myapplication;
//
//import android.app.Activity;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.view.ViewCompat;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.example.user.myapplication.helper1.Myadapter1;
//import com.example.user.myapplication.helper1.Myadapter2;
//import com.example.user.myapplication.view.SwipeMenuRecyclerView;
//import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
//import com.yanzhenjie.recyclerview.swipe.SwipeItemLongClickListener;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
//import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
//import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
//import com.yanzhenjie.recyclerview.swipe.touch.OnItemStateChangedListener;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class RecycleActivity1 extends Activity implements SwipeItemClickListener{
//    ArrayList<String> strings;
//    protected SwipeMenuRecyclerView mRecyclerView;
//    protected RecyclerView.LayoutManager mLayoutManager;
//    protected RecyclerView.ItemDecoration mItemDecoration;
//    private Myadapter2 mAdapter;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycleview1);
//
//        strings=new ArrayList<>();å
//        for (int i=0;i<20;i++){
//            strings.add("测试"+i);
//        }
//        itntview();
//    }
//
//    private void itntview(){
//        mRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycleview);
//        mLayoutManager= new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator); // 菜单创建器。
//        mRecyclerView.setSwipeItemClickListener(this);
//        mRecyclerView.setSwipeItemLongClickListener(new SwipeItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View itemView, int position) {
//
//            }
//        });
//        mAdapter=new Myadapter2(this,strings);
//        mRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener); // Item的Menu点击。
//
//
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//
//        mRecyclerView.setOnItemMoveListener(onItemMoveListener);// 监听拖拽和侧滑删除，更新UI和数据源。
//        mRecyclerView.setOnItemStateChangedListener(mOnItemStateChangedListener); // 监听Item的手指状态，拖拽、侧滑、松开。
//        mRecyclerView.setLongPressDragEnabled(true); // 长按拖拽，默认关闭。
//        mRecyclerView.setItemViewSwipeEnabled(false); // 滑动删除，默认关闭。
//    }
//    private Handler mhandler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what){
//                case  0:
//                    mAdapter.notifyDataSetChanged();
//                    break;
//            }
//        }
//    };
//
//    /**
//     * 监听拖拽和侧滑删除，更新UI和数据源
//     **/
//
//    private OnItemMoveListener  onItemMoveListener=new OnItemMoveListener() {
//        @Override
//        public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
//            if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) return false;
//
//            // 真实的Position：通过ViewHolder拿到的position都需要减掉HeadView的数量。
//            int fromPosition = srcHolder.getAdapterPosition() - mRecyclerView.getHeaderItemCount();
//            int toPosition = targetHolder.getAdapterPosition() - mRecyclerView.getHeaderItemCount();
////            mDataList.set(fromPosition,"第" + toPosition + "个Item");
////            mDataList.set(toPosition,"第" + fromPosition + "个Item");
//            Collections.swap(strings, fromPosition, toPosition);
//            mAdapter.notifyItemMoved(fromPosition, toPosition);
//
//            return true;// 返回true表示处理了并可以换位置，返回false表示你没有处理并不能换位置。
//        }
//
//        @Override
//        public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
//            int adapterPosition = srcHolder.getAdapterPosition();
//            int position = adapterPosition - mRecyclerView.getHeaderItemCount();
//
////            if (mRecyclerView.getHeaderItemCount() > 0 && adapterPosition == 0) { // HeaderView。
////                mRecyclerView.removeHeaderView(mHeaderView);
////                Toast.makeText(RecycleActivity1.this, "HeaderView被删除。", Toast.LENGTH_SHORT).show();
////            } else { // 普通Item。
//                strings.remove(position);
//                mAdapter.notifyItemRemoved(position);
//                Toast.makeText(RecycleActivity1.this, "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
//           // }
//        }
//    };
//    /**
//     * Item的拖拽/侧滑删除时，手指状态发生变化监听。
//     */
//    private OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
//        @Override
//        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//            if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {
//                //mActionBar.setSubtitle("状态：拖拽");
//                // 拖拽的时候背景就透明了，这里我们可以添加一个特殊背景。
//               // sss=true;
//                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(RecycleActivity1.this, R.color.white_pressed));
//            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
//               // mActionBar.setSubtitle("状态：滑动删除");
//                Toast.makeText(RecycleActivity1.this, "list第" , Toast.LENGTH_SHORT).show();
//            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
//                //mActionBar.setSubtitle("状态：手指松开");
////                mAdapter.notifyDataSetChanged();
////                sss=false;
//                // 在手松开的时候还原背景。
//                mhandler.sendEmptyMessage(0);
//                ViewCompat.setBackground(viewHolder.itemView, ContextCompat.getDrawable(RecycleActivity1.this, R.drawable.select_white));
//            }
//        }
//    };
//
//    /**
//     * 菜单创建器。
//     */
//    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
//        @Override
//        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
//            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
//
//            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
//            // 2. 指定具体的高，比如80;
//            // 3. WRAP_CONTENT，自身高度，不推荐;
//            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//
//            // 添加右侧的，如果不添加，则右侧不会出现菜单。
//            {
//                SwipeMenuItem deleteItem = new SwipeMenuItem(RecycleActivity1.this)
//                        .setBackground(R.drawable.selector_red)
//                        .setImage(R.mipmap.ic_action_delete)
//                        .setText("Delete")
//                        .setTextColor(Color.WHITE)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
//            }
//        }
//    };
//
//    /**
//     * RecyclerView的Item的Menu点击监听。
//     */
//    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
//        @Override
//        public void onItemClick(SwipeMenuBridge menuBridge) {
//
//            menuBridge.closeMenu();
//            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
//            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
//            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
//
//            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
//
//                strings.remove(adapterPosition);
//
//                mAdapter.notifyItemRemoved(adapterPosition);
//               // mAdapter.notifyDataSetChanged();
//                Toast.makeText(RecycleActivity1.this, "现在的第" + adapterPosition + "条被删除。", Toast.LENGTH_SHORT).show();
//                mhandler.sendEmptyMessage(0);
//               // Toast.makeText(RecycleActivity1.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(RecycleActivity1.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    };
//
//
//    @Override
//    public void onItemClick(View itemView, int position) {
//        Toast.makeText(this, "第" + position + "个", Toast.LENGTH_SHORT).show();
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//        }
//
//        return true;
//    }
//       boolean sss=false;
//
//}
