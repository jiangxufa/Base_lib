package com.jiangxufa.base_lib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 创建时间：2018/7/10
 * 编写人：lenovo
 * 功能描述：
 */

public class CustomActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_custom);
        setContentView(R.layout.act_drag_layout);
        DragLayout dl = (DragLayout) findViewById(R.id.dl);
        dl.setOrientation(DragLayout.Orientation.Horizontal);
    }
}
