package cn.compose.admin.utils;

import java.util.TimeZone;


public class CommonUtils {

    //获取当前系统时区
    public static String getDefaultTimeZone() {
        return TimeZone.getDefault().getID();
    }
}
