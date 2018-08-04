package com.jiangxufa.net_lib.util;


import android.support.annotation.NonNull;
import android.view.View;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jiangxufa.net_lib.ApiException;
import com.jiangxufa.net_lib.DoResponse;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建时间：2018/1/17
 * 编写人：lenovo
 * 功能描述：
 */
public class RxNetUtil {

    /**
     * 统一线程处理
     *
     * @param <T> t
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerFlowHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> FlowableTransformer<DoResponse<T>, T> handleFlowResult() {   //compose判断结果
        return new FlowableTransformer<DoResponse<T>, T>() {
            @Override
            public Flowable<T> apply(@NonNull Flowable<DoResponse<T>> upstream) {
                return upstream.flatMap(new Function<DoResponse<T>, Publisher<T>>() {
                    @Override
                    public Flowable<T> apply(@NonNull DoResponse<T> tDoResponse) throws Exception {
                        if (tDoResponse.getCode() == DoResponse.CODE_SUCCESS) {
                            return createFlowData(tDoResponse.getData());
                        } else {
                            return Flowable.error(new ApiException(tDoResponse.getCode(),tDoResponse.getTip()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<DoResponse<T>, T> handleResult() {   //compose判断结果
        return new ObservableTransformer<DoResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<DoResponse<T>> upstream) {
                return upstream.flatMap(new Function<DoResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(DoResponse<T> tDoResponse)  {
                        try {
                            if (tDoResponse.isSucc()) {
                                return createData(tDoResponse.getData());
                            } else {
                                ApiException apiException = new ApiException(tDoResponse.getCode(), tDoResponse.getTip());
                                return Observable.error(apiException);
                            }
                        } catch (Exception e) {
                            return Observable.error(e);
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {

            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                try {
                    subscriber.onNext(t);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * 生成Flowable
     *
     * @param <T> t
     * @return FlowableTransformer
     */
    public static <T> Flowable<T> createFlowData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }


    public static Observable<Object> clickDoubleCheck(View view) {
        return RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 简化网络监测
     * @param <T> t
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, Boolean> rxNetworkHelp() {
        return new ObservableTransformer<T, Boolean>() {
            @Override
            public ObservableSource<Boolean> apply(Observable<T> upstream) {
                return upstream.flatMap(new Function<T, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(T t) throws Exception {
                        boolean networkConnected = NetworkUtils.isConnected();
                        if (!networkConnected) ToastUtils.showShort("网络不好，请稍后再试...");
                        return Observable.just(networkConnected);
                    }
                });
            }
        };
    }

}

