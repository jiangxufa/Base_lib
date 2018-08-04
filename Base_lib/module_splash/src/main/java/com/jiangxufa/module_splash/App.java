package com.jiangxufa.module_splash;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * 创建时间：2018/6/25
 * 编写人：lenovo
 * 功能描述：
 */

public class App extends Application {

    String APP_BASE_URL = "http://app.bilibili.com/";


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);


//        registerActivityLifecycleCallbacks();
    }
}
