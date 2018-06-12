package com.jiangxufa.fa_lib.base.ibase;

import com.jiangxufa.fa_lib.base.ToastStatus;

/**
 * 创建时间：2018/6/8
 * 编写人：lenovo
 * 功能描述：
 */

public interface IBaseView {

    void showToast(String s);

    void showToast(ToastStatus status, String s);

}
