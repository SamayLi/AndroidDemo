package com.samay.customviewdemo.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by shaohua.li on 7/27/16.
 */
public class UpMarqueeTextViewScroll extends ViewGroup {

    private Scroller mScroller;
    private int topBorder;
    private int bottomBorder;
    private int mTouchSlop;

    private float mYDown;
    private float mYMove;
    private float mYLastMove;

    private int childheight;


    public UpMarqueeTextViewScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mTouchSlop = 100;
        ScrollerRunnanle runnanle = new ScrollerRunnanle();
        mHander.removeCallbacks(runnanle);
        mHander.postDelayed(runnanle, 1000);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                childView.layout(0, i * childView.getMeasuredHeight(), childView.getMeasuredWidth(), (i + 1) * childView.getMeasuredHeight());
            }
            topBorder = getChildAt(0).getTop();
            bottomBorder = getChildAt(getChildCount() - 1).getBottom();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            childheight = childView.getHeight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mYDown = ev.getRawY();
                mYLastMove = mYDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mYMove = ev.getRawY();
                float diff = Math.abs(mYMove - mYDown);
                mYLastMove = mYDown;
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mYMove = event.getRawY();
                Log.d("samay@@@@@@", "This is move:" + mYMove);
                int scrolledY = (int) (mYLastMove - mYMove);
                if (getScrollY() + scrolledY < topBorder) {
                    scrollTo(0, 0);
                    break;
                } else if (getScrollY() + getHeight() + scrolledY > bottomBorder) {
                    scrollTo(0, bottomBorder - getHeight());
                    return true;
                }
                scrollBy(0, scrolledY);
                mYLastMove = mYMove;
                break;
            case MotionEvent.ACTION_UP:
                int targetIndex = (getScrollY() + getHeight() / 2) / getHeight();
                int dy = targetIndex * getHeight() - getScrollY();
                mScroller.startScroll(0, getScrollY(), 0, dy);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }


    private class ScrollerRunnanle implements Runnable {

        @Override
        public void run() {
            Log.d("samay@@@", "ScrollerRunnanle");
            scrollView();
            invalidate();
            mHander.postDelayed(this, 1000);
        }
    }

    private Handler mHander = new Handler();


    private void scrollView() {
        if (getScrollY() < topBorder) {
//            mScroller.startScroll(0,getScrollY(),0,0,100);
            scrollTo(0, 0);
        } else if (getScrollY() > bottomBorder - getHeight()) {
//            mScroller.startScroll(0,getScrollY(),0,bottomBorder-getHeight(),100);
            scrollTo(0, bottomBorder - getHeight());
        } else if (getScrollY() == bottomBorder - getHeight()) {
            scrollTo(0, 0);
        } else {
//            if (getScrollY() + childheight > bottomBorder-getHeight()) {
            mScroller.startScroll(0, getScrollY(), 0, childheight, 1000);
//            } else {
            mScroller.startScroll(0, getScrollY(), 0, childheight, 1000);
//            }
            postInvalidate();
//            scrollBy(0, childheight);
        }
    }
}

