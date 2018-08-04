package com.jiangxufa.fa_lib.base;

import com.jiangxufa.fa_lib.base.ibase.IRefreshPresenter;
import com.jiangxufa.fa_lib.base.ibase.IRefreshView;

/**
 * 创建时间：2018/6/29
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class BaseRefreshPresenter<IView extends IRefreshView> extends BasePresenter<IView> implements
        IRefreshPresenter{
    public BaseRefreshPresenter(IView view) {
        super(view);
    }
}
