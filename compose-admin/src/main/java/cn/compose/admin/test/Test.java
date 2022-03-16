package cn.compose.admin.test;

import cn.compose.admin.utils.DateTimeUtils;
import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) {
        Map<String,String> deviceMap =new ConcurrentHashMap();
        deviceMap.put("132","23434");
        Map<String,String> map=new HashMap();
        map.putAll(deviceMap);
        deviceMap.clear();

        map.entrySet().forEach(entry->{
            System.err.println("key:"+ entry.getKey()+"value:"+entry.getValue());
        });
        map.put("test","33434");

        System.err.println(JSON.toJSONString(map));
        System.err.println(JSON.toJSONString(deviceMap));
//
//        SimpleDateFormat sdf8=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.err.println("JDK时区："+TimeZone.getDefault().getID());
//        System.err.println("JDK时区时间："+DateTimeUtils.convertTimeStamp2UtcDate(new Date().getTime(),TimeZone.getDefault().getID()));
//        System.err.println("零时区时间："+DateTimeUtils.convertTimeStamp2UtcDate(new Date().getTime(),TimeZone.getTimeZone("Etc/GMT+0").getID()));
//        Date faultTime = DateTimeUtils.convertTimeStamp2UtcDate(new Date().getTime(),TimeZone.getTimeZone("Africa/Johannesburg").getID());
//        Date dateUser = DateTimeUtils.convertUtcDate2Date(faultTime, "Asia/Shanghai");
//        System.err.println("使用工具类零时区转为东八区时间："+dateUser);
//        System.err.println(sdf8.format(faultTime));
//        sdf8.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));//设置时区为东八区
//        System.err.println("不适用工具类转换为东八区时间："+sdf8.format(faultTime));

    }
}
