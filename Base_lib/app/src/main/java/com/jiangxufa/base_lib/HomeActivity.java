package com.jiangxufa.base_lib;

import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;
import com.jiangxufa.base_lib.model.event.SwitchNavigationEvent;
import com.jiangxufa.base_lib.ui.HomeFragment;
import com.jiangxufa.fa_lib.base.BaseActivity;
import com.jiangxufa.fa_lib.base.BasePresenter;
import com.jiangxufa.fa_lib.utils.AppUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * 创建时间：2018/7/2
 * 编写人：lenovo
 * 功能描述：
 */

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private int mCurrentPos = -1;
    private List<HomeFragment> mFragments = new ArrayList<>();

    @Override
    protected int getView() {
        return R.layout.act_home;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mFragments = Collections.singletonList(HomeFragment.newInstance());
    }

    @Override
    protected void initView() {
        disableNavigationViewScrollbars(navView);
        navView.setNavigationItemSelectedListener(this);
        switchFragmentIndex();//初始化位置
    }

    private void switchFragmentIndex() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentPos != -1)
            transaction.hide(mFragments.get(mCurrentPos));
        if (!mFragments.get(0).isAdded()) {
            transaction.add(R.id.fl_content, mFragments.get(0));
        }
        transaction.show(mFragments.get(0)).commit();
        mCurrentPos = 0;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchNavigationEvent(SwitchNavigationEvent event) {
        toggleDrawer();
    }


    /**
     * DrawerLayout侧滑菜单开关
     */
    private void toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    /**
     * 去掉滚动条
     *
     * @param navigationView navigationView
     */
    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        AppUtils.runOnUIDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();
                item.setChecked(true);//设置选型被选中
                item.setCheckable(true);
                switch (id) {
                    case R.id.item_vip:
                        break;
                    case R.id.item_unicom:
                        break;
                    case R.id.item_down:
                        break;
                }
                ToastUtils.showShort("点击");
            }
        },230);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 监听back键处理
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(mDrawerLayout.getChildAt(1))) {
                mDrawerLayout.closeDrawers();
            } else {
                exitApp();
            }
        }
        return true;
    }

    private long exitTime  = -1;

    private void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showShort("再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
