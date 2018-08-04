package com.jiangxufa.base_lib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jiangxufa.base_lib.R;
import com.jiangxufa.fa_lib.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * 创建时间：2018/7/2
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class BaseHomeFragment extends BaseFragment {

    public Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//支持menu
    }


    @Override
    protected void initView(View view) {
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = ButterKnife.findById(mRootView, R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            //换成下面这句就OK了
            mToolbar.inflateMenu(R.menu.menu_main);
        }
    }

}
