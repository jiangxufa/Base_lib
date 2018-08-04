package com.jiangxufa.base_lib;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import static android.support.v4.widget.ViewDragHelper.create;

/**
 * 创建时间：2018/7/10
 * 编写人：lenovo
 * 功能描述：
 */

public class DragLayout extends FrameLayout {

    private ViewDragHelper viewDragHelper;
    private GestureDetectorCompat gestureDetector;
    private Context context;
    /**
     * 水平可以滚动的范围
     */
    private int horizontalRange;
    /**
     * 垂直可以滚动的范围
     */
    private int verticalRange;
    /**
     * 默认滚动式水平的
     */
    private Orientation orientation = Orientation.Horizontal;

    private int viewWidth;
    private int viewHeight;
    private int distanceLeft;
    private int distanceTop;

    private ViewGroup layoutMenu;
    private ViewGroup layoutContent;

    private Status status = Status.Close;

    enum Status {
        Open, Close, Drag
    }

    enum Orientation {
        Horizontal, Vertical
    }

    public DragLayout(@NonNull Context context) {
        this(context, null);
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gestureDetector = new GestureDetectorCompat(context,
                new XYScrollDetector());
        viewDragHelper = create(this, 1.0f, dragHelperCallback);
    }

    class XYScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx,
                                float dy) {
            if (orientation == Orientation.Vertical) {
                return Math.abs(dy) >= Math.abs(dx);
            }
            return Math.abs(dy) <= Math.abs(dx);
        }

    }

    private ViewDragHelper.Callback dragHelperCallback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return child == layoutContent;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            if (orientation == Orientation.Vertical)
                return 0;
            if (distanceLeft + dx < 0) {
                // 右边界
                return 0;
            } else if (distanceLeft + dx > horizontalRange) {
                // 左边界
                return horizontalRange;
            } else {
                // 左右边界范围内
                return left;
            }
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
            return horizontalRange;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            if (orientation == Orientation.Horizontal)
                return 0;

            if (distanceTop + dy < 0) {
                return 0;
            } else if (distanceTop + dy > verticalRange) {
                return verticalRange;
            } else {
                return top;
            }
        }

        @Override
        public int getViewVerticalDragRange(@NonNull View child) {
            return verticalRange;
        }

        private static final String TAG = "DragLayout";

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (orientation == Orientation.Vertical) {
                if (releasedChild == layoutMenu)
                    return;
                Log.i(TAG, "onViewReleased: " + " xvel=" + xvel + " yvel=" + yvel);
                if (yvel > 0) {
                    // 加速度向下
                    open();
                } else if (yvel < 0) {
                    // 加速度向上
                    close();
                } else if (releasedChild == layoutContent
                        && distanceTop > verticalRange * 0.3) {
                    // 如果释放时，手指在内容区且内容区离左边的距离是range * 0.3
                    open();
                } else {
                    close();
                }

            } else {
                if (xvel > 0) {
                    // 加速度向
                    open();
                } else if (xvel < 0) {
                    // 加速度向左
                    close();
                } else if (releasedChild == layoutContent
                        && distanceLeft > horizontalRange * 0.3) {
                    // 如果释放时，手指在内容区且内容区离左边的距离是range * 0.3
                    open();
                } else if (releasedChild == layoutMenu
                        && distanceLeft > horizontalRange * 0.7) {
                    // 如果释放时，手指在菜单区且内容区离左边的距离是range * 0.7
                    open();
                } else {
                    close();
                }
            }

        }

        // view在拖动过程坐标发生变化时会调用此方法，包括两个时间段：手动拖动和自动滚动
        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            if (orientation == Orientation.Horizontal) {
                if (changedView == layoutContent) {
                    distanceLeft = left;
                } else {
                    distanceLeft = distanceLeft + left;
                }
                if (distanceLeft < 0) {
                    distanceLeft = 0;
                } else if (distanceLeft > horizontalRange) {
                    distanceLeft = horizontalRange;
                }
                layoutMenu.layout(0, 0, viewWidth, viewHeight);
                layoutContent.layout(distanceLeft, 0, distanceLeft + viewWidth,
                        viewHeight);
                dispatchDragEvent(distanceLeft);
            } else {
                distanceTop = top;
                if (distanceTop < 0) {
                    distanceTop = 0;
                } else if (distanceTop > verticalRange) {
                    distanceTop = verticalRange;
                }
                layoutMenu.layout(0, 0, viewWidth, viewHeight);
                layoutContent.layout(0, distanceTop, viewWidth, distanceTop
                        + viewHeight);
                dispatchDragEvent(distanceTop);
            }

        }
    };

    private DragListener dragListener;

    public interface DragListener {
        /**
         * 已经打开
         */
        public void onOpen();

        /**
         * 已经关闭
         */
        public void onClose();

        /**
         * 真在拖拽
         */
        public void onDrag(float percent);
    }

    public void setDragListener(DragListener dragListener) {
        this.dragListener = dragListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        layoutMenu = (ViewGroup) getChildAt(0);
        layoutContent = (ViewGroup) getChildAt(1);
        layoutMenu.setClickable(true);
        layoutContent.setClickable(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = layoutMenu.getMeasuredWidth();
        viewHeight = layoutMenu.getMeasuredHeight();
        horizontalRange = (int) (viewWidth * 0.7);
        verticalRange = (int) (viewHeight * 0.9);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        layoutMenu.layout(0, 0, viewWidth, viewHeight);
        if (orientation == Orientation.Horizontal) {
            layoutContent.layout(distanceLeft, 0, distanceLeft + viewWidth,
                    viewHeight);
        } else {
            layoutContent.layout(0, distanceTop, viewWidth, distanceTop
                    + viewHeight);
        }

    }

    private void dispatchDragEvent(int mainLeft) {
        float percent;
        if (orientation == Orientation.Horizontal) {
            percent = mainLeft / (float) horizontalRange;
            animateView(percent);
        } else {
            percent = mainLeft / (float) verticalRange;
        }

        Status lastStatus = status;
        if (dragListener == null)
            return;
        dragListener.onDrag(percent);
        if (lastStatus != getStatus() && status == Status.Close) {
            dragListener.onClose();
        } else if (lastStatus != getStatus() && status == Status.Open) {
            dragListener.onOpen();
        }
    }

    private void animateView(float percent) {
        float f1 = 1 - percent * 0.3f;

        ViewHelper.setScaleX(layoutContent, f1);
        ViewHelper.setScaleY(layoutContent, f1);

        ViewHelper.setTranslationX(
                layoutMenu,
                -layoutMenu.getWidth() / 2.3f
                        + layoutMenu.getWidth() / 2.3f * percent);
        ViewHelper.setScaleX(layoutMenu, 0.5f + 0.5f * percent);
        ViewHelper.setScaleY(layoutMenu, 0.5f + 0.5f * percent);
        ViewHelper.setAlpha(layoutMenu, percent);

        getBackground().setColorFilter(
                evaluate(percent, Color.BLACK, Color.TRANSPARENT),
                PorterDuff.Mode.SRC_OVER);
    }

    private Integer evaluate(float fraction, Object startValue, Integer endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;
        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;
        return (int) ((startA + (int) (fraction * (endA - startA))) << 24)
                | (int) ((startR + (int) (fraction * (endR - startR))) << 16)
                | (int) ((startG + (int) (fraction * (endG - startG))) << 8)
                | (int) ((startB + (int) (fraction * (endB - startB))));
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public Status getStatus() {
        if (orientation == Orientation.Horizontal) {
            if (distanceLeft == 0) {
                status = Status.Close;
            } else if (distanceLeft == horizontalRange) {
                status = Status.Open;
            } else {
                status = Status.Drag;
            }
        } else {
            if (distanceTop == 0) {
                status = Status.Close;
            } else if (distanceTop == verticalRange) {
                status = Status.Open;
            } else {
                status = Status.Drag;
            }
        }

        return status;
    }

    public ViewGroup getlayoutContent() {
        return layoutContent;
    }

    public ViewGroup getlayoutMenu() {
        return layoutMenu;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void open() {
        open(true);
    }

    public void open(boolean animate) {
        if (animate) {
            if (orientation == Orientation.Horizontal) {
                if (viewDragHelper.smoothSlideViewTo(layoutContent,
                        horizontalRange, 0)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            } else {
                if (viewDragHelper.smoothSlideViewTo(layoutContent, 0,
                        verticalRange)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }

        } else {
            layoutContent.layout(horizontalRange, 0, horizontalRange
                    + viewWidth, viewHeight);
            dispatchDragEvent(horizontalRange);
        }
    }

    public void close() {
        close(true);
    }

    public void close(boolean animate) {
        if (animate) {

            if (viewDragHelper.smoothSlideViewTo(layoutContent, 0, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }

        } else {
            layoutContent.layout(0, 0, viewWidth, viewHeight);
            dispatchDragEvent(0);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_DOWN:
                viewDragHelper.cancel(); // 相当于调用 processTouchEvent收到ACTION_CANCEL
                break;
        }

        /**
         * 检查是否可以拦截touch事件
         * 如果onInterceptTouchEvent可以return true 则这里return true
         */
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

}
