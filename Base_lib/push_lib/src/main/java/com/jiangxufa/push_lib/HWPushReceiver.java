package com.jiangxufa.push_lib;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.huawei.hms.support.api.push.PushReceiver;

/**
 * 创建时间：2018/6/22
 * 编写人：lenovo
 * 功能描述：
 */

public class HWPushReceiver extends PushReceiver {

    //通知栏点击事件处理
    // 由于是通过广播触发，所以当应用的进程不存在时可能由于系统原因
    // 无法通过广播方式拉起应用处理通知栏点击事件等。需要在手机上面为应用设置“允许自启动”才能在进程不存在时正常处理PushReceiver的广播
    // 该方法会在设置标签、点击打开通知栏消息、点击通知栏上的按钮之后被调用。
    // public static enum Event
    //    {
    //        NOTIFICATION_OPENED, //通知栏中的通知被点击打开
    //        NOTIFICATION_CLICK_BTN, //通知栏中通知上的按钮被点击
    //    }
    // http://developer.huawei.com/consumer/cn/service/hms/catalog/huaweipush_agent.html?page=hmssdk_huaweipush_devguide_client_agent
    @Override
    public void onEvent(Context context, Event event, Bundle bundle) {
        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            int notifyId = bundle.getInt(BOUND_KEY.pushNotifyId, 0);
            LogUtils.iTag("HwPush 收到通知栏消息点击事件,notifyId:" + notifyId);
            if (0 != notifyId) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notifyId);
            }
        }

        String message = bundle.getString(BOUND_KEY.pushMsgKey);
        ToastUtils.showShort(message);
        super.onEvent(context, event, bundle);
    }

    //接收token返回
    @Override
    public void onToken(Context context, String token) {
        super.onToken(context, token);
        LogUtils.iTag( "HwPush onToken: " + token );
    }

    //透传消息
    //供子类继承实现后，推送消息下来时会自动回调onPushMsg方法实现应用透传消息处理。
    @Override
    public boolean onPushMsg(Context context, byte[] msgBytes, Bundle extras) {
        try {
            //CP可以自己解析消息内容，然后做相应的处理
            String content = new String(msgBytes, "UTF-8");
            LogUtils.iTag("HwPush 收到PUSH透传消息,消息内容为:" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //push连接状态
    @Override
    public void onPushState(Context context, boolean pushState) {
        super.onPushState(context, pushState);
    }

}

