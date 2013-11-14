package com.hilton.gitatouille;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.webkit.WebView;

public class CustomizedWebView extends WebView {
    private OnScrollListener mOnScrollListener;
    
    public CustomizedWebView(Context context) {
        super(context);
    }

    public CustomizedWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomizedWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void setScrollListener(OnScrollListener l) {
        mOnScrollListener = l;
    }
    
    private MotionEvent mStartingPoint;
    private boolean mScrollingDown;
    private boolean mScrollingUp;
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("font", "onTouchEvent " + event + ", starting point ");
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            mScrollingDown = false;
            mScrollingUp = false;
            mStartingPoint = event;
            Log.e("font", "on action down " + mStartingPoint);
            break;
        case MotionEvent.ACTION_MOVE:
            Log.e("font", "onTouchEvent, d y " + String.valueOf((event.getY(0) - mStartingPoint.getY(0))));
            Log.e("font", "on move mStartingEvent " + mStartingPoint);
            Log.e("font", "on move start y " + mStartingPoint.getY(0) + ", event y " + event.getY(0));
            if ((event.getY(0) - mStartingPoint.getY(0)) > 5.0) {
                if (!mScrollingDown) {
                    mScrollingDown = true;
                    mOnScrollListener.scrollDown();
                    mScrollingUp = false;
                }
            } else if ((event.getY(0) - mStartingPoint.getY(0)) < -5.0) {
                if (!mScrollingUp) {
                    mScrollingUp = true;
                    mScrollingDown = false;
                    mOnScrollListener.scrollUp();
                }
            }
            break;
        case MotionEvent.ACTION_UP:
            mScrollingDown = false;
            mScrollingUp = false;
            mStartingPoint = null;
            break;
        }
        final boolean b = super.onTouchEvent(event);
        return b;
    }
    
    private class ScrollGestureListener extends SimpleOnGestureListener {
        private boolean mScrollingDown;
        private boolean mScrollingUp;
        
        public ScrollGestureListener() {
            mScrollingUp = false;
            mScrollingDown = false;
        }
        
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY) {
            // TODO Auto-generated method stub
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                float distanceX, float distanceY) {
            Log.e("ll", "onScroll e1 " + e1 +", e2 " + e2 + ", distanceX " + distanceX + ", distanceY " + distanceY);
            if (e1 == null || e2 == null) {
                return false;
            }
            if (mOnScrollListener == null) {
                return false;
            }
            if ((e2.getY() - e1.getY()) > 1) {
                if (!mScrollingDown) {
                    mScrollingDown = true;
                    mScrollingUp = false;
                } else {
                    return false;
                }
            } else {
                if (!mScrollingUp) {
                    mScrollingUp = true;
                    mScrollingDown = false;
                } else {
                    return false;
                }
            }
            return true;
        }
    }
    
    public interface OnScrollListener {
        public void scrollDown();
        public void scrollUp();
    }
}