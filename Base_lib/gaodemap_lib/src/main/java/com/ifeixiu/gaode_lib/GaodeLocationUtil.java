package com.ifeixiu.gaode_lib;

import android.content.Context;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.support.v4.app.AppOpsManagerCompat;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * 创建时间：2018/6/20
 * 编写人：lenovo
 * 功能描述：
 */

@SuppressWarnings("ALL")
public class GaodeLocationUtil {

    private static final String TAG = "LocationUtil";

    private static class LocationUtilHolder {
        private static final GaodeLocationUtil INSTANCE = new GaodeLocationUtil();
    }

    public static GaodeLocationUtil getInstance() {
        return LocationUtilHolder.INSTANCE;
    }

    private boolean gpgNotResponse = true;
    /**
     * 缓存为位置信息
     **/
    private CacheLocation cacheLocation;

    // 缓存位置的有效时间 默认60s
    private long validFactor = 60 * 1000 * 30;

    /**
     * 判断是否开启了定位服务
     *
     * @return true表示开启了，false没有
     */
    public static boolean isOpenGps() {
        LocationManager locaionManager = (LocationManager) Utils.getApp().getSystemService(Context.LOCATION_SERVICE);
        boolean netEnable = locaionManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean gpsEnable = locaionManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return netEnable || gpsEnable;
    }

    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            stopLocation();
            Log.d(TAG, "onLocationChanged: " + Thread.currentThread().getName());
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    if (cacheLocation == null) {
                        cacheLocation = new CacheLocation(amapLocation);
                    } else {
                        cacheLocation.setLocation(amapLocation);
                    }
                    notifyLocationSucess(amapLocation);

                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                    Log.e("AmapErr", errText);
                    if (amapLocation.getErrorCode() >= 12) {

                        if (observers != null) {
                            for (LocationObserver observer : observers) {
                                observer.onLocationFail(amapLocation);
                            }
                        }

                        //表示没有权限
                        ToastUtil.showerror(Utils.getApp(), amapLocation.getErrorCode());
                    }
                }
            }
        }
    };

    private void notifyLocationSucess(AMapLocation amapLocation) {
        if (observers != null) {
            for (LocationObserver observer : observers) {
                observer.onLocationSucess(amapLocation);
            }
        }
    }


    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private GaodeLocationUtil() {
        if (mLocationClient == null) {
            //初始化定位
            mLocationClient = new AMapLocationClient(Utils.getApp());

            //设置定位回调监听
            mLocationClient.setLocationListener(mLocationListener);

            //初始化AMapLocationClientOption对象
            mLocationOption = new AMapLocationClientOption();

            //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //获取一次定位结果：
            mLocationOption.setOnceLocation(true);
            //该方法默认为false。
            mLocationOption.setOnceLocation(true);
            //
            //获取最近3s内精度最高的一次定位结果
            //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
            mLocationOption.setOnceLocationLatest(true);

            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);

            //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
            mLocationOption.setHttpTimeOut(20000);

            //关闭缓存机制
            mLocationOption.setLocationCacheEnable(true);

            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
        }
    }

    public void startLocation() {
        if (!isOpenGps()) {
            ToastUtils.showLong(R.string.gaode_without_location_service);
            return;
        }
        decideStrategies();
    }


    public void stopLocation() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        }
    }

    public void destoryLocation() {
        if (mLocationClient != null) {
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }

    /**
     * 决定使用缓存还是直接定位
     */
    public void decideStrategies() {
        if (cacheLocation == null || !cacheLocation.isLocationValid(validFactor)) {
            //启动定位
            if (mLocationClient != null) {
                mLocationClient.startLocation();
            }
        } else {
            if (cacheLocation.isLocationInfoValid()) {
                notifyLocationSucess(cacheLocation.location);
            } else {
                //启动定位
                if (mLocationClient != null) {
                    mLocationClient.startLocation();
                }
            }
        }
    }

    private LinkedList<LocationObserver> observers;

    public void registerLocationObserver(LocationObserver observer) {
        if (observers == null) {
            observers = new LinkedList<>();
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unregisterLocationObserver(LocationObserver observer) {
        if (observers == null) {
           return;
        }
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    interface LocationObserver {

        void onLocationSucess(AMapLocation amapLocation);

        void onLocationFail(AMapLocation amapLocation);

    }

    private class CacheLocation {
        private long lastLocationTime = -1;
        private AMapLocation location;

        public CacheLocation(AMapLocation location) {
            this.location = location;
            lastLocationTime = System.currentTimeMillis();
        }

        public boolean isLocationInfoValid() {
            if (location == null) return false;
            return !(location.getLongitude() == 0 && location.getLatitude() == 0);
        }

        public AMapLocation getLocation() {
            return location;
        }

        public void setLocation(AMapLocation location) {
            this.location = location;
        }

        /**
         * 时间小于有效时间表示可以服用
         * @param validFactor
         * @return true表示有效 false表示无效
         */
        public boolean isLocationValid(long validFactor) {
            return System.currentTimeMillis() - lastLocationTime < validFactor;
        }
    }

    /**
     * 检查权限列表
     *
     * @param context
     * @param op       这个值被hide了，去AppOpsManager类源码找，如位置权限  AppOpsManager.OP_GPS==2
     * @param opString 如判断定位权限 AppOpsManager.OPSTR_FINE_LOCATION
     * @return @see 如果返回值 AppOpsManagerCompat.MODE_IGNORED 表示被禁用了
     */
    public static int checkOp(Context context, int op, String opString) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            Object object = context.getSystemService(Context.APP_OPS_SERVICE);
            Class c = object.getClass();
            try {
                Class[] cArg = new Class[3];
                cArg[0] = int.class;
                cArg[1] = int.class;
                cArg[2] = String.class;
                Method lMethod = c.getDeclaredMethod("checkOp", cArg);
                return (Integer) lMethod.invoke(object, op, Binder.getCallingUid(), context.getPackageName());
            } catch (Exception e) {
                e.printStackTrace();
                if (Build.VERSION.SDK_INT >= 23) {
                    return AppOpsManagerCompat.noteOp(context, opString, context.getApplicationInfo().uid,
                            context.getPackageName());
                }

            }
        }
        return -1;
    }

}
