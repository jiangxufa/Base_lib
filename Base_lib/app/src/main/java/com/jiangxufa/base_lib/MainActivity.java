package com.jiangxufa.base_lib;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.amap.api.location.AMapLocation;
import com.blankj.utilcode.util.ToastUtils;
import com.ifeixiu.gaode_lib.RxGaoDeUtil;
import com.jiangxufa.net_lib.util.RxNetUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

    }

    private static final String TAG = "7777777";

    @Override
    protected void onResume() {
        super.onResume();
        RxGaoDeUtil.fetchLocation()
                .delay(10, TimeUnit.SECONDS)
                .compose(RxNetUtil.<AMapLocation>rxSchedulerHelper())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG, "accept: "+ Thread.currentThread().toString());
                        progressBar.setVisibility(View.VISIBLE);
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "run: "+ Thread.currentThread().toString());
                        progressBar.setVisibility(View.GONE);
                    }
                })
                .subscribe(new Observer<AMapLocation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: "+ Thread.currentThread().toString());
//                        d.dispose();
                    }

                    @Override
                    public void onNext(AMapLocation value) {
                        Log.d(TAG, "onNext: "+ Thread.currentThread().toString());
                        ToastUtils.showLong(value.getLongitude() + ","
                                + value.getLatitude());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+ Thread.currentThread().toString());
                        ToastUtils.showLong(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: "+ Thread.currentThread().toString());
//                        ToastUtils.showLong("onComplete");
                    }
                });
    }
}
