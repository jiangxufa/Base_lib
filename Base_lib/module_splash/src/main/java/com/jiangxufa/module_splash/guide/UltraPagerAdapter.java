package com.jiangxufa.module_splash.guide;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiangxufa.module_splash.R;

/**
 * 创建时间：2018/6/25
 * 编写人：lenovo
 * 功能描述：
 */

class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;
    private int[] mImageIds = new int[]{
                    Color.parseColor("#2196F3"),
                    Color.parseColor("#673AB7"),
                    Color.parseColor("#009688"),
                    Color.parseColor("#607D8B"),
                    Color.parseColor("#F44336")};

    public UltraPagerAdapter(boolean isMultiScr) {
        this.isMultiScr = isMultiScr;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.module_splash_item_image, null);
        ImageView iv_image =  frameLayout.findViewById(R.id.iv_image);
        TextView tv_index =  frameLayout.findViewById(R.id.tv_index);
        tv_index.setText(position + "");
        frameLayout.setId(R.id.module_splash_item_id);
        switch (position) {
            case 0:
                iv_image.setBackgroundColor(Color.parseColor("#2196F3"));
                break;
            case 1:
                iv_image.setBackgroundColor(Color.parseColor("#673AB7"));
                break;
            case 2:
                iv_image.setBackgroundColor(Color.parseColor("#009688"));
                break;
            case 3:
                iv_image.setBackgroundColor(Color.parseColor("#607D8B"));
                break;
            case 4:
                iv_image.setBackgroundColor(Color.parseColor("#F44336"));
                break;
        }
        container.addView(frameLayout);
        return frameLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        FrameLayout view = (FrameLayout) object;
        container.removeView(view);
    }
}