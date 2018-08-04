package com.jiangxufa.fa_lib.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.blankj.utilcode.util.ConvertUtils;
import com.jiangxufa.fa_lib.R;
import com.jiangxufa.fa_lib.base.ibase.IActBaseView;
import com.jiangxufa.fa_lib.ui.LoadingDialog;
import com.jiangxufa.fa_lib.utils.statebar.Eyes;

import butterknife.ButterKnife;

/**
 * 创建时间：2018/6/8
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IActBaseView{

    protected P mPresenter;

    protected Toolbar mToolbar;//Toolbar
    protected boolean mBack = true;

    private LoadingDialog loadingDialog;

    protected boolean supportTitleBar(){return false;}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        if (supportTitleBar()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Eyes.setStatusBarLightMode(this, getResources().getColor(R.color.colorPrimary));
        setContentView(getView());
        ButterKnife.bind(this);
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        if (mToolbar != null) {
            //初始化Toolbar
            initToolbar();
            //让组件支持Toolbar
            setSupportActionBar(mToolbar);
            if (mBack) mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        initWidget();
        initView();
        if (mPresenter == null) {
            mPresenter = initPresenter();
        }
        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
        }
        initData();
    }

    protected void initWidget() {}

    /**
     * 初始化Toolbar
     */
    protected void initToolbar() {
        if (mBack) mToolbar.setNavigationIcon(R.drawable.ic_clip_back_white);
    }


    protected abstract int getView();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract void initData();

    public P getPresenter() {
        if (mPresenter == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " mPresenter is null");
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        dismissLoading();
        super.onDestroy();
    }

    @Override
    public void finish(int result) {
        setResult(result);
        finish();
    }

    @Override
    public void finish(int result, Intent intent) {
        setResult(result, intent);
        finish();
    }


    @Override
    public void showLoadingDilaog() {
        showLoadingDialog("");
    }

    @Override
    public void showLoadingDialog(String msg) {
        if (isFinishing()) return;
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.setMessage(msg);
        loadingDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 隐藏View
     *
     * @param views 视图
     */
    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 显示View
     *
     * @param views 视图
     */
    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * 隐藏View
     *
     * @param id
     */
    protected void gone(final @IdRes int... id) {
        if (id != null && id.length > 0) {
            for (int resId : id) {
                View view = $(resId);
                if (view != null)
                    gone(view);
            }
        }

    }

    /**
     * 显示View
     *
     * @param id
     */
    protected void visible(final @IdRes int... id) {
        if (id != null && id.length > 0) {
            for (int resId : id) {
                View view = $(resId);
                if (view != null)
                    visible(view);
            }
        }
    }

    private View $(@IdRes int id) {
        View view;
        view = this.findViewById(id);
        return view;
    }

    //************************************工具******************************************
    public float dp2px(float dp) {
        return ConvertUtils.dp2px(dp);
    }
}
