package com.jiangxufa.module_splash.login;

import com.jiangxufa.fa_lib.base.BasePresenter;

/**
 * 创建时间：2018/6/26
 * 编写人：lenovo
 * 功能描述：
 */

public class LoginPresenter extends BasePresenter<LoginContract.IView> implements LoginContract.IPresenter {

    public LoginPresenter(LoginContract.IView view) {
        super(view);
    }

    @Override
    public void login() {
        mView.gotoLogin();
    }

    @Override
    public void registor() {
        mView.gotoRegistor();
    }

    public void forgetPsw() {
        mView.gotoForgetPsw();
    }
}
