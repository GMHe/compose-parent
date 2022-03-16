package cn.compose.admin.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期序列化工具,直接将时间类型的转为yyyy-MM-dd类型的数据
 */
@Configuration
public class JsonZoneTimeSerialize extends JsonSerializer<Date> {

    //定义日期格式
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    private SimpleDateFormat formatTime = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa", Locale.ENGLISH);
    private String timeZone = ZoneId.systemDefault().getId();

    public void setTimeZone(String timeZone){
        this.timeZone = timeZone;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(sdf.format(date));
        //        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Lisbon"));
//        calendar.setTime(date);
//        StringBuilder str=new StringBuilder();
//        str.append(calendar.get(Calendar.YEAR) + "-")
//                .append(calendar.get(Calendar.MONTH) + "-" )
//                .append(calendar.get(Calendar.DAY_OF_MONTH) + "  ")
//                .append(calendar.get(Calendar.HOUR_OF_DAY) + ":")
//                .append(calendar.get(Calendar.MINUTE) + ":")
//                .append(calendar.get(Calendar.SECOND));
//        System.err.println(str.toString());
        jsonGenerator.writeString(sdf.format(date));
    }

}
