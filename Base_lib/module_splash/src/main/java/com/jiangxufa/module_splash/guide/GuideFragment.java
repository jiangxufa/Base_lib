package com.jiangxufa.module_splash.guide;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

import com.blankj.utilcode.util.SPUtils;
import com.jiangxufa.fa_lib.Constants;
import com.jiangxufa.fa_lib.base.BaseFragment;
import com.jiangxufa.fa_lib.base.BasePresenter;
import com.jiangxufa.module_splash.R;
import com.jiangxufa.module_splash.SplashActivity;
import com.tmall.ultraviewpager.UltraViewPager;

/**
 * 创建时间：2018/6/25
 * 编写人：lenovo
 * 功能描述：UltraViewPager的使用参考https://github.com/alibaba/UltraViewPager/blob/master/README-ch.md
 */

public class GuideFragment extends BaseFragment {

    private UltraViewPager viewPager;
    private ImageButton guide_btn_start;

    public static GuideFragment newInstance() {
        return new GuideFragment();
    }


    @Override
    protected int getViewResourse() {
        return R.layout.module_splash_act_guide;
    }

    @Override
    protected void initView(View view) {
        viewPager = view.findViewById(R.id.vp_pagers);
        guide_btn_start = view.findViewById(R.id.guide_btn_start);
        guide_btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SplashActivity) mActivity).gotoLogin();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        viewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //UltraPagerAdapter 绑定子view到UltraViewPager
        PagerAdapter adapter = new UltraPagerAdapter(false);
        viewPager.setAdapter(adapter);

        //内置indicator初始化
        viewPager.initIndicator();
        //设置indicator样式
        viewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary))
                .setNormalColor(Color.WHITE)
                .setMargin(0,0,0, (int) dp2px(16))
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        //设置indicator对齐方式
        viewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //构造indicator,绑定到UltraViewPager
        viewPager.getIndicator().build();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int count = viewPager.getAdapter().getCount();
                if (position == count - 1) {
                    guide_btn_start.setVisibility(View.VISIBLE);
                } else {
                    guide_btn_start.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        SPUtils.getInstance().put(Constants.IS_FIRST_USE, true);
    }

    @Override
    protected BasePresenter initPresenter(BaseFragment baseFragment) {
        return null;
    }
}
