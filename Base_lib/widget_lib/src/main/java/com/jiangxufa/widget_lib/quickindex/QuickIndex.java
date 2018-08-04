package com.jiangxufa.widget_lib.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 创建时间：2016/12/16
 * 编写人：江徐发
 * 功能描述：
 */

public class QuickIndex extends View {

    private String[] indexArr = {"❤", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z", "#"};
    public final int COLOR_DEFAULT = Color.BLUE;
    public final int COLOR_PRESSED = Color.BLACK;
    private Paint mPaint;
    int index = -1;
    private IndexListener listener;
    private int touchY;

    public QuickIndex(Context context) {
        this(context, null);
    }

    public QuickIndex(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public QuickIndex(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setColor(COLOR_DEFAULT);
        mPaint.setTextSize(32);
        //默认是左下角  中心
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    float cellHeight;//格子的高度

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cellHeight = getMeasuredHeight() * 1f / indexArr.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //遍历数组绘制字母
        for (int i = 0; i < indexArr.length; i++) {
            String s = indexArr[i];
            float x = getMeasuredWidth() / 2;
            float y = getTextHeight(s) / 2 + cellHeight / 2 + i * cellHeight;


            //判断是否按下
            mPaint.setColor(index == i ? COLOR_PRESSED : COLOR_DEFAULT);

            if (index == i) {
                mPaint.setTextSize(92);
            } else if (index == i - 1 || index == i + 1) {
                mPaint.setTextSize(64);
            } else if (index == i - 2 || index == i + 2) {
                mPaint.setTextSize(40);
            } else {
                mPaint.setTextSize(32);
            }


            canvas.drawText(s, x, y, mPaint);
            if (index == i) {
            mPaint.setColor(COLOR_PRESSED);
            mPaint.setTextSize(80);
            canvas.drawText(s,x-100,y,mPaint);
            }else{
                mPaint.setColor(Color.TRANSPARENT);
            }

        }

    }


    public float getTextHeight(String s) {
        Rect rect = new Rect();
        mPaint.getTextBounds(s, 0, s.length(), rect);
        return rect.height();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchY = (int) event.getY();
                if (listener != null) {
                    listener.onTouchEnd(false);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                /*int */touchY = (int) event.getY();
                if (index != touchY / cellHeight) {
                    index = (int) (touchY / cellHeight);
                    if (index >= 0 && index < indexArr.length) {

                        if (listener != null) {
                            listener.onLetterchange(indexArr[index]);
                            listener.onHeightChange(touchY);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                index = -1;
                if (listener != null) {
                    listener.onTouchEnd(true);
                }
                break;
        }
        invalidate();
        return true;
    }


    public interface IndexListener {
        void onLetterchange(String letter);

        void onHeightChange(int height);

        void onTouchEnd(boolean end);
    }

    public void setOnIndexListener(IndexListener listener) {
        this.listener = listener;
    }


}
