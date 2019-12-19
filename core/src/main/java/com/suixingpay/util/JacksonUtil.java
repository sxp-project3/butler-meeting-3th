package com.suixingpay.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 封装常用的 Jackson 序列化方法
 * <p>
 * Created by Jalr4ever on 2019/12/19.
 */
public class JacksonUtil {


    /**
     * Jackson 转化器
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * 日期格式
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    /**
     * 把对象序列化成字符串
     *
     * @param obj 对象
     * @return
     * @throws JsonProcessingException
     */
    public static String objToString(Object obj) throws JsonProcessingException {
        return MAPPER.writeValueAsString(obj);
    }

    /**
     * 把 Map<String, Object> 序列化成 Bean
     *
     * @param map   map
     * @param clazz 相应的类型
     * @return 返回一个 Bean
     * @throws IOException
     */
    public static Object mapToBean(Map<String, Object> map, Class clazz) throws IOException {
        return MAPPER.readValue(objToString(map), clazz);
    }

    /**
     * 把 Bean 序列化成 Map
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static Map beanToMap(Object obj) throws IOException {
        return MAPPER.readValue(objToString(obj), Map.class);
    }

    /**
     * 把 Date 序列化成 String
     *
     * @param date
     * @return
     */
    public static String dateToString(Object date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }


}
