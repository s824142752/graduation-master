package com.song.core.system;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统设置
 */
public class SystemConfig {
    // 管理系统相关配置
    public final static String GRADU_WEB_NAME = "gradu_mall_name";
    public final static String GRADU_WEB_ADDRESS = "gradu_mall_address";
    public final static String GRADU_WEB_PHONE = "gradu_mall_phone";
    public final static String GRADU_WEB_QQ = "gradu_mall_qq";
    public final static String GRADU_WEB_LONGITUDE = "gradu_mall_longitude";
    public final static String GRADU_WEB_Latitude = "gradu_mall_latitude";

    //所有的配置均保存在该 HashMap 中
    private static Map<String, String> SYSTEM_CONFIGS = new HashMap<>();

    private static String getConfig(String keyName) {
        return SYSTEM_CONFIGS.get(keyName);
    }

    private static Integer getConfigInt(String keyName) {
        return Integer.parseInt(SYSTEM_CONFIGS.get(keyName));
    }

    private static Boolean getConfigBoolean(String keyName) {
        return Boolean.valueOf(SYSTEM_CONFIGS.get(keyName));
    }

    private static BigDecimal getConfigBigDec(String keyName) {
        return new BigDecimal(SYSTEM_CONFIGS.get(keyName));
    }

    public static String getMallName() {
        return getConfig(GRADU_WEB_NAME);
    }

    public static String getMallAddress() {
        return getConfig(GRADU_WEB_ADDRESS);
    }

    public static String getMallPhone() {
        return getConfig(GRADU_WEB_PHONE);
    }

    public static String getMallQQ() {
        return getConfig(GRADU_WEB_QQ);
    }

    public static String getMallLongitude() {
        return getConfig(GRADU_WEB_LONGITUDE);
    }

    public static String getMallLatitude() {
        return getConfig(GRADU_WEB_Latitude);
    }

    public static void setConfigs(Map<String, String> configs) {
        SYSTEM_CONFIGS = configs;
    }

    public static void updateConfigs(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            SYSTEM_CONFIGS.put(entry.getKey(), entry.getValue());
        }
    }
}