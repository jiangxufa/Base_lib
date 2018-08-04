package com.jiangxufa.module_splash.login;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jiangxufa.fa_lib.base.BaseActivity;
import com.jiangxufa.module_splash.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * 创建时间：2018/6/25
 * 编写人：lenovo
 * 功能描述：
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.IView {

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.et_username)
    AppCompatEditText etUsername;
    @BindView(R.id.et_password)
    AppCompatEditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_user_delete)
    ImageView ivUserDelete;
    @BindView(R.id.iv_psw_delete)
    ImageView ivPswDelete;

    @Override
    protected boolean supportTitleBar() {
        return true;
    }

    @Override
    public void showUserNameError() {

    }

    @Override
    public void showUserPasswordError() {

    }

    @Override
    public void gotoForgetPsw() {

    }

    @Override
    public void gotoRegistor() {

    }

    @Override
    public void gotoLogin() {

    }

    @Override
    protected int getView() {
        return R.layout.module_splash_act_login;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        mToolbar.setTitle("登录");
    }

    @Override
    protected void initView() {
        btnLogin.setEnabled(false);

        RxView.focusChanges(etUsername)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        ivLeft.setImageResource(R.mipmap.ic_22);
                        ivRight.setImageResource(R.mipmap.ic_33);
                        if (aBoolean) {
                            if (StringUtils.isEmpty(etUsername.getText())) {
                                ivUserDelete.setVisibility(View.GONE);
                            } else {
                                ivUserDelete.setVisibility(View.VISIBLE);
                            }
                            Drawable[] drawables = etUsername.getCompoundDrawables();
                            drawables[0].setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                        } else {
                            ivUserDelete.setVisibility(View.GONE);
                            Drawable[] drawables = etUsername.getCompoundDrawables();
                            drawables[0].setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
                        }
                    }
                });

        RxView.focusChanges(etPassword)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        ivLeft.setImageResource(R.mipmap.ic_22_hide);
                        ivRight.setImageResource(R.mipmap.ic_33_hide);
                        if (aBoolean) {
                            if (StringUtils.isEmpty(etPassword.getText())) {
                                ivPswDelete.setVisibility(View.GONE);
                            } else {
                                ivPswDelete.setVisibility(View.VISIBLE);
                            }
                            Drawable[] drawables = etPassword.getCompoundDrawables();
                            drawables[0].setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                        } else {
                            ivPswDelete.setVisibility(View.GONE);
                            Drawable[] drawables = etPassword.getCompoundDrawables();
                            drawables[0].setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
                        }
                    }
                });

        RxView.clicks(tvForget)
                .throttleFirst(1000, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mPresenter.forgetPsw();
                    }
                });


        Observable.combineLatest(RxTextView.textChanges(etPassword),
                RxTextView.textChanges(etUsername), new BiFunction<CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                        if (!StringUtils.isEmpty(charSequence) && etPassword.hasFocus()) {
                            ivPswDelete.setVisibility(View.VISIBLE);
                        } else {
                            ivPswDelete.setVisibility(View.GONE);
                        }
                        if (!StringUtils.isEmpty(charSequence2) && etUsername.hasFocus()) {
                            ivUserDelete.setVisibility(View.VISIBLE);
                        } else {
                            ivUserDelete.setVisibility(View.GONE);
                        }
                        return !StringUtils.isEmpty(charSequence) && !StringUtils.isEmpty(charSequence2);
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                btnLogin.setEnabled(aBoolean);
            }
        });

        RxView.clicks(ivUserDelete)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        etUsername.setText("");
                    }
                });

        RxView.clicks(ivPswDelete)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        etPassword.setText("");
                    }
                });

        RxView.clicks(btnLogin)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mPresenter.login();
                    }
                });

        RxView.clicks(btnRegister)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mPresenter.registor();
                    }
                });


    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initData() {

    }

}
