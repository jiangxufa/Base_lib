package com.ifeixiu.image_lib;

import android.app.Application;

/**
 * 创建时间：2018/5/10
 * 编写人：lenovo
 * 功能描述：
 */

public class ImageLibInit {

    public static Application application;

    public static void init(Application app) {
        application = app;
    }

}
