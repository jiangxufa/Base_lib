package com.jiangxufa.fa_lib.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.jiangxufa.fa_lib.R;
import com.jiangxufa.fa_lib.base.ibase.IRefreshBaseView;

import butterknife.ButterKnife;

/**
 * 创建时间：2018/6/27
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class BaseRefreshActivity<P extends BaseRefreshPresenter> extends BaseActivity<P> implements
        SwipeRefreshLayout.OnRefreshListener,IRefreshBaseView {
    protected RecyclerView mRecycler;
    protected SwipeRefreshLayout mRefresh;
    protected boolean mIsRefreshing = false;

    @Override
    protected void initWidget() {
        super.initWidget();
        mRefresh = ButterKnife.findById(this, R.id.refresh);
        mRecycler = ButterKnife.findById(this,R.id.recycler);
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        if (mRefresh != null) {
            mRefresh.setColorSchemeResources(R.color.colorPrimary);
            mRecycler.post(new Runnable() {
                @Override
                public void run() {
                    mRefresh.setRefreshing(true);
                    loadData();
                }
            });
            mRefresh.setOnRefreshListener(this);
        }
    }

    @Override
    public void onRefresh() {
        clearData();
        loadData();
    }

    protected void clearData() {
        mIsRefreshing = true;
    }

    @Override
    public void complete() {
        if (mRefresh != null) {
            mRefresh.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRefresh.setRefreshing(false);
                    mIsRefreshing = false;
                    ToastUtils.showShort(getString(R.string.refresh_success));
                }
            },300);
        }
    }

    /**
     * 加载数据
     */
    @Override
    public void loadData() {
        mPresenter.refreshData(true);
    }

}
