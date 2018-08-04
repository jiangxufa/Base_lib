package com.jiangxufa.base_lib.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jiangxufa.base_lib.R;
import com.jiangxufa.fa_lib.base.BaseFragment;
import com.jiangxufa.fa_lib.base.BasePresenter;

/**
 * 创建时间：2018/7/3
 * 编写人：lenovo
 * 功能描述：
 */

public class LiveFragment extends BaseFragment {

    public static LiveFragment newInstance() {
        return new LiveFragment();
    }


    @Override
    protected int getViewResourse() {
        return R.layout.layout_text;
    }

    @Override
    protected void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setText(this.getClass().getSimpleName());
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter initPresenter(BaseFragment baseFragment) {
        return null;
    }
}
