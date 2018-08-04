package com.jiangxufa.fa_lib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ConvertUtils;
import com.jiangxufa.fa_lib.base.ibase.IBaseView;
import com.jiangxufa.fa_lib.ui.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 创建时间：2018/6/9
 * 编写人：lenovo
 * 功能描述：
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    protected AppCompatActivity mActivity;
    protected Context mContext;
    protected P mPresenter;
    protected View mRootView;
    // 标志位 标志已经初始化完成。
    protected boolean isViewCreated;
    //标志位 fragment是否可见
    protected boolean isVisible;
    //第一次可见
    private boolean isFirstVisible = true;
    private Unbinder mUnbinder;
    private LoadingDialog loadingDialog;


    @Override
    public void onAttach(Context context) {
        this.mActivity = (AppCompatActivity) context;
        this.mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != mRootView) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        } else {
            mRootView = View.inflate(mActivity, getViewResourse(), null);
            mActivity = (AppCompatActivity) getActivity();
            mContext = mActivity;
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        mUnbinder = ButterKnife.bind(this, view);
        initInject();
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = initPresenter(this);
        }
        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
        }
        if (isFirstVisible && isVisible && isViewCreated && isAdded()) {
            onFirstLoadData();
        }
    }


    protected abstract int getViewResourse();

    /**
     * 注入dagger2依赖
     */
    protected void initInject() {
    }

    protected abstract void initView(View view);

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract P initPresenter(BaseFragment<P> baseFragment);

    /**
     * Fragment数据的懒加载
     * <p>
     * setUserVisibileHint() 来知道当前一个fragment对用户来说是隐藏还是显示，
     * 这个方法仅仅工作在FragmentPagerAdapter中，不能被使用在一个普通的activity中。
     *
     * @param isVisibleToUser 是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            //需要fragment可见的时候刷新数据的时候用
            if (isViewCreated && isAdded()) {
                if (isFirstVisible) {
                    onFirstLoadData();
                }
                onResumeData();
            }
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 第一次Fragment可见的时候调用
     */
    protected void onFirstLoadData() {
        isFirstVisible = false;
    }

    /**
     * 每次Fragment可见的时候调用
     */
    protected void onResumeData() {
    }

    /**
     * Fragment不可见的时候调用
     */
    protected void onInvisible() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        dismissLoading();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mActivity != null) mActivity = null;
    }

    /**
     * 获取ApplicationContext 信息
     *
     * @return Context
     */
    public Context getApplicationContext() {
        return this.mContext == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.mContext.getApplicationContext();
    }

    @Override
    public void showLoadingDilaog() {
        showLoadingDialog("");
    }

    @Override
    public void showLoadingDialog(String msg) {
        if (!isAdded() || isDetached()) return;
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mContext);
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

    //************************************工具******************************************
    public float dp2px(float dp) {
        return ConvertUtils.dp2px(dp);
    }
}
