package com.yuan.common.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author joryun ON 2017/10/22.
 * <p>
 * Json工具类，实现了实体类和Json数据格式之间的互转功能
 */
public class JsonUtils {

    // jackson包提供的json与javabean的转换类ObjectMapper
    private static ObjectMapper objectMapper;

    // 初始化对象objectMapper,sdf
    static {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
    }


    /*
     * 实现功能--将javabean对象转换为json对象的字符串
     *
     * @param obj--要转换的javabean对象
     *
     * @return String类型--json对象的字符串
     */
    public static String object2json(Object obj) {

        // 变量json--返回用的json对象的字符串
        String json = null;

        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /*
     * 实现功能--将json对象的字符串转换为javabean对象
     *
     * @param json--要转换的json对象的字符串
     *
     * @param collectionClass--原始类的类对象
     *
     * @param elementClasses--泛型类的类对象
     *
     * @return T类型--期望的javabean对象(T类型)，调用后需要结合具体类型进行强制转换
     */
    public static <T> T json2object(String json, Class<?> collectionClass,
                                    Class<?>... elementClasses) {

        // 变量object--返回用的javabean对象
        T t = null;
        JavaType javaType = getCollectionType(collectionClass, elementClasses);
        try {
            t = objectMapper.readValue(json, javaType);
        } catch (JsonParseException e) {
            System.out.println("转换异常");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("json映射异常");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("io异常");
            e.printStackTrace();
        }

        return t;
    }

    /*
     * 实现功能--将原始类的类对象与泛型类的类对象通过objectMapper绑定为jackson包提供的JavaType对象
     *
     * @param collectionClass--原始类的类对象
     *
     * @param elementClasses--泛型类的类对象
     *
     * @return JavaType类型--绑定后的JavaType对象
     */
    public static JavaType getCollectionType(Class<?> collectionClass,
                                             Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(
                collectionClass, elementClasses);
    }

    public static void main(String[] args) {
        String str = "{ \n" +
                "\"openid\":\"OPENID\",\n" +
                "\"nickname\":\"NICKNAME\",\n" +
                "\"sex\":1,\n" +
                "\"province\":\"PROVINCE\",\n" +
                "\"city\":\"CITY\",\n" +
                "\"country\":\"COUNTRY\",\n" +
                "\"headimgurl\": \"http://wx.qlogo" +
                ".cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0\",\n" +
                "\"privilege\":[\n" +
                "\"PRIVILEGE1\", \n" +
                "\"PRIVILEGE2\"\n" +
                "],\n" +
                "\"unionid\": \" o6_bmasdasdsad6_2sgVt7hMZOPfL\"\n" +
                "\n" +
                "}";
        Map<String, Object> map = json2object(str, Map.class, String.class, Object.class);
        System.out.println(((ArrayList) map.get("privilege")).get(0));
    }
}