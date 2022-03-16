package cn.compose.admin.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.*;

/**
 * 日期时间Utils
 */
public class DateTimeUtils {

    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat HHMMSS_FORMAT = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat YYYYMMDDHHMMSSSSSZ_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat YYYYMMDDHHMMSSZ_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static final SimpleDateFormat YYYYMMDDHHMMSSSSSXXX_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static final SimpleDateFormat YYYY_MM_FORMAT = new SimpleDateFormat("yyyy-MM");


    public static final String YYYYMMDDHHMMSS_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    public static Date parseSyyyyMMdd(String dateStr) {
        try {
            return YYYYMMDD_FORMAT.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseSyyyyMMddHHmmss(String dateTimeStr) {
        try {
            return YYYYMMDDHHMMSS_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseSyyyyMMddHHmmssSSSZ(String dateTimeStr) {
        try {
            return YYYYMMDDHHMMSSSSSZ_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseSyyyyMMddHHmmssSSZ(String dateTimeStr) {
        try {
            return YYYYMMDDHHMMSSZ_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseSyyyyMMddHHmmssSSSXXX(String dateTimeStr) {
        try {
            return YYYYMMDDHHMMSSSSSXXX_FORMAT.parse(dateTimeStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String format2yyyyMMddHHmmss(Date date) {
        return YYYYMMDDHHMMSS_FORMAT.format(date);
    }

    public static String format2yyyyMMddHHmmssSSSZ(Date date) {
        return YYYYMMDDHHMMSSSSSZ_FORMAT.format(date);
    }

    public static String format2yyyyMMdd(Date date) {
        return YYYYMMDD_FORMAT.format(date);
    }


    /**
     * yyyyMMdd
     */
    public static String getCurrentDateStr() {
        return LocalDate.now().format(DEFAULT_DATE_FORMATTER);
    }

    public static String getCurrentDateTimeStr(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentTimeStr(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime parseLocalTime(String timeStr, String pattern) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDateTime(LocalDateTime datetime, String pattern) {
        return datetime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalTime(LocalTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DEFAULT_DATE_FORMATTER);
    }


    /**
     * Period
     */
    public static Period periodBetween(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        return Period.between(startDateInclusive, endDateExclusive);
    }

    /**
     * 日期相隔小时
     */
    public static long durationHours(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toHours();
    }

    /**
     * 日期相隔分钟
     */
    public static long durationMinutes(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMinutes();
    }

    /**
     * 日期相隔毫秒数
     */
    public static long durationMillis(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMillis();
    }


    /**
     * to milli
     */
    public static Long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取当日开始时间
     */
    public static Date getBeginOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当日结束时间
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime, String timeZoneStr) {
        ZoneId zoneId = TimeZone.getTimeZone(timeZoneStr).toZoneId();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return Date.from(instant);
    }

    /**
     * Date转LocalDateTime
     */
    public static String dateToStr(Date date, String formatter) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * String转LocalDateTime
     */
    public static LocalDateTime strToLocalDateTime(String date, String formatter) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter1);
        return localDateTime;
    }

    /**
     * 只支持 年月日时分秒的 字符串时间类型
     */
    public static Date strToDate(String date, String formatter) {
        LocalDateTime localDateTime = strToLocalDateTime(date, formatter);
        return localDateTime2Date(localDateTime);
    }

    /**
     * 根据时区标识，推送用户所属于的时区推断几天后
     * 的日期
     *
     * @param timeZoneStr
     * @param past        推断几天后的日期参数
     * @return
     */
    public static String getLocalDateByTimeZone(String timeZoneStr, long past) {
        if (StringUtils.isEmpty(timeZoneStr)) {
            //当用户没有时区设置得时候默认给与上海东八区
            timeZoneStr = TimeZoneConstant.DEFAULT_ZONE_ID;
        }
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneStr);
        LocalDate localDate = LocalDate.now(timeZone.toZoneId());
        return localDate.plusDays(past).toString();
    }


    /**
     * 根据时区标识，推送用户所属于的时区推断前12个月
     * 的月份
     */
    public static List<String> get12MonthsBefore(String timeZoneStr) {
        List<String> monthList = new ArrayList<>();
        if (StringUtils.isEmpty(timeZoneStr)) {
            //当用户没有时区设置得时候默认给与上海东八区
            timeZoneStr = TimeZoneConstant.DEFAULT_ZONE_ID;
        }
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneStr);
        LocalDate localDate = LocalDate.now(timeZone.toZoneId());
        for (long i = 12L; i >= 1L; i--) {
            monthList.add(localDate.minusMonths(i).toString().substring(0, 7));
        }
        return monthList;
    }

    /**
     * 根据 开始时间和结束时间 得到时间段
     *
     * @param startDateString （yyyy-MM-dd）
     * @param endDateString   （yyyy-MM-dd）
     */
    public static List<String> toDays(String startDateString, String endDateString,SimpleDateFormat format) {
        Date startDate = string2date(startDateString);
        Date endDate = string2date(endDateString);
        int daysNum = differDays(startDate, endDate);
        List<String> days = new ArrayList<>();
        for (int i = 0; i <= daysNum; i++) {
            days.add(date2String(startDate, format));
            startDate = getDateAmount(startDate, Calendar.DATE, 1);
        }
        return days;
    }

    public static Date string2date(String datetimeStr) {
        if (datetimeStr == null || datetimeStr.trim().equals("")) {
            return null;
        }
        try {
            return YYYYMMDD_FORMAT.parse(datetimeStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 时间间隔前后的时间，指定filed
     */
    public static Date getDateAmount(Date date, int filed, int amount) {
        Calendar cal = Calendar.getInstance();
        if (date != null) cal.setTime(date);
        cal.add(filed, amount);
        return cal.getTime();
    }

    public static String date2String(Date date, SimpleDateFormat format) {
        return format.format(date);
    }


    /**
     * date2比date1多的天数
     */
    public static int differDays(Date date1, Date date2) {
        LocalDateTime localDateTime1 = date2LocalDateTime(date1);
        LocalDateTime localDateTime2 = date2LocalDateTime(date2);
        return (int) (localDateTime2.toLocalDate().toEpochDay() - localDateTime1.toLocalDate().toEpochDay());
    }

    public static Date formatStringToDate2(String date, String format) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    public static Date convertTimeStamp2UtcDate(long timeStamp, String timeZoneStr) {
        return convertDate2UtcDate(new Date(timeStamp), timeZoneStr);
    }

    /**
     * default time zone is Asia/Shanghai Utc8
     */
    public static Date convertDate2UtcDate(Date time) {
        Instant instant = time.toInstant().minusMillis(TimeZone.getDefault().getRawOffset());
        return new Date(instant.toEpochMilli());
    }

    public static Date convertDate2UtcDate(Date time, String timeZoneStr) {
        Instant instant = time.toInstant().minusMillis(TimeZone.getTimeZone(timeZoneStr).getRawOffset());
        return new Date(instant.toEpochMilli());
    }

    public static Date convertDate2Date(Date time, String timeZoneStr) {
        ZoneId of = ZoneId.of(timeZoneStr);
        if(of ==null){
            of = ZoneId.of("Asia/Shanghai");
        }
        ZonedDateTime instant = time.toInstant().atZone(of);
        return new Date(instant.toInstant().toEpochMilli());
    }

    public static Date convertUtcDate2Date(Date time, String timeZoneStr) {
        if(time==null){
            return null;
        }

        Instant instant = time.toInstant().plusMillis(TimeZone.getTimeZone(timeZoneStr).getRawOffset());
        return new Date(instant.toEpochMilli());
    }

    /**
     * 根据时区标识，推送用户所属于的时区推断前12个月
     * 的月份
     */
    public static List<String> getMonthsBetween(String startDate,String endDate,String pattern) {
        List<String> monthList = new ArrayList<>();
        LocalDate start = parseLocalDate(startDate,pattern);
        LocalDate end = parseLocalDate(endDate,pattern);
        Period period = periodBetween(start,end);
        int month = period.getMonths();
        for (long i = 0L; i <= month; i++) {
            monthList.add(start.plusMonths(i).toString().substring(0, 7));
        }
        return monthList;
    }

    public static void main(String[] args) {
        List<String> months = getMonthsBetween("2021-09-12","2021-10-23","yyy-MM-dd");
        System.out.println(months);
        String gateOld = "080000000100\n" +
                "08000000035\n" +
                "08000000035\n" +
                "08000000111\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001671\n" +
                "08000001710\n" +
                "08000001786\n" +
                "08000001786\n" +
                "08000001786\n" +
                "08000001786\n" +
                "08000001786\n" +
                "08000001786\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000001789\n" +
                "08000045678\n" +
                "08000045678\n" +
                "08001111234\n" +
                "15382000000\n";

        String deviceCode ="39ADPMFGWSW1\n" +
                "33OHOH8D9TS1\n" +
                "33OHOKJ5W5S1\n" +
                "32RT8MXIHBK1\n" +
                "5AUE1UAQHTS7\n" +
                "5B19Y5QM14W7\n" +
                "5AUBJEPW65S7\n" +
                "5AUBKQ14EF47\n" +
                "5AU984LKGY87\n" +
                "5AUA2009W7K7\n" +
                "5AXQ2FNPXLC7\n" +
                "4A0K2O0VPAO7\n" +
                "5AUALEDLCCW7\n" +
                "5AUBJDZKLI87\n" +
                "49X63B6XMPS8\n" +
                "5AUA188JVS07\n" +
                "4A0K3NL5DOW7\n" +
                "5AUCMFVF4R47\n" +
                "5AXQ22RQEFK7\n" +
                "5AUAFWYGNB47\n" +
                "5AUBGRRFTSW7\n" +
                "5AUBJIEIOVK7\n" +
                "5AU9B01FYXC7\n" +
                "5AUBKQ3LCKW7\n" +
                "5AUBGQT3BWW7\n" +
                "5AUBJEY1DPS7\n" +
                "5AU995VTSUO7\n" +
                "5AUAE6VC6MO7\n" +
                "5EL3LDHZ21S7\n" +
                "5K2ZKNI47R47\n" +
                "5K6XLPGQ51C8\n" +
                "5K6YFX6GUOG8\n" +
                "5K2X0F5824G8\n" +
                "5K2Y153KSW07\n" +
                "5K6XF8MVQO07\n" +
                "5K2VXN24FDC8\n" +
                "5K2VXTLNAKG8\n" +
                "5K2W0Y62FAO8\n" +
                "5K2W4Q2DR407\n" +
                "5K2XEWE31DS8\n" +
                "5K2Y203ZCGG7\n" +
                "5K6XOU3S7Z48\n" +
                "5K72LBW0W6O8\n" +
                "5K2VTKPJSDS8\n" +
                "5K2VXT4UFVK8\n" +
                "5K2W0X9DIGW8\n" +
                "5KAGO0SL18W7\n" +
                "5K2VXOA9PVK8\n" +
                "5K2VZ7BZO288\n" +
                "5K2W1DASJSW7\n" +
                "5K73CRUYHK08\n" +
                "5K73GYNDSR47\n" +
                "5KA1O2NQZ407\n" +
                "5K2VXO20FPS8\n" +
                "5K2W4YV0PRK7\n" +
                "5K2YNYR4HHC7\n" +
                "5KAGX614ZQO8\n" +
                "5K73D17IEE88\n" +
                "5K2W1ALYLZ47\n" +
                "5K6Y8PWBM7K7\n" +
                "5K767GMQNCG8\n" +
                "5K3MVNPPHSG7\n" +
                "5LYUBB4BKLC7\n" +
                "32ZFFF00IN41\n";
        String[] gatewayOldArray = gateOld.split("\n");
        String[] deviceCodeOldArray = deviceCode.split("\n");
        String format = "update device_associated set gateway_id ='%s',inner_gateway_id =(select id from gateway where gateway_no ='%s') where device_code='%s';";
        for (int i = 0; i < gatewayOldArray.length; i++) {
            System.out.println(String.format(format,gatewayOldArray[i],gatewayOldArray[i],deviceCodeOldArray[i]));
        }

    }
}
