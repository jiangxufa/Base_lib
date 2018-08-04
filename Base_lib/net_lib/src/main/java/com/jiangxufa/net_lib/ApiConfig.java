package com.jiangxufa.net_lib;


/*
 * 创建时间：2018/1/6
 * 编写人：lenovo
 * 功能描述：
 */
public class ApiConfig {

    /**
     * 获取飞修域名
     *
     * @return 主机名
     */
    public static String getHost() {
        return "";
    }

    /**
     * 获得门店端url
     *
     * @return api
     */
    public static String getStoreApiUrl() {
        return "https://" + getHost() + "/api/fr/v3/demander/";
    }

    /**
     * 获得维修工端url
     *
     * @return api
     */
    public static String getFettlerApiUrl() {
        return "https://" + getHost() + "/api/fr/v3/provider/";
    }

    /**
     * 获得通用的url
     *
     * @return api
     */
    public static String getCommonApiUrl() {
        return "https://" + getHost() + "/api/fr/v3/common/";
    }


    /**
     * 获得token
     *
     * @return api
     */
    public static String getToken() {
        return "";
    }

    public static String getVersionCode() {
        return "";
    }

    public static String getTerminalType() {
        return "";
    }

    public static String getAppType() {
        return "";
    }

    public static String getUserType() {
        return "";
    }

    public static String getUniqid() {
        return "";
    }


}
