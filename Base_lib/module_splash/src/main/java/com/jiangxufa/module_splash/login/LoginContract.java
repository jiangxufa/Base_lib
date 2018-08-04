package com.jiangxufa.module_splash.login;

import com.jiangxufa.fa_lib.base.ibase.IActBaseView;

/**
 * 创建时间：2018/6/26
 * 编写人：lenovo
 * 功能描述：
 */

class LoginContract {

    interface IView extends IActBaseView {

        void showUserNameError();

        void showUserPasswordError();

        void gotoForgetPsw();

        void gotoRegistor();

        void gotoLogin();
    }

    interface IPresenter {

        void login();

        void registor();
    }

}
