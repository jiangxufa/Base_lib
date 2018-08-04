package com.jiangxufa.widget_lib.quickindex;

import com.jiangxufa.fa_lib.base.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 创建时间：2018/6/25
 * 编写人：lenovo
 * 功能描述：
 */

public class QuickIndexPresenter extends BasePresenter<QuickIndexContract.IView>
        implements QuickIndexContract.IPresenter {

    public QuickIndexPresenter(QuickIndexContract.IView view) {
        super(view);
    }

    @Override
    public void getContacts() {
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend("李伟"));
        friends.add(new Friend("张三"));
        friends.add(new Friend("阿三"));
        friends.add(new Friend("阿四"));
        friends.add(new Friend("段誉"));
        friends.add(new Friend("段正淳"));
        friends.add(new Friend("张三丰"));
        friends.add(new Friend("陈坤"));
        friends.add(new Friend("林俊杰1"));
        friends.add(new Friend("陈坤2"));
        friends.add(new Friend("王二a"));
        friends.add(new Friend("林俊杰a"));
        friends.add(new Friend("张四"));
        friends.add(new Friend("林俊杰"));
        friends.add(new Friend("王二"));
        friends.add(new Friend("王二b"));
        friends.add(new Friend("赵四"));
        friends.add(new Friend("杨坤"));
        friends.add(new Friend("赵子龙"));
        friends.add(new Friend("杨坤1"));
        friends.add(new Friend("李伟1"));
        friends.add(new Friend("宋江"));
        friends.add(new Friend("宋江1"));
        friends.add(new Friend("李伟3"));
        //排序
        Collections.sort(friends);
        mView.showContacts(friends);
    }
}
