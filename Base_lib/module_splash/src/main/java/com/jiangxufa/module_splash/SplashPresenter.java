package com.jiangxufa.module_splash;

import android.arch.lifecycle.Lifecycle;

import com.blankj.utilcode.util.SPUtils;
import com.jiangxufa.fa_lib.Constants;
import com.jiangxufa.fa_lib.base.BasePresenter;
import com.jiangxufa.net_lib.SampleSubscriber;
import com.jiangxufa.net_lib.api.AppService;
import com.jiangxufa.net_lib.bean.app.Splash;
import com.jiangxufa.net_lib.util.RxNetUtil;


/**
 * 创建时间：2018/6/22
 * 编写人：lenovo
 * 功能描述：
 */

public class SplashPresenter extends BasePresenter<SplashContract.IView> implements
        SplashContract.IPresenter {

    private final AppService service;

    public SplashPresenter(SplashContract.IView view) {
        super(view);
        mView.showCountDown();
        service = RetrofitHelper.getService();
    }

    @Override
    public void getSplashData() {
        service.getSplash()
                .compose(RxNetUtil.<Splash>rxSchedulerHelper())
                .compose(this.<Splash>bindUntilEvent(Lifecycle.Event.ON_PAUSE))
                .subscribe(new SampleSubscriber<Splash>(mCompositeDisposable) {
                    @Override
                    public void onNext(Splash splash) {
                        if (splash.code==0)
                            mView.showSplash(splash);
                    }
                });
    }

    @Override
    public void decideJump() {
        boolean used = SPUtils.getInstance().getBoolean(Constants.IS_FIRST_USE, false);
        if (!used) {
            mView.gotoGuide();
            return;
        }
        boolean flag = SPUtils.getInstance().getBoolean(Constants.IS_LOGINED_FLAG, false);
        if (flag) {
            mView.gotoHome();
        } else {
            mView.gotoLogin();
        }
    }

}
