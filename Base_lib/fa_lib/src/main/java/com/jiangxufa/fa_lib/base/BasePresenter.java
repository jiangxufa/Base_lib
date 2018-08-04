package com.jiangxufa.fa_lib.base;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jiangxufa.fa_lib.base.ibase.IBaseView;
import com.jiangxufa.fa_lib.base.ibase.IPresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 创建时间：2018/6/8
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class BasePresenter<IView extends IBaseView> implements GenericLifecycleObserver, IPresenter {
    private static final String TAG = "BasePresenter";

    protected IView mView;

    protected CompositeDisposable mCompositeDisposable;

    public BasePresenter(IView view) {
        this.mView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    private final BehaviorSubject<Lifecycle.Event> lifecycleSubject = BehaviorSubject.create();

    protected final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull Lifecycle.Event event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        lifecycleSubject.onNext(event);
        switch (event) {
            case ON_ANY:
                Log.d(TAG, "BasePresenter ON_ANY: ");
                break;
            case ON_CREATE:
                Log.d(TAG, "BasePresenter ON_CREATE: ");
                break;
            case ON_START:
                Log.d(TAG, "BasePresenter ON_START: ");
                break;
            case ON_RESUME:
                Log.d(TAG, "BasePresenter ON_RESUME: ");
                break;
            case ON_PAUSE:
                Log.d(TAG, "BasePresenter ON_PAUSE: ");
                break;
            case ON_STOP:
                Log.d(TAG, "BasePresenter ON_STOP: ");
                break;
            case ON_DESTROY:
                Log.d(TAG, "BasePresenter ON_DESTROY: ");
                dropView();
                unDispose();
                onDestroy();
                break;
        }
    }

    protected void onDestroy(){}

    public void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);//将所有 Disposable 放入集中处理
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
    }

    @Override
    public void dropView() {
        this.mView = null;
    }
}








