package com.jiangxufa.module_splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.FragmentUtils;
import com.ifeixiu.image_lib.ImageDisplayUtil;
import com.jiangxufa.fa_lib.WebFragment;
import com.jiangxufa.fa_lib.base.BaseActivity;
import com.jiangxufa.module_splash.guide.GuideFragment;
import com.jiangxufa.module_splash.login.LoginActivity;
import com.jiangxufa.net_lib.bean.app.Splash;
import com.jiangxufa.widget_lib.CircleSeekBar;

import java.util.Random;

import butterknife.BindView;

/**
 * 创建时间：2018/6/22
 * 编写人：lenovo
 * 功能描述：
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.IView {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.tv_environment)
    TextView tvEnvironment;
    @BindView(R.id.cs_progress)
    CircleSeekBar csProgress;
    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getView() {
        return R.layout.module_splash_act_splash;
    }

    @Override
    protected void initView() {
        tvEnvironment.setText("测试");
        ivSplash.setImageResource(R.mipmap.ic_default_bg);
    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected void initData() {
        mPresenter.getSplashData();
    }

    @Override
    public void showSplash(Splash splash) {
        if (!splash.data.isEmpty()) {
            int pos = new Random().nextInt(splash.data.size());
            ImageDisplayUtil.displayRound(ivSplash, splash.data.get(pos).thumb);
        } else {
            ivSplash.setImageResource(R.mipmap.ic_default_bg);
        }
    }

    /**
     *
     *用于显示h5的广告
     */
    @SuppressWarnings("unused")
    private void showAdvertising() {
        container.setVisibility(View.VISIBLE);
        WebFragment webFragment = (WebFragment) getSupportFragmentManager().findFragmentByTag("WebFragment");
        if (webFragment == null) {
            webFragment = WebFragment.newInstance();
            FragmentUtils.add(getSupportFragmentManager(), webFragment, R.id.container,"WebFragment",
                    R.anim.act_fade_in,R.anim.act_fade_out);
        }
        webFragment.setOnJumpClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.decideJump();
            }
        });
    }

    @Override
    public void showCountDown() {
        csProgress.start(3000L, 20L, new CircleSeekBar.OnFinishListener() {
            @Override
            public void onFinish() {
                mPresenter.decideJump();
            }
        });
    }

    @Override
    public void gotoGuide() {
        container.setVisibility(View.VISIBLE);
        GuideFragment guideFragment = (GuideFragment) getSupportFragmentManager().findFragmentByTag("GuideFragment");
        if (guideFragment == null) {
            guideFragment = GuideFragment.newInstance();
            FragmentUtils.add(getSupportFragmentManager(), guideFragment, R.id.container,"GuideFragment");
        }
    }

    @Override
    public void gotoLogin() {
        csProgress.setVisibility(View.GONE);
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @Override
    public void gotoHome() {

    }

}
