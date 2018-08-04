package com.jiangxufa.fa_lib.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.jiangxufa.fa_lib.R;

/**
 * 创建时间：2018/6/29
 * 编写人：lenovo
 * 功能描述：
 */

public class LoadingDialog extends AppCompatDialog {

    private final TextView textView;
    private final ProgressBar ivLoading;

    public LoadingDialog(Context context) {
        super(context,R.style.LoadingDialog);
        setContentView(R.layout.dialog_standard_progress);
        textView = findViewById(R.id.tv_tip);
        ivLoading = findViewById(R.id.pb_loading);
        setCanceledOnTouchOutside(true);
    }

    public LoadingDialog setMessage(String text){
        if (!StringUtils.isEmpty(text)) {
            textView.setText(text);
        } else {
            textView.setText("加载中..");
        }
        return this;
    }

}
