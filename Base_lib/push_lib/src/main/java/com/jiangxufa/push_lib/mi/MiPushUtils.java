package com.jiangxufa.push_lib.mi;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * 创建时间：2018/6/23
 * 编写人：lenovo
 * 功能描述：https://dev.mi.com/console/doc/detail?pId=41
 */

public class MiPushUtils {

    // user your appid the key.
    private static final String APP_ID = "1000270";
    // user your appid the key.
    private static final String APP_KEY = "670100056270";
    // 此TAG在adb logcat中检索自己所需要的信息， 只需在命令行终端输入 adb logcat | grep
    // com.xiaomi.mipushdemo
    public static final String TAG = "com.xiaomi.mipushdemo";

    public static void init() {
        // 注册push服务，注册成功后会向DemoMessageReceiver发送广播
        // 可以从DemoMessageReceiver的onCommandResult方法中MiPushCommandMessage对象参数中获取注册信息
        if (shouldInit()) {
            MiPushClient.registerPush(Utils.getApp(), APP_ID, APP_KEY);
        }

        if (AppUtils.isAppDebug()) {
            LoggerInterface newLogger = new LoggerInterface() {
                @Override
                public void setTag(String tag) {
                    // ignore
                }

                @Override
                public void log(String content, Throwable t) {
                    LogUtils.iTag("MiPush " + content + " Throwable " + t);
                }

                @Override
                public void log(String content) {
                    LogUtils.iTag("MiPush " + content);
                }
            };
            Logger.setLogger(Utils.getApp(), newLogger);
        }
    }

    private static boolean shouldInit() {
        ActivityManager am = ((ActivityManager) Utils.getApp().getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = Utils.getApp().getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

}
