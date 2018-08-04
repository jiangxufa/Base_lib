package com.jiangxufa.base_lib.ui.adapter.home;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ifeixiu.image_lib.ImageDisplayUtil;
import com.jiangxufa.base_lib.R;
import com.jiangxufa.fa_lib.utils.FormatUtils;
import com.jiangxufa.fa_lib.utils.NumberUtils;
import com.jiangxufa.net_lib.bean.recommend.MulRecommend;
import com.jiangxufa.net_lib.bean.recommend.Recommend;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2018/7/3
 * 编写人：lenovo
 * 功能描述：
 */

public class RecommendAdapter extends BaseMultiItemQuickAdapter<MulRecommend, BaseViewHolder> {

    public RecommendAdapter() {
        super(new ArrayList<MulRecommend>());
        addItemType(MulRecommend.TYPR_HEADER, R.layout.layout_recommend_banner);
        addItemType(MulRecommend.TYPE_ITEM, R.layout.layout_item_home_recommend_body);
    }

    @Override
    protected void convert(BaseViewHolder holder, MulRecommend item) {
        switch (holder.getItemViewType()) {
            case MulRecommend.TYPR_HEADER:
                Banner bannar = holder.getView(R.id.banner);
                List<Recommend.BannerItemBean> banner_item = item.mBannerItemBean;
                List<String> urls = new ArrayList<>();
                for (int i = 0; i < banner_item.size(); i++) {
                    Recommend.BannerItemBean bean = banner_item.get(i);
                    urls.add(bean.image);
                }
                bannar.setIndicatorGravity(BannerConfig.RIGHT)
                        .setImages(urls)
                        .setImageLoader(new GlideImageLoader())
                        .start();
//                bannar.setOnBannerListener(i -> {
//                    Recommend.BannerItemBean bannerBean = banner_item.get(i);
//                    BrowerActivity.startActivity(mContext, bannerBean.uri, bannerBean.title, bannerBean.image);
//                });
                break;
            case MulRecommend.TYPE_ITEM:
                ImageDisplayUtil.displayRound((ImageView) holder.getView(R.id.iv_video_preview),item.mRecommend.cover);
                holder.setText(R.id.tv_video_play_num, NumberUtils.format(item.mRecommend.play + ""))
                        .setText(R.id.tv_video_time, FormatUtils.formatDuration(item.mRecommend.duration + ""))
                        .setText(R.id.tv_video_danmu, NumberUtils.format(item.mRecommend.danmaku + ""))
                        .setText(R.id.tv_video_title, item.mRecommend.title);
                if (item.mRecommend.open != 0) {
                    //直播
                    holder.setText(R.id.tv_video_tag, item.mRecommend.area);
                } else {
                    //推荐
                    holder.setText(R.id.tv_video_tag, item.mRecommend.tname + " · " + item.mRecommend.tag.tag_name);
                }
//                holder.itemView.setOnClickListener(view ->
//                        mContext.startActivity(new Intent(mContext, VideoDetailActivity.class)));
                break;
        }
    }

    static class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ImageDisplayUtil.displayRound(imageView, (String) path);
        }
    }
}
