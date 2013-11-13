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
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final boolean b = super.onTouchEvent(event);
        GestureDetector gd = new GestureDetector(getContext(), new ScrollGestureListener());
        gd.onTouchEvent(event);
        return b;
    }
    
    private class ScrollGestureListener extends SimpleOnGestureListener {
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
            if (mOnScrollListener == null) {
                return false;
            }
            if ((e1.getY() - e2.getY()) > 0) {
                mOnScrollListener.scrollDown();
            } else {
                mOnScrollListener.scrollUp();
            }
            return true;
        }
    }
    
    public interface OnScrollListener {
        public void scrollDown();
        public void scrollUp();
    }
}