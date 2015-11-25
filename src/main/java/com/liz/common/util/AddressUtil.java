package com.liz.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 14-7-14
 * Time: P.M. 5:21
 * To change this template use File | Settings | File Templates.
 */
public class AddressUtil {

    //根据详细地址生成经纬度
    public static Map getLatLngByAddress(String address, String regionName) throws IOException {
        if(address == null || "".equals(address) || regionName == null || "".equals(regionName)){
            return null;
        }
        String url = String.format("http://apis.map.qq.com/ws/geocoder/v1/");

        Map<String, String> params = new HashMap<String, String>();
        params.put("region", regionName);
        params.put("address", address);
        params.put("key", "HIPBZ-IQL3W-ZFVRC-OG523-TEZSV-VDBNJ");

        url = String.format(url,regionName,address);
        String JsonResult = HttpUtil.sendGetRequest(url, params, "UTF-8");
        Map<String, Object> data = (Map<String, Object>) JsonUtil.format(JsonResult, Map.class);
        Map result = (Map) data.get("result");
        Map location = (Map) result.get("location");
        Double latitude = (Double) location.get("lat");
        Double longitude = (Double) location.get("lng");

        Map map = new HashMap();
        map.put("status",data.get("status"));
        map.put("message",data.get("message"));
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        return map;
    }

    //根据经纬度生成详细地址

    public static String getAddressByLatLng(Double latitude,Double longtitude) throws IOException {
        String url = "http://apis.map.qq.com/ws/geocoder/v1/";
        Map<String, String> params = new HashMap<String, String>();
        params.put("location", latitude +","+ longtitude);
        params.put("get_poi", 1+ "");
        params.put("key", "HIPBZ-IQL3W-ZFVRC-OG523-TEZSV-VDBNJ");

        String JsonResult = HttpUtil.sendGetRequest(url, params, "UTF-8");
        Map<String, Object> data = (Map<String, Object>) JsonUtil.format(JsonResult, Map.class);
        Map result = (Map) data.get("result");
        Map location = (Map) result.get("location");
        String address = (String) result.get("address");

        return address;
    }

    public static void main(String args[]) throws IOException {
        String address="美术馆东街十八号";
        String regionName = "北京市    北京市    东城区";
        Map<String,Object> map = getLatLngByAddress(address,regionName);
        if( map != null && map.size() > 0 ){
            System.out.println("—————————根据详细地址生成经纬度—————————————");
            System.out.println("实际地址是："+regionName+address);
            Iterator iterator= map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry entry = (Map.Entry)iterator.next();
                String key = entry.getKey().toString();
                String value = entry.getValue().toString();
                System.out.println("key="+key+",value="+value);
            }
        }

        Double longitude =116.410702;
        Double latitude =39.926655;
        String realAddress =  getAddressByLatLng(latitude,longitude);
        System.out.println("—————————根据经纬度逆解析，生成详细地址—————————————");
        System.out.println("经度："+longitude+",纬度："+latitude+",解析出来的地址是："+realAddress);

    }

}
