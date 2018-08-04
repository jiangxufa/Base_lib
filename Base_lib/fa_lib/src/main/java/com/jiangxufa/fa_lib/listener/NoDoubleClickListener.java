package com.jiangxufa.fa_lib.listener;

import android.util.Log;
import android.view.View;

/**
 * Created by lenovo on 2017/11/22.
 * 防止二次点击的监听器
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {

    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        Log.d("NoDoubleClickListener","currentTime:"+currentTime +" lastClickTime:"+lastClickTime);
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View v);
}
