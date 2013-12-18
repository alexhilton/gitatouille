package com.hilton.gitatouille;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class CustomizedWebView extends WebView {
    private static final float Y_EPISODE = 10.0f;
    private static final float X_EPISODE = 30.0f;
    private OnScrollListener mOnScrollListener;
    private float mXEpisode;
    private float mYEpisode;
    
    public CustomizedWebView(Context context) {
        super(context);
        initialize();
    }

    public CustomizedWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CustomizedWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }
    
    public void setScrollListener(OnScrollListener l) {
        mOnScrollListener = l;
    }
    
    private float mStartY;
    private float mStartX;
    private boolean mScrollingDown;
    private boolean mScrollingUp;
    private boolean mScrollingLeft;
    private boolean mScrollingRight;
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float currentX = event.getX();
        final float currentY = event.getY();
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            mStartX = event.getX();
            mStartY = event.getY();
            break;
        case MotionEvent.ACTION_MOVE: {
            final float dx = Math.abs(currentX - mStartX);
            final float dy = Math.abs(currentY - mStartY);
            if ((currentX - mStartX) > mXEpisode && dy <= mYEpisode) {
                if (!mScrollingLeft) {
                    mScrollingLeft = true;
                    mScrollingRight = false;
                    mScrollingUp = false;
                    mScrollingDown = false;
//                    mOnScrollListener.scrollLeft();
                }
                break;
            } else if ((mStartX - currentX) > mXEpisode && dy <= mYEpisode) {
                if (!mScrollingRight) {
                    mScrollingRight = true;
                    mScrollingLeft = false;
                    mScrollingDown = false;
                    mScrollingUp = false;
//                    mOnScrollListener.scrollRight();
                }
                break;
            }
            if ((currentY - mStartY) > mYEpisode && dx <= mXEpisode) {
                if (!mScrollingDown) {
                    mScrollingDown = true;
                    mScrollingUp = false;
                    mScrollingRight = false;
                    mScrollingLeft = false;
                    mOnScrollListener.scrollUp();
                }
                break;
            } else if ((mStartY - currentY) > mYEpisode && dx <= mXEpisode) {
                if (!mScrollingUp) {
                    mScrollingUp = true;
                    mScrollingDown = false;
                    mScrollingRight = false;
                    mScrollingLeft = false;
                    mOnScrollListener.scrollDown();
                }
                break;
            }
            break;
        }
        case MotionEvent.ACTION_UP:
            initialize();
            break;
        }
        return super.onTouchEvent(event);
    }

    private void initialize() {
        mScrollingDown = false;
        mScrollingUp = false;
        mScrollingLeft = false;
        mScrollingRight = false;
        mStartY = Float.NaN;
        mStartX = Float.NaN;
        mXEpisode = getContext().getResources().getDisplayMetrics().density * X_EPISODE;
        mYEpisode = getContext().getResources().getDisplayMetrics().density * Y_EPISODE;
    }
    
    public interface OnScrollListener {
        public void scrollDown();
        public void scrollUp();
        public void scrollLeft();
        public void scrollRight();
    }
}