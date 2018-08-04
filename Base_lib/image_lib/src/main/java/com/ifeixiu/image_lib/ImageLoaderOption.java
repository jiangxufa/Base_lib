package com.ifeixiu.image_lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;


public class ImageLoaderOption {

    private static DisplayImageOptions dioCircle;
    private static DisplayImageOptions dioSquare;
    private static DisplayImageOptions dioSquareTransparent;
    private static DisplayImageOptions dioSquare99Transparent;
    private static DisplayImageOptions dioSquareSmall;
    private static DisplayImageOptions dioRound;


    /**
     * 圆形
     * 灰色背景
     */
    public static DisplayImageOptions getDioCircle() {
        if (dioCircle == null) {
            dioCircle = new DisplayImageOptions.Builder()
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .showImageOnLoading(R.drawable.img_default_head/*shape_circle_grey4*/) //设置图片在下载期间显示的图片
//                    .showImageForEmptyUri(R.drawable.icon_blogdetial_network_error)//设置图片Uri为空或是错误的时候显示的图片
//                    .showImageForEmptyUri(R.drawable.img_default_head)
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                    .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                    .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                    .displayer(new RoundedBitmapDisplayer(1000))//是否设置为圆角，弧度为多少
                    .build();//构建完成
        }
        return dioCircle;
    }

    /**
     * 圆角矩形
     * 灰色背景
     */
    public static DisplayImageOptions getDioRound() {
        if (dioRound == null) {
            dioRound = new DisplayImageOptions.Builder()
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .showImageOnLoading(R.color.image_lib_alpha_0x20_black) //设置图片在下载期间显示的图片
//                    .showImageForEmptyUri(R.drawable.icon_blogdetial_network_error)//设置图片Uri为空或是错误的时候显示的图片
                    .cacheOnDisk(false)
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                    .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                    .displayer(new RoundedBitmapDisplayer(4))//是否设置为圆角，弧度为多少
                    .build();//构建完成
        }
        return dioRound;
    }

    /**
     * 方形
     * 灰色背景
     */
    public static DisplayImageOptions getDioSquare() {
        if (dioSquare == null) {
            dioSquare = new DisplayImageOptions.Builder()
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .showImageOnLoading(R.color.image_lib_alpha_0x20_black) //设置图片在下载期间显示的图片
//                    .showImageForEmptyUri(R.drawable.icon_blogdetial_network_error)//设置图片Uri为空或是错误的时候显示的图片
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                    .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                    .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                    .build();//构建完成
        }
        return dioSquare;
    }

    /**
     * 方形
     * 透明
     */
    public static DisplayImageOptions getDioSquareTransparent() {
        if (dioSquareTransparent == null) {
            dioSquareTransparent = new DisplayImageOptions.Builder()
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .showImageOnLoading(R.color.image_lib_alpha_0x00) //设置图片在下载期间显示的图片
//                    .showImageForEmptyUri(R.drawable.icon_blogdetial_network_error)//设置图片Uri为空或是错误的时候显示的图片
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                    .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                    .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                    .build();//构建完成
        }
        return dioSquareTransparent;
    }


    /**
     * 方形
     * 透明
     */
    public static DisplayImageOptions getDioSquare99Transparent() {
        if (dioSquareTransparent == null) {
            dioSquare99Transparent = new DisplayImageOptions.Builder()
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .showImageOnLoading(R.color.image_lib_alpha_0x05_black) //设置图片在下载期间显示的图片
//                    .showImageForEmptyUri(R.drawable.icon_blogdetial_network_error)//设置图片Uri为空或是错误的时候显示的图片
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                    .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                    .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                    .build();//构建完成
        }
        return dioSquareTransparent;
    }


    /**
     * 小方形
     * 灰色背景
     */
    public static DisplayImageOptions getDioSquareSmall() {
        if (dioSquareSmall == null) {
            Options options = new Options();
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inSampleSize = 32;
            dioSquareSmall = new DisplayImageOptions.Builder()
                    .decodingOptions(options)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .showImageOnLoading(R.color.image_lib_alpha_0x05_black) //设置图片在下载期间显示的图片
//                    .showImageForEmptyUri(R.drawable.icon_blogdetial_network_error)//设置图片Uri为空或是错误的时候显示的图片
                    .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                    .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                    .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                    .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                    .build();//构建完成
        }
        return dioSquareSmall;
    }

}
