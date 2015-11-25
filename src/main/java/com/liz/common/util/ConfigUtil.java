package com.liz.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

    private static Properties _manager_properties = new Properties();

    static {
        try {
            _manager_properties.load(new FileInputStream(new File(ConfigUtil.class.getResource("/conf.properties").getPath())));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public static String getProperty(String key) {
        return _manager_properties.getProperty(key);
    }
}

