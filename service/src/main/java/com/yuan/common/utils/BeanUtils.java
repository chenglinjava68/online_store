package com.yuan.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class BeanUtils extends org.springframework.beans.BeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    public static <F, T> T copy(F from, Class<T> toClass) {
        final T to;
        try {
            to = toClass.newInstance();
        } catch (Exception e) {
            logger.warn("", e);
            return null;
        }
        copyProperties(from, to);
        return to;
    }

    public static void copyNonNullProperties(Object src, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String createGetMethodNameByFieldName(String fieldName){
        StringBuilder stringBuilder = new StringBuilder(5);
        stringBuilder.append("get");
        stringBuilder.append(fieldName.substring(0, 1).toUpperCase());
        stringBuilder.append(fieldName.substring(1, fieldName.length()));
        return stringBuilder.toString();
    }

    public static String createSetMethodNameByFieldName(String fieldName){
        StringBuilder stringBuilder = new StringBuilder(5);
        stringBuilder.append("set");
        stringBuilder.append(fieldName.substring(0, 1).toUpperCase());
        stringBuilder.append(fieldName.substring(1, fieldName.length()));
        return stringBuilder.toString();
    }
}
