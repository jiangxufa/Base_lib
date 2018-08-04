package com.jiangxufa.base_lib.mvp.contract;

import com.jiangxufa.fa_lib.base.ibase.IRefreshView;
import com.jiangxufa.net_lib.bean.recommend.Recommend;

import java.util.List;

/**
 * 创建时间：2018/7/3
 * 编写人：lenovo
 * 功能描述：
 */

public interface RecommendContract {

    interface RecommendView extends IRefreshView{
        void showRecommend(List<Recommend> recommend);
    }

}
