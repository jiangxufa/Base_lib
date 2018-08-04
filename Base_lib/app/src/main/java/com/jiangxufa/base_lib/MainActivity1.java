package com.jiangxufa.base_lib;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.FragmentUtils;
import com.jiangxufa.widget_lib.quickindex.QuickIndexFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity1 extends AppCompatActivity {

    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private QuickIndexFragment quickIndexFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);

        MyFragment myFragment = MyFragment.newInstance();

//        if (savedInstanceState == null) {
//            quickIndexFragment = QuickIndexFragment.newInstance();
            FragmentUtils.add(getSupportFragmentManager(),
                    myFragment, R.id.activity_main,"quickIndexFragment");
//        } else {
//            quickIndexFragment = (QuickIndexFragment) getSupportFragmentManager().findFragmentByTag("quickIndexFragment");
//            FragmentUtils.add(getSupportFragmentManager(),
//                    quickIndexFragment, R.id.activity_main,"quickIndexFragment");
//        }
//
//        activityMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity1.this,MainActivity1.class));
//            }
//        });

    }


}
