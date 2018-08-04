package com.jiangxufa.fa_lib.base.ibase;

/**
 * 创建时间：2018/6/8
 * 编写人：lenovo
 * 功能描述：
 */

public interface IBaseView {

    void showLoadingDilaog();

    void showLoadingDialog(String msg);

    void dismissLoading();
}
