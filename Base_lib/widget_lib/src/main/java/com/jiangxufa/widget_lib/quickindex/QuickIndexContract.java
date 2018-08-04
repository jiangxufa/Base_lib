package com.jiangxufa.widget_lib.quickindex;

import com.jiangxufa.fa_lib.base.ibase.IBaseView;

import java.util.List;

/**
 * 创建时间：2018/6/25
 * 编写人：lenovo
 * 功能描述：
 */

public interface QuickIndexContract {

    interface IView extends IBaseView {

        void showContacts(List<Friend> friends);

    }

    interface IPresenter {

      void getContacts();
    }

}
