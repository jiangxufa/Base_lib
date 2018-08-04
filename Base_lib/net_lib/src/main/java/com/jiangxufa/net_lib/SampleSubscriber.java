package com.jiangxufa.net_lib;

import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 创建时间：2018/1/17
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class SampleSubscriber<T> implements Observer<T> {

    private CompositeDisposable mDisposable;

    public SampleSubscriber(CompositeDisposable disposable) {
        this.mDisposable = disposable;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable.add(d);
    }


    @Override
    public void onError(Throwable t) {
        try {
                RetrofitException.ResponeThrowable exception = RetrofitException.retrofitException(t);
        } catch (Exception e) {
            ToastUtils.showShort(t.toString());
        }
    }

    @Override
    public void onComplete() {

    }
}
