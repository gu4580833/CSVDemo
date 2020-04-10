package com.example.user.myapplication.view;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.user.myapplication.Utils;
import com.example.user.myapplication.helper1.MyAdapter;
import com.example.user.myapplication.helper1.Myadapter1;
//import com.example.user.myapplication.helper1.ItemTouchHelperCallback;
//import com.example.user.myapplication.helper1.MyAdapter;

public class SlidingMenu extends HorizontalScrollView {

    //菜单占屏幕宽度比
    private static final float radio = 0.2f;
    private final int mScreenWidth;
    private final int mMenuWidth;


    private boolean once = true;
    private boolean isOpen;
    int mAdapterPosition;
   // private MyLinearLayout mlinearLayout;

    private Context context;
    public SlidingMenu(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //getScreenWidth();
        this.context=context;
        mScreenWidth = getScreenWidth(context);
        mMenuWidth = //(int) (mScreenWidth * radio)
        dip2px(70);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
    }

    /**
     * 关闭菜单
     */

    public void closeMenu() {

        this.smoothScrollTo(0, 0);
        isOpen = false;
    }

    /**
     * 菜单是否打开
     */
    public boolean isOpen() {
        return isOpen;
    }



public  interface  SlidingMenuAdapter{
        void holdOpenMenu(SlidingMenu slidingMenu);
        void closeOpenMenu();
         SlidingMenu getScrollingMenu();
        void setScrollingMenu(SlidingMenu scrollingMenu);
    }
    private SlidingMenuAdapter slidingMenuAdapter;
    public  void setSlidingMenuAdapter(SlidingMenuAdapter slidingMenuAdapter) {
        this.slidingMenuAdapter = slidingMenuAdapter;
    }


//    /**
//     * 获取 adapter
//     */
//    private MyAdapter getAdapter() {
//        View view = this;
//        while (true) {
//            view = (View) view.getParent();
//            if (view instanceof RecyclerView) {
//                break;
//            }
//        }
//        return (MyAdapter) ((RecyclerView) view).getAdapter();
//    }

    /**
     * 当打开菜单时记录此 view ，方便下次关闭
     */
    private void onOpenMenu() {
        slidingMenuAdapter.holdOpenMenu(this);
        isOpen = true;
    }

    /**
     * 当触摸此 item 时，关闭上一次打开的 item
     */
    private void closeOpenMenu() {

        if (!isOpen) {
            slidingMenuAdapter.closeOpenMenu();
        }
    }
    private void closeOpen() {


            slidingMenuAdapter.closeOpenMenu();

    }

    /**
     * 获取正在滑动的 item
     */
    public SlidingMenu getScrollingMenu() {
        return  slidingMenuAdapter.getScrollingMenu();
    }

    /**
     * 设置本 item 为正在滑动 item
     */
    public void setScrollingMenu(SlidingMenu scrollingMenu) {
        slidingMenuAdapter.setScrollingMenu(scrollingMenu);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (once) {
            LinearLayout  mlinearLayout = (LinearLayout) getChildAt(0);
            mlinearLayout.getChildAt(0).getLayoutParams().width = mScreenWidth;
            mlinearLayout.getChildAt(1).getLayoutParams().width = mMenuWidth;
            once = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        Log.e("sad","OnFocusChangeListener:");
        super.setOnFocusChangeListener(l);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        Log.e("sad","onFocusChanged:"+gainFocus);
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (getScrollingMenu() != null && getScrollingMenu() != this) {
            Log.e("sad","ACTION_DOWNsdsdsdddd");
            return false;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("sad","ACTION_DOWN222222:"+isOpen());

                //return super.onTouchEvent(ev);
                downTime = System.currentTimeMillis();
                closeOpenMenu();
                setScrollingMenu(this);
//                return false;
//                downTime = System.currentTimeMillis();
////                if(isOpen){
////                    closeOpenMenu();
////                    setScrollingMenu(this);
////                    return true;
////                }else{
//                    closeOpenMenu();
//                    setScrollingMenu(this);
                    //return  true;
               // }
                break;
            case MotionEvent.ACTION_UP:
                Log.e("sad","ACTION_UP22222:"+isOpen());

                //return super.onTouchEvent(ev);
               // break;

                setScrollingMenu(null);

                int scrollX = getScrollX();

//                if (System.currentTimeMillis() - downTime <= 200 && scrollX == 0&&!isOpen()) {
//                    //if(){
//
//
//                    if (mCustomOnClickListener != null) {
//                        mCustomOnClickListener.onClick();
//                    }
//                    return false;
//                   // }
//                }
//                if(!isOpen()){
//                        scrollX=0;
//                    }
                if (Math.abs(scrollX) > mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                     onOpenMenu();
                } else {
                    this.smoothScrollTo(0, 0);
                    closeOpen();
                }


                return false;
        }

        return super.onTouchEvent(ev);
    }

    public int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    long downTime = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 当有正在滑动的item且不是自身则禁止滑动
        if (getScrollingMenu() != null && getScrollingMenu() != this) {
            return false;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                    closeOpenMenu();
                    setScrollingMenu(this);
//
             break;
            case MotionEvent.ACTION_UP:
               // mlinearLayout.setisopen(isOpen);
                Log.e("sad","ACTION_UP:"+super.dispatchTouchEvent(ev));
                //Log.e("sad","dispatchTouchEvent:"+super.dispatchTouchEvent(ev));

//
                setScrollingMenu(null);
                int scrollX = getScrollX();

//                if (System.currentTimeMillis() - downTime <= 100 && scrollX == 0) {
//                    if (mCustomOnClickListener != null) {
//                        mCustomOnClickListener.onClick();
//                    }
//                    return false;
//                }
                if (Math.abs(scrollX) > mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    onOpenMenu();
                } else {
                    this.smoothScrollTo(0, 0);
                    closeOpen();
                }


                return false;
        }
       // Log.e("sad","dispatchTouchEvent:"+super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }
//
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                Log.e("sad", "ACTION_DOWNonlll:" + super.onInterceptTouchEvent(ev));
//                return super.onInterceptTouchEvent(ev);
//            case MotionEvent.ACTION_UP:
//                Log.e("sad", "ACTION_UPlll:" + super.onInterceptTouchEvent(ev));
////                if(isOpen){
////                    setScrollingMenu(null);
//////                    slidingMenuAdapter.closeOpenMenu();
////                    this.smoothScrollTo(0, 0);
////                    closeMenu();
////                    return true;
////                }
//                return super.onInterceptTouchEvent(ev);
//        }
//        return super.onInterceptTouchEvent(ev);
//    }


    public interface CustomOnClickListener {
        void onClick();
    }

    public int getAdapterPosition() {
        return mAdapterPosition;
    }

    public CustomOnClickListener mCustomOnClickListener;

    public void setCustomOnClickListener(CustomOnClickListener listener) {
        this.mCustomOnClickListener = listener;
    }

//    public static int getScreenWidth(Context context) {
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(outMetrics);
//        return outMetrics.widthPixels;
//    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }


    public static class RecycleItemTouchHelper extends ItemTouchHelper.Callback {
        private static final String TAG = "RecycleItemTouchHelper";


        //    public RecycleItemTouchHelper1(ItemTouchHelperCallback helperCallback) {
//        this.helperCallback = helperCallback;
//    }
        boolean isLongPressDragEnabled = false;

        public void setLongPressDragEnabled(boolean isflag) {
            this.isLongPressDragEnabled = isflag;
        }

        boolean isItemViewSwipeEnabled = false;

        public void setItemViewSwipeEnabled(boolean isflag) {
            this.isItemViewSwipeEnabled = isflag;
        }

        /**
         * 设置滑动类型标记
         *
         * @param recyclerView
         * @param viewHolder
         * @return 返回一个整数类型的标识，用于判断Item那种移动行为是允许的
         */
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //Log.e(TAG, "getMovementFlags: " );
            //START  右向左 END左向右 LEFT  向左 RIGHT向右  UP向上
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.LEFT);
        }

        /**
         * Item是否支持长按拖动
         *
         * @return true  支持长按操作
         * false 不支持长按操作
         */
        @Override
        public boolean isLongPressDragEnabled() {
            return isLongPressDragEnabled;
        }

        /**
         * Item是否支持滑动
         *
         * @return true  支持滑动操作
         * false 不支持滑动操作
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
         * @return 如果Item切换了位置，返回true；反之，返回false
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //Log.e(TAG, "onMove: " );

            return helperCallback.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }

        /**
         * 滑动Item
         *
         * @param viewHolder
         * @param direction  Item滑动的方向
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
         * @param actionState 当前Item的状态
         *                    ItemTouchHelper.ACTION_STATE_IDLE   闲置状态
         *                    ItemTouchHelper.ACTION_STATE_SWIPE  滑动中状态
         *                    ItemTouchHelper#ACTION_STATE_DRAG   拖拽中状态
         */
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            //super.onSelectedChanged(viewHolder, actionState);
            helperCallback.onSelectedChanged(viewHolder, actionState);
        }

        public void setHelperCallback(ItemTouchHelperCallback helperCallback) {
            this.helperCallback = helperCallback;
        }

        private ItemTouchHelperCallback helperCallback;


    }
    public interface ItemTouchHelperCallback{
        void onItemDelete(int positon);
        boolean onMove(int fromPosition,int toPosition);
        void  onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState);
    }
    public interface OnClickListener {
        void onMenuClick(RecyclerView.ViewHolder viewHolder, String top);

        void onContentClick(RecyclerView.ViewHolder viewHolder);
    }


}
