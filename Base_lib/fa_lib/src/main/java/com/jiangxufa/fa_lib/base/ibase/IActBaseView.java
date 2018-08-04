package com.jiangxufa.fa_lib.base.ibase;

import android.content.Intent;

/**
 * 创建时间：2018/6/25
 * 编写人：lenovo
 * 功能描述：
 */

public interface IActBaseView extends IBaseView{

    void finish();

    void finish(int result);

    void finish(int result, Intent intent);

}
