package com.jiangxufa.fa_lib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建时间：2018/6/9
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private AppCompatActivity activity;
    private P mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (AppCompatActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(activity, getViewResourse(), null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = initPresenter(this);
            getLifecycle().addObserver(mPresenter);
        }
    }

    protected abstract int getViewResourse();

    protected abstract void initView(View view);

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract P initPresenter(BaseFragment<P> baseFragment);

    @Override
    public void onDetach() {
        super.onDetach();
        if (activity != null) activity = null;
    }
}
