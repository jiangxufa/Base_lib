package com.ifeixiu.gaode_lib;

import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;

import java.text.DecimalFormat;

/**
 * 创建时间：2018/5/4
 * 编写人：lenovo
 * 功能描述：
 */

public class GaodeUtils {

    public static final String Kilometer = "\u516c\u91cc";// "公里";
    public static final String Meter = "\u7c73";// "米";

//    /**
//     * @param context
//     * @param startPoint 起点的经纬度  LatLonPoint mStartPoint = new LatLonPoint(39.997361,  116.478346);//起点，116.335891,39.942295
//     * @param endPoint   终点的经纬度 LatLonPoint mEndPoint = new LatLonPoint(39.966577, 116.3246);//终点，116.481288,39.995576
//     * @return 字符串数组  string[0]表示 两点的路程(智能显示类型 超过一公里 显示**公里 否则显示**米)
//     * string[1]表示 驾车所需的时间(只能显示类型 大于1小时显示**小时**分**秒)
//     * <p>
//     * 使用说明 注意处理异常
//     * LatLonPoint mStartPoint = new LatLonPoint(39.997361,  116.478346);//起点，116.335891,39.942295
//     * LatLonPoint mEndPoint = new LatLonPoint(39.966577, 116.3246);//终点，116.481288,39.995576
//     * GaodeUtils.caculateDistance(this,mStartPoint,mEndPoint)
//     * .subscribe(new Action1<String[]>() {
//     * @Override public void call(String[] integers) {
//     * if (integers.length > 0) {
//     * ToastUtil.showToast("距离为" + integers[0] + " 时间为：" + integers[1]);
//     * }
//     * }
//     * }, new Action1<Throwable>() {
//     * @Override public void call(Throwable throwable) {
//     * ToastUtil.showToast(throwable.toString());
//     * }
//     * });
//     */
//    public static Observable<String[]> caculateDistance(Context context, final LatLonPoint startPoint, final LatLonPoint endPoint) {
//        final RouteSearch mRouteSearch = new RouteSearch(context);
//
//        return Observable.create(new Observable.OnSubscribe<String[]>() {
//            @Override
//            public void call(final Subscriber<? super String[]> subscriber) {
//                RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
//                        startPoint, endPoint);
//                // 驾车路径规划
//                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault, null,
//                        null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
//                mRouteSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
//                mRouteSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
//                    @Override
//                    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
//
//                    }
//
//                    @Override
//                    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int errorCode) {
//                        if (!subscriber.isUnsubscribed()) {
//                            if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
//                                if (driveRouteResult != null && driveRouteResult.getPaths() != null) {
//                                    DrivePath drivePath = driveRouteResult.getPaths()
//                                            .get(0);
//                                    int dis = (int) drivePath.getDistance();
//                                    int dur = (int) drivePath.getDuration();
//                                    subscriber.onNext(new String[]{getFriendlyLength(dis), getFriendlyTime(dur)});
//                                    subscriber.onCompleted();
//                                } else if (driveRouteResult != null && driveRouteResult.getPaths() == null) {
//                                    subscriber.onNext(new String[]{});
//                                    subscriber.onCompleted();
//                                }
//                            } else {
//                                subscriber.onError(new Exception("高德错误代码为:" + errorCode + ""));
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
//
//                    }
//
//                    @Override
//                    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
//
//                    }
//                });
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//    }

    public static float calculateLineDistance(LatLng var0, LatLng var1) {

        return AMapUtils.calculateLineDistance(var0,var1);
    }

    public static String getFriendlyTime(int second) {
        if (second > 3600) {
            int hour = second / 3600;
            int miniate = (second % 3600) / 60;
            return hour + "小时" + miniate + "分钟";
        }
        if (second >= 60) {
            int miniate = second / 60;
            return miniate + "分钟";
        }
        return second + "秒";
    }

    public static String getFriendlyLength(int lenMeter) {
        if (lenMeter > 10000) // 10 km
        {
            int dis = lenMeter / 1000;
            return dis + Kilometer;
        }

        if (lenMeter > 1000) {
            float dis = (float) lenMeter / 1000;
            DecimalFormat fnum = new DecimalFormat("##0.0");
            String dstr = fnum.format(dis);
            return dstr + Kilometer;
        }

        if (lenMeter > 100) {
            int dis = lenMeter / 50 * 50;
            return dis + Meter;
        }

        int dis = lenMeter / 10 * 10;
        if (dis == 0) {
            dis = 10;
        }

        return dis + Meter;
    }

}
