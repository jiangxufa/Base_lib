package com.ifeixiu.image_lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;


public class ProgressImageView2 extends ImageView {

	// 画实心圆的画笔
	private Paint mCirclePaint;
	// 画圆环的画笔
	private Paint mRingPaint;

	// 圆形颜色
	private int mCircleColor= R.color.image_lib_alpha_0x00;
	private int mRingColor= R.color.image_lib_white;

	// 半径
	private float mRadius;
	// 圆环半径
	private float mRingRadius;
	// 圆环宽度
	private float mStrokeWidth;
	// 圆心x坐标
	private int mXCenter;
	// 圆心y坐标
	private int mYCenter;
	// 总进度
	private float mTotalProgress = 1000;
	// 当前进度
	private float mProgress;


	public ProgressImageView2(Context context) {
		super(context);
		init();
	}

	public ProgressImageView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ProgressImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public void init(){

		mRadius =0;
		mStrokeWidth = DensityUtil.dip2px(2);
		mRingRadius = DensityUtil.dip2px(20);

		mCirclePaint = new Paint();
		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setColor(ContextCompat.getColor(getContext(),mCircleColor));
		mCirclePaint.setStyle(Paint.Style.FILL);

		mRingPaint = new Paint();
		mRingPaint.setAntiAlias(true);
		mRingPaint.setColor(ContextCompat.getColor(getContext(),mRingColor));
		mRingPaint.setStyle(Paint.Style.STROKE);
		mRingPaint.setStrokeWidth(mStrokeWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);



		mXCenter = getWidth() / 2;
		mYCenter = getHeight() / 2;

		canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);

		if (mProgress >= 0) {
			RectF oval = new RectF();
			oval.left = (mXCenter - mRingRadius);
			oval.top = (mYCenter - mRingRadius);
			oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
			oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
			float radius=((float)mProgress / mTotalProgress) * 360;
			if(radius<0.05 && mTotalProgress>0)
				radius=0.05f;
			canvas.drawArc(oval, -90,radius, false, mRingPaint);
		}
	}



	public void setProgress(float progress,float total) {
		mProgress = progress;
		mTotalProgress=total;
		postInvalidate();
	}
}
