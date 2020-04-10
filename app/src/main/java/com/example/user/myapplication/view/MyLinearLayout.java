package com.example.user.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
public class  MyLinearLayout extends LinearLayout {


    public MyLinearLayout(Context context) {
        super(context);
    }
    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyLinearLayout(Context context, AttributeSet attrs,
                          int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                Log.e("sad", "ACTION_DOWNongggg:" + super.onInterceptTouchEvent(ev));
                return super.onInterceptTouchEvent(ev);
            case MotionEvent.ACTION_UP:
                Log.e("sad", "ACTION_UPgggg:" + super.onInterceptTouchEvent(ev));
                Log.e("sad", "ACTION_UPggggssssss:" + islll);
                if(islll){
//                    setScrollingMenu(null);
////                       slidingMenuAdapter.closeOpenMenu();
//                    closeMenu();
                    Log.e("sad", "ACTION_UPggggssssss:" + super.onInterceptTouchEvent(ev));
                    return true;
                }
                return super.onInterceptTouchEvent(ev);
        }
        return super.onInterceptTouchEvent(ev);
    }



    boolean islll;
    public void setisopen( boolean islll){
        this.islll=islll;
    }
}