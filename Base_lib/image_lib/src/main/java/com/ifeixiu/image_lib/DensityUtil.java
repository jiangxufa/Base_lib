package com.ifeixiu.image_lib;

import java.lang.reflect.Field;

/**
 * 显示单位长度转换方法集
 *
 * @author: Ocean7
 * @date: 2017-02-17
 */
class DensityUtil {

    public static int getHeight() {
        return ImageLibInit.application.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getWidth() {
        return ImageLibInit.application.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getPxPerDP() {
        return dip2px(1.0f);
    }

    public static int getActBarHeight() {
//		if (Build.VERSION.SDK_INT >= 21) {
//			return DensityUtil.dip2px(48)+getStatusBarHeight();
//		}
        return DensityUtil.dip2px(48);
    }

    public static int px2dip(float px) {
        final float scale = ImageLibInit.application.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = ImageLibInit.application.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


    public static int dip2px(float dpValue) {
//        Log.e("TAG", "1111===" + F.AppContext); //null
        final float scale = ImageLibInit.application.getResources().getDisplayMetrics().density;
        int r = (int) (dpValue * scale + 0.5f);
        return r;
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(float pxValue) {
        final float fontScale = ImageLibInit.application.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(float spValue) {
        final float fontScale = ImageLibInit.application.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


}

