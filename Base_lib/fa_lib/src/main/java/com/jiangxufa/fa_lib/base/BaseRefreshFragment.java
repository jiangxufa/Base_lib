package com.jiangxufa.fa_lib.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.jiangxufa.fa_lib.R;
import com.jiangxufa.fa_lib.base.ibase.IRefreshView;

import butterknife.ButterKnife;

/**
 * 创建时间：2018/7/3
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class BaseRefreshFragment<P extends BaseRefreshPresenter> extends BaseFragment<P> implements
        SwipeRefreshLayout.OnRefreshListener, IRefreshView {

    protected RecyclerView mRecycler;
    protected SwipeRefreshLayout mRefresh;
    protected boolean mIsRefreshing = false;

    @Override
    protected void initView(View view) {
        mRefresh = ButterKnife.findById(view, R.id.refresh);
        mRecycler = ButterKnife.findById(view, R.id.recycler);
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
    protected P initPresenter(BaseFragment baseFragment) {
        return null;
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
    public void loadData() {
        mPresenter.refreshData(true);
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
            }, 300);
        }
    }
}
