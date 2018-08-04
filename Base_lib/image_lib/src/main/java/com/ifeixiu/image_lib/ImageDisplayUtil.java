package com.ifeixiu.image_lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * 图片显示方法集
 *
 * @author: Ocean7
 * @date: 2017-02-17
 */
public class ImageDisplayUtil {

    private static ImageLoader newImageLoader;

    public static ImageLoader getImageLoader(Context ctx) {
        if (newImageLoader == null) {
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ctx)
                    // .memoryCacheExtraOptions(widthPixels/2, heightPixels/3)
                    // // max width, max height，即保存的每个缓存文件的最大长宽
                    .threadPoolSize(3)
                    // 线程池内加载的数量
                    .threadPriority(Thread.NORM_PRIORITY - 2)
                    .denyCacheImageMultipleSizesInMemory()
                    // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 *
                    // 1024))
                    // .memoryCacheSize(2 * 1024 * 1024)
                    .tasksProcessingOrder(QueueProcessingType.LIFO).defaultDisplayImageOptions(DisplayImageOptions.createSimple()).imageDownloader(new BaseImageDownloader(ctx, 5 * 1000, 30 * 1000))
					//.writeDebugLogs()
                    .build();// 开始构建
            ImageLoader.getInstance().init(config);// 全局初始化此配置
            newImageLoader = ImageLoader.getInstance();
        }
        return newImageLoader;
    }

    /**
     * 特征：方形，小
     * 场景：本地选择图片
     */
    public static void displaySquareSmall(ImageView imageView, String url) {
        getImageLoader(imageView.getContext())
                .displayImage(
                        url,
                        imageView,
                        ImageLoaderOption.getDioSquareSmall());
    }


    /**
     * 特征：方形,带进度,透明
     * 场景：大图浏览
     */
    public static void displaySquareProgressTransparent(final ProgressImageView2 imageView, String url) {
        getImageLoader(imageView.getContext())
                .displayImage(
                        url,
                        imageView,
                        ImageLoaderOption.getDioSquareTransparent(),
                        new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                super.onLoadingComplete(imageUri, view, loadedImage);
                                imageView.setProgress(0, 0);
                            }
                        }, new ImageLoadingProgressListener() {
                            @Override
                            public void onProgressUpdate(String s, View view, int i, int i1) {
                                imageView.setProgress(i, i1);

                            }
                        });
    }

    /**
     * 特征：方形,带进度,透明
     * 场景：大图浏览
     */
    public static void displaySquareProgress99Transparent(final ProgressImageView2 imageView, String url) {
        getImageLoader(imageView.getContext())
                .displayImage(
                        url,
                        imageView,
                        ImageLoaderOption.getDioSquare99Transparent(),
                        new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                super.onLoadingComplete(imageUri, view, loadedImage);
                                imageView.setProgress(0, 0);
                            }
                        }, new ImageLoadingProgressListener() {
                            @Override
                            public void onProgressUpdate(String s, View view, int i, int i1) {
                                imageView.setProgress(i, i1);
                            }
                        });
    }


    /**
     * 特征：方形,带进度
     * 场景：大图浏览
     */
    public static void displaySquareProgress(final ProgressImageView2 imageView, String url) {
        getImageLoader(imageView.getContext())
                .displayImage(
                        url,
                        imageView,
                        ImageLoaderOption.getDioSquare(),
                        new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                super.onLoadingComplete(imageUri, view, loadedImage);
                                imageView.setProgress(0, 0);
                            }
                        }, new ImageLoadingProgressListener() {
                            @Override
                            public void onProgressUpdate(String s, View view, int i, int i1) {
                                imageView.setProgress(i, i1);
                            }
                        });
    }


    /**
     * 特征：带进度，圆角矩形,动态调整大小
     * 场景：聊天气泡
     */
    public static void displayProgressResize(final ProgressImageView2 imageView, String url, final double maxHeight, final double maxWidth) {
        getImageLoader(imageView.getContext()).displayImage(
                url,
                imageView,
                ImageLoaderOption.getDioSquare(),
                new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {

                        ViewGroup.LayoutParams lp = view.getLayoutParams();
                        double picWidth = bitmap.getWidth();
                        double picHeight = bitmap.getHeight();
                        double mh_mw = maxHeight / maxWidth;
                        double ph_pw = picHeight / picWidth;

                        if (ph_pw > mh_mw*2) {
                            ph_pw = mh_mw*2;
                        } else if (ph_pw < mh_mw*0.5) {
                            ph_pw = mh_mw*0.5;
                        }

                        //如果图片高宽比 大于 控件高宽比
                        //

                        if (ph_pw > mh_mw) {
                            lp.height = (int) maxHeight;
                            lp.width = (int) (maxHeight/ph_pw);
                        } else {
                            lp.width = (int) maxWidth;
                            lp.height = (int) (maxWidth*ph_pw);
                        }
                        view.setLayoutParams(lp);
                        view.invalidate();

                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
    }


    /**
     * 特征：方形,透明
     * 场景：大图浏览
     */
    public static void displaySquareTransparent(final ImageView imageView, String url) {
        getImageLoader(imageView.getContext())
                .displayImage(
                        url,
                        imageView,
                        ImageLoaderOption.getDioSquareTransparent());
    }


    /**
     * 特征：带进度，圆角矩形,动态调整大小
     * 场景：聊天气泡
     */
    public static void displayRoundProgressResize(ProgressImageView2 imageView, String url) {
        getImageLoader(imageView.getContext()).displayImage(
                url,
                imageView,
                ImageLoaderOption.getDioRound(),
                new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {

                        ViewGroup.LayoutParams lp = view.getLayoutParams();
                        double picWidth = bitmap.getWidth();
                        double picHeight = bitmap.getHeight();
                        double maxHeight = DensityUtil.dip2px(100);
                        double maxWidth = DensityUtil.getWidth() - DensityUtil.dip2px(124);
                        double mh_mw = maxHeight / maxWidth;
                        double ph_pw = picHeight / picWidth;

                        if (ph_pw > 2)
                            ph_pw = 2;
                        else if (ph_pw < 0.5)
                            ph_pw = 0.5;

                        if (ph_pw > mh_mw) {
                            double beishu = maxHeight / picHeight;
                            lp.height = (int) maxHeight;
                            lp.width = (int) (picWidth * beishu);
                        } else {
                            double beishu = maxWidth / picWidth;
                            lp.width = (int) maxWidth;
                            lp.height = (int) (picHeight * beishu);
                        }
                        view.setLayoutParams(lp);
                        view.invalidate();

                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
    }


    /**
     * 特征：不带进度，圆形
     * 场景：用户头像，圈子头像等
     */
    public static void displayCircle(ImageView imageView, String url) {
        getImageLoader(imageView.getContext()).displayImage(
                url,
                imageView,
                ImageLoaderOption.getDioCircle());
    }

    /**
     * 特征：不带进度，圆角矩形
     */
    public static void displayRound(ImageView imageView, String url) {
        getImageLoader(imageView.getContext()).displayImage(
                url,
                imageView,
                ImageLoaderOption.getDioRound());
    }
}

