package com.jiangxufa.base_lib.ui.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiangxufa.base_lib.R;
import com.jiangxufa.base_lib.mvp.contract.RecommendContract;
import com.jiangxufa.base_lib.mvp.presenter.RecommendPresenter;
import com.jiangxufa.base_lib.ui.adapter.home.RecommendAdapter;
import com.jiangxufa.fa_lib.base.BaseFragment;
import com.jiangxufa.fa_lib.base.BaseRefreshFragment;
import com.jiangxufa.fa_lib.base.BaseRefreshPresenter;
import com.jiangxufa.fa_lib.utils.AppUtils;
import com.jiangxufa.net_lib.bean.recommend.MulRecommend;
import com.jiangxufa.net_lib.bean.recommend.Recommend;
import com.jiangxufa.widget_lib.divider.VerticalDividerItemDecoration;

import java.util.List;

import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 创建时间：2018/7/3
 * 编写人：lenovo
 * 功能描述：
 */

public class RecommendFragment extends BaseRefreshFragment<BaseRefreshPresenter>
        implements RecommendContract.RecommendView {

    Unbinder unbinder;
    private RecommendAdapter adapter;

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }


    @Override
    protected int getViewResourse() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new RecommendAdapter();
        GridLayoutManager mLayoutManager = new GridLayoutManager(mActivity, 2);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return adapter.getData().get(position).spanSize;
            }
        });
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(adapter);
        //添加分割条
        VerticalDividerItemDecoration build = new VerticalDividerItemDecoration.Builder(getActivity())
                .color(AppUtils.getColor(R.color.transparent))
                .sizeResId(R.dimen.dp_10)
                .showLastDivider()
                .build();
        mRecycler.addItemDecoration(build);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected BaseRefreshPresenter initPresenter(BaseFragment baseFragment) {
        return new RecommendPresenter(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_rank, R.id.iv_rank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rank:
            case R.id.iv_rank:
                ToastUtils.showShort("进入排行榜");
                break;
        }
    }

    @Override
    public void showRecommend(List<Recommend> recommend) {
        final List<MulRecommend> data = adapter.getData();
        data.clear();
        Stream.of(recommend)
                .forEach(new Consumer<Recommend>() {
                    @Override
                    public void accept(Recommend recommend) {
                        if (recommend.banner_item != null) {
                            data.add(new MulRecommend(MulRecommend.TYPR_HEADER, MulRecommend.HEADER_SPAN_SIZE, recommend.banner_item));
                        } else {
                            data.add(new MulRecommend(MulRecommend.TYPE_ITEM, MulRecommend.ITEM_SPAN_SIZE, recommend));
                        }
                    }
                });
        adapter.notifyDataSetChanged();
    }
}
