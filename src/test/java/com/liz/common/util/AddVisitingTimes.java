package com.liz.common.util;

import java.io.IOException;

/**
 * Created by Frank on 2015/8/13
 *
 */
public class AddVisitingTimes {

    public static void main(String [] args) throws IOException {
        String url = "http://club.life.sina.com.cn/thread-1938752-1-1.html?qq-pf-to=pcqq.group";
        String charSet = "utf-8";
        for(int i = 0;i < 1000; i++){
            String result = HttpUtil.sendGetRequest(url,charSet);
            System.out.println("第"+i+"次访问url："+url);
            System.out.println("第"+i+"次访问的结果为:"+result);
        }

    }
}
