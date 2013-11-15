package com.hilton.gitatouille;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class CustomizedWebView extends WebView {
    private static final float EPISODE = 5.0f;
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
    
    private float mStartY = Float.NaN;
    private float mStartX = Float.NaN;
    private boolean mScrollingDown = false;
    private boolean mScrollingUp = false;
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float currentX = event.getX();
        final float currentY = event.getY();
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            mStartX = event.getX();
            mStartY = event.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            if (Math.abs(currentX - mStartX) > EPISODE) {
                break;
            }
            if ((currentY - mStartY) > EPISODE) {
                if (!mScrollingDown) {
                    mScrollingDown = true;
                    mScrollingUp = false;
                    mOnScrollListener.scrollUp();
                }
            } else if ((mStartY - currentY) > EPISODE) {
                if (!mScrollingUp) {
                    mScrollingUp = true;
                    mScrollingDown = false;
                    mOnScrollListener.scrollDown();
                }
            }
            break;
        case MotionEvent.ACTION_UP:
            mScrollingDown = false;
            mScrollingUp = false;
            mStartY = Float.NaN;
            mStartX = Float.NaN;
            break;
        }
        return super.onTouchEvent(event);
    }
    
    public interface OnScrollListener {
        public void scrollDown();
        public void scrollUp();
    }
}