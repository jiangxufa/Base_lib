package com.jiangxufa.fa_lib.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jiangxufa.fa_lib.base.ibase.IBaseView;
import com.jiangxufa.fa_lib.utils.statebar.Eyes;

/**
 * 创建时间：2018/6/8
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements IBaseView {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Eyes.setStatusBarLightMode(this, Color.WHITE);
        setContentView(getView());
        initView();
        mPresenter = initPresenter(this);
        getLifecycle().addObserver(mPresenter);
        initData();
    }

    protected abstract int getView();

    protected abstract void initView();

    protected abstract P initPresenter(BaseActivity<P> context);

    protected abstract void initData();

    public P getPresenter() {
        if (mPresenter == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " mPresenter is null");
        return mPresenter;
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void showToast(ToastStatus status, String s) {

    }
}
