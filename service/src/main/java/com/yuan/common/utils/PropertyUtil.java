package com.yuan.common.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author joryun ON 2017/10/22.
 */
public class PropertyUtil {

    public static String getValue(String path, String key) {
        try {
            Resource resource = new ClassPathResource(path);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
