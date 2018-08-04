package com.jiangxufa.base_lib;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.jiangxufa.fa_lib.utils.AppUtils;

/**
 * 创建时间：2018/6/21
 * 编写人：lenovo
 * 功能描述：
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        AppUtils.init(this);

//        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                EventBus.getDefault().register(activity);
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//                EventBus.getDefault().unregister(activity);
//            }
//        });
    }
}
