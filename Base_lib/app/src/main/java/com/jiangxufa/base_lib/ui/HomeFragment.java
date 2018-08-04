package com.jiangxufa.base_lib.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.jiangxufa.base_lib.R;
import com.jiangxufa.base_lib.model.event.SwitchNavigationEvent;
import com.jiangxufa.base_lib.ui.adapter.home.HomeAdapter;
import com.jiangxufa.fa_lib.base.BaseFragment;
import com.jiangxufa.fa_lib.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * 创建时间：2018/7/2
 * 编写人：lenovo
 * 功能描述：
 */

public class HomeFragment extends BaseHomeFragment {

    @BindView(R.id.stl_tabs)
    SlidingTabLayout mStlTabs;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    Unbinder unbinder;
    @BindView(R.id.ll_navigation)
    LinearLayout llNavigation;
    Unbinder unbinder1;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    protected int getViewResourse() {
        return R.layout.layout_main_toolbar;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initViewPager();

        RxView.clicks(llNavigation)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        EventBus.getDefault().post(new SwitchNavigationEvent());
                    }
                });
    }

    private void initViewPager() {
        HomeAdapter adapter = new HomeAdapter(getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(adapter);
        mStlTabs.setViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter initPresenter(BaseFragment baseFragment) {
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_game:
                ToastUtils.showShort("游戏");
                break;
            case R.id.menu_download:
                ToastUtils.showShort("缓存");
                break;
            case R.id.menu_search:
                ToastUtils.showShort("搜索");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
