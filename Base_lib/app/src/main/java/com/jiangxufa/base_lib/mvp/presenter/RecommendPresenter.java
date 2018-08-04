package com.jiangxufa.base_lib.mvp.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jiangxufa.base_lib.mvp.contract.RecommendContract;
import com.jiangxufa.base_lib.util.JsonUtils;
import com.jiangxufa.fa_lib.base.BaseRefreshPresenter;
import com.jiangxufa.net_lib.bean.recommend.Recommend;
import com.jiangxufa.net_lib.util.RxNetUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 创建时间：2018/7/3
 * 编写人：lenovo
 * 功能描述：
 */

public class RecommendPresenter extends BaseRefreshPresenter<RecommendContract.RecommendView> {
    public RecommendPresenter(RecommendContract.RecommendView view) {
        super(view);
    }

    @Override
    public void refreshData(boolean refresh) {
        Flowable.just(JsonUtils.readJson("recommend.json"))
                .map(new Function<String, List<Recommend>>() {
                    @Override
                    public List<Recommend> apply(String s) throws Exception {
                        Gson gson = new Gson();
                        JsonObject object = new JsonParser().parse(s).getAsJsonObject();
                        JsonArray array = object.getAsJsonArray("data");
                        List<Recommend> recommends = new ArrayList<>();
                        for (JsonElement jsonElement : array) {
                            recommends.add(gson.fromJson(jsonElement, Recommend.class));
                        }
                        return recommends;
                    }
                })
                .compose(RxNetUtil.<List<Recommend>>rxSchedulerFlowHelper())
                .subscribe(new DisposableSubscriber<List<Recommend>>() {
                    @Override
                    public void onNext(List<Recommend> recommends) {
                        mView.showRecommend(recommends);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        mView.complete();
                    }
                });
    }
}
