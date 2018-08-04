package com.jiangxufa.widget_lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建时间：2017/7/10
 * 编写人：lenovo
 * 功能描述：
 */

public class CircleSeekBar extends View {

    private Paint mPaint;
    private float mProgress;
    private int mWidth = 50;
    private int mHeight = 50;
    private RectF progressRect;
    private int mProgressBackColor = Color.parseColor("#939393");
    private int mProgressColor = Color.parseColor("#fb7299");
    private TextPaint mTextPaint;
    private String mText = "3秒";
    private int delta;
    private int cx;
    private int cy;
    private int l;
    private int t;
    private int r;
    private int b;
    private int radius;
    private CountDownTimer countDownTimer;

    public CircleSeekBar(Context context) {
        this(context, null);
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#72B44E"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.parseColor("#939393"));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(36);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;

        if (widthMode == MeasureSpec.EXACTLY) {
            measureWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            measureWidth = Math.min(widthSize, mWidth);
        } else {
            measureWidth = mWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = heightSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            measureHeight = Math.min(heightSize, mHeight);
        } else {
            measureHeight = mHeight;
        }

        int mRadius = measureWidth > measureHeight ? measureWidth : measureHeight;
        setMeasuredDimension(mRadius, mRadius);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float descent = mTextPaint.getFontMetrics().descent;
        float ascent = mTextPaint.getFontMetrics().ascent;
        delta = (int) Math.abs(descent + ascent);

        cx = getWidth() / 2;
        cy = getHeight() / 2;
        l = getPaddingLeft();
        t = getPaddingTop();
        r = getWidth() - getPaddingRight();
        b = getHeight() - getPaddingBottom();
        radius = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (progressRect == null) {
            progressRect = new RectF();
        }
        progressRect.set(l, t, r, b);
        mPaint.setColor(mProgressBackColor);
        canvas.drawCircle(cx, cy, radius, mPaint);
        mPaint.setColor(mProgressColor);
        canvas.drawArc(progressRect, 270, 360 * mProgress, false, mPaint);
        canvas.drawText(mText, cx, cy + delta/2, mTextPaint);

    }

    public void setmProgress(float progress) {
        this.mProgress = progress;
        invaidateSelft();
    }

    private void invaidateSelft() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    private OnFinishListener finishListener;

    public interface OnFinishListener {
        void onFinish();
    }

    public void setOnFinishListener(OnFinishListener finishListener) {
        this.finishListener = finishListener;
    }
    private boolean isrunning = false;
    public void start(Long time, Long interval, final OnFinishListener finishListener) {
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(time, interval) {
                @Override
                public void onTick(long l) {
                    isrunning = true;
                    float v = (3000f - l) / 3000f;
                    if (l > 2000) {
                        mText = "2秒";
                    } else if (l < 1000) {
                        mText = "1秒";
                    }
                    setmProgress(v);
                }

                @Override
                public void onFinish() {
                    isrunning = false;
                    mText = "跳转";
                    setmProgress(1f);
                    if (finishListener != null) {
                        finishListener.onFinish();
                    }
                    end();
                }
            };
        }
        if (!isrunning) {
            countDownTimer.start();
        }
    }

    public void end() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}
