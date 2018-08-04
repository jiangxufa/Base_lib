package com.jiangxufa.module_splash;

import com.jiangxufa.net_lib.Network;
import com.jiangxufa.net_lib.api.AppService;

/**
 * 创建时间：2018/6/25
 * 编写人：lenovo
 * 功能描述：
 */

public class RetrofitHelper {

    private static String APP_BASE_URL = "http://app.bilibili.com";
    private static AppService apiService;

    public static AppService getService() {
        if (apiService == null) {
           Network.init();
           Network.initService(APP_BASE_URL,AppService.class);
            apiService =Network.getService(AppService.class);
        }
        return apiService;
    }

}
