package com.jiangxufa.base_lib.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 创建时间：2018/7/10
 * 编写人：lenovo
 * 功能描述：
 */

public class CustomView extends LinearLayout {
    private ViewDragHelper mDragHelper;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        /**
         * @params ViewGroup forParent 必须是一个ViewGroup
         * @params float sensitivity 灵敏度
         * @params Callback cb 回调
         */
        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragCallback());
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {

        /**
         * 尝试捕获子view，一定要返回true
         *
         * @param child     child 尝试捕获的view
         * @param pointerId pointerId 指示器id？
         *                  这里可以决定哪个子view可以拖动
         */
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return true;
        }

        private static final String TAG = "ViewDragCallback";

        /**
         * 处理水平方向上的拖动
         *
         * @param child child 被拖动到view
         * @param left  left 移动到达的x轴的距离
         * @param dx    dx 建议的移动的x距离
         */
        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            Log.i(TAG, "clampViewPositionHorizontal: " + "left = " + left + ", dx = " + dx);
            if (getPaddingLeft() > left) {
                return getPaddingLeft();
            }

            if (getWidth() - child.getWidth() - getPaddingRight() < left) {
                return getWidth() - child.getWidth() - getPaddingRight();
            }

            return left;
        }

        /**
         * 处理竖直方向上的拖动
         *
         * @param child child 被拖动到view
         * @param top   top 移动到达的y轴的距离
         * @param dy    dy 建议的移动的y距离
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.i(TAG, "clampViewPositionVertical: " + "top = " + top + ", dy = " + dy);
            // 两个if主要是为了让viewViewGroup里
            if (getPaddingTop() > top) {
                return getPaddingTop();
            }

            if (getHeight() - child.getHeight() - getPaddingBottom() < top) {
                return getHeight() - child.getHeight() - getPaddingBottom();
            }

            return top;
        }

        /**
         * 当拖拽到状态改变时回调
         *
         * @params state 新的状态
         */
        @Override
        public void onViewDragStateChanged(int state) {
            switch (state) {
                case ViewDragHelper.STATE_DRAGGING:  // 正在被拖动
                    break;
                case ViewDragHelper.STATE_IDLE:  // view没有被拖拽或者 正在进行fling/snap
                    break;
                case ViewDragHelper.STATE_SETTLING: // fling完毕后被放置到一个位置
                    break;
            }
            super.onViewDragStateChanged(state);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_DOWN:
                mDragHelper.cancel(); // 相当于调用 processTouchEvent收到ACTION_CANCEL
                break;
        }

        /**
         * 检查是否可以拦截touch事件
         * 如果onInterceptTouchEvent可以return true 则这里return true
         */
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 处理拦截到的事件
         * 这个方法会在返回前分发事件
         */
        mDragHelper.processTouchEvent(event);
        return true;
    }
}
