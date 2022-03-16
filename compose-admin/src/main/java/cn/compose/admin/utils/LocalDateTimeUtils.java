package cn.compose.admin.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class LocalDateTimeUtils {
    /**
     * 将date转为LocalDateTime后再转为Date
     * 解决时区转换问题
     * @param date
     * @param timeZone
     * @return
     */
    public static Date dateFromTimeZone(Date date,String timeZone){
        if(date == null){
            return null;
        }
        return Date.from(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(timeZone)).toInstant());
    }

}
