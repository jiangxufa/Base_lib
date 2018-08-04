package com.jiangxufa.module_splash;

import com.jiangxufa.fa_lib.base.ibase.IActBaseView;
import com.jiangxufa.net_lib.bean.app.Splash;

/**
 * 创建时间：2018/6/22
 * 编写人：lenovo
 * 功能描述：
 */

public interface SplashContract {

    interface IView extends IActBaseView {
        void showSplash(Splash splash);

        void showCountDown();

        void gotoGuide();

        void gotoLogin();

        void gotoHome();
    }

    interface IPresenter {

        void getSplashData();

        void decideJump();
    }


}
