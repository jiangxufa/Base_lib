package com.jiangxufa.push_lib;

import android.app.Activity;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;

/**
 * 创建时间：2018/6/23
 * 编写人：lenovo
 * 功能描述：
 */

public class HwPushUtils {

    public static void init() {
        HMSAgent.init(Utils.getApp());
        // 建议在应用启动时调用Connect。
        HMSAgent.Push.getToken(new GetTokenHandler() {
            @Override
            public void onResult(int rst) {
                LogUtils.iTag( "HwPush onResult: "+rst);
            }
        });
    }

    public static void connect(Activity activity) {
        // 0表示连接成功
        HMSAgent.connect(activity, new ConnectHandler() {
            @Override
            public void onConnect(int rst) {
                LogUtils.iTag("HwPush onConnect: " + "HMS connect end:" + rst);
            }
        });
    }

}
