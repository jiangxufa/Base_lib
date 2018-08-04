package com.jiangxufa.widget_lib.quickindex;

/**
 * Created by 江徐发 on 2016/12/16.
 */
public class Friend implements Comparable<Friend>{
    public String pinyin;
    public String name;
    public Friend(String name) {
        this.name = name;

       pinyin = PinYinUtil.getPinYin(this.name);
    }

    @Override
    public int compareTo(Friend o) {
        return this.pinyin.compareTo(o.pinyin);
    }
}

