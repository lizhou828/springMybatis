package com.liz.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 15-1-23
 * Time: 下午5:51
 * To change this template use File | Settings | File Templates.
 */
public class RegionUtil {

    public static final String ipUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=%s";

    public static String findRegionNameByIp(String ip){

        if(StringUtils.isBlank(ip)){
//            ip = "220.168.56.121"; //湖南长沙  电信
            ip="116.234.90.0";//上海宝山区
//            ip = "60.176.0.0";//浙江杭州
        }
        ip = ip.trim();
        try {
            String jsonCity = HttpUtil.connect(String.format(ipUrl, ip), "UTF-8");
            if (("").equals(jsonCity)) return null;
            Map cityMap = (Map) JsonUtil.format(jsonCity, Map.class);
            if (cityMap == null) return null;
            Map regName = (Map) cityMap.get("data");
            return  String.valueOf(regName.get("city"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  "上海市";
        }
    }

    public static void main(String [] args ){
        String regionName = findRegionNameByIp(null);

    }

}
