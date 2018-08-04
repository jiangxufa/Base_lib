package com.jiangxufa.fa_lib.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.jiangxufa.fa_lib.R;


/**
 * 创建时间：2018/4/12
 * 编写人：lenovo
 * 功能描述：
 */

public class TipPageLinearLayout extends LinearLayout {

    private int contentSize1 = 16;
    private int contentSize2 = 16;
    private int contentTextColor1 = ContextCompat.getColor(getContext(), R.color.empty_color);
    private int contentTextColor2 = ContextCompat.getColor(getContext(),R.color.empty_color);
    private String content1;
    private String content2;
    private int imgRes;
    private TextView contentText2;
    private TextView contentText1;
    private ImageView tipImage;


    public TipPageLinearLayout(Context context) {
        this(context,null);
    }

    public TipPageLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public TipPageLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,  R.styleable.TipPageLinearLayout);
        content1 = a.getString(R.styleable.TipPageLinearLayout_contentText1);
        content2 = a.getString(R.styleable.TipPageLinearLayout_contentText2);
        contentTextColor1 = a.getColor(R.styleable.TipPageLinearLayout_contentTextColor1,
                ContextCompat.getColor(getContext(),R.color.empty_color));
        contentTextColor2 = a.getColor(R.styleable.TipPageLinearLayout_contentTextColor2,
                ContextCompat.getColor(getContext(),R.color.empty_color));
        contentSize1 = a.getInt(R.styleable.TipPageLinearLayout_contentTextSize1,16);
        contentSize2 = a.getInt(R.styleable.TipPageLinearLayout_contentTextSize2,16);
        imgRes = a.getResourceId(R.styleable.TipPageLinearLayout_imgRes, R.mipmap.empty_view);
        a.recycle();
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.WHITE);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        tipImage = new ImageView(getContext());
        tipImage.setImageResource(imgRes);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 20;
        addView(tipImage,params);
        contentText1 = new TextView(getContext());
        contentText1.setTextSize(contentSize1);
        contentText1.setTextColor(contentTextColor1);
        contentText1.setText(content1);
        contentText2 = new TextView(getContext());
        contentText2.setTextSize(contentSize2);
        contentText2.setTextColor(contentTextColor2);
        contentText2.setText(content2);
        addView(contentText1,params);
        addView(contentText2,params);
    }

    public void setOnGreenTextClick(OnClickListener click) {
        if (contentText2 != null) {
            contentText2.setOnClickListener(click);
        }
    }

    public void setTipConfig(int resourse,String tip) {
        if (tipImage != null) {
            tipImage.setImageResource(resourse);
        }
        if (contentText1 != null) {
            contentText1.setText(tip);
        }
        if (contentText2 != null) {
            contentText2.setVisibility(GONE);
        }
    }

    public void setTipContent(String text1,String text2) {
        setTextContent(contentText1,text1);
        setTextContent(contentText2,text2);
    }

    private void setTextContent(TextView contentText,String text) {
        if (contentText == null) return;
        if (StringUtils.isEmpty(text)) {
            contentText.setVisibility(GONE);
        } else {
            contentText.setVisibility(VISIBLE);
            contentText.setText(text);
        }
    }
}
