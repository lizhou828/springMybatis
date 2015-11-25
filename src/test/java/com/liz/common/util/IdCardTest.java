package com.liz.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lizhou on 2015/7/23
 * 暴力破解 身份证号码
 */
public class IdCardTest {
    public static void main(String [] args) {
//        decryptIdCardNO("4302241995****4229");
        decryptIdCardNO("4304241989****3151");
//        getInfoFromIdCard("430424198908283151");
//        getInfoFromIdCard("430224199502184229");

    }

    private static void getInfoFromIdCard(String s) {
        int age = IdcardUtil.getAgeByIdCard(s);
        String birthday = IdcardUtil.getBirthByIdCard(s);
        String gender = IdcardUtil.getGenderByIdCard(s);
        String province = IdcardUtil.getProvinceByIdCard(s);
        Short date = IdcardUtil.getDateByIdCard(s);
        String welcome = "m".equalsIgnoreCase(gender) ? "先生" : "女士";
        System.out.println("来自"+province+"的"+welcome+"，您好! 您出生于："+birthday+",今年"+age+"岁");


    }

    public static List<String> months = Arrays.asList(new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"});
    public static List<String> days = Arrays.asList(new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13",
            "14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"});

    /**
     * 暴力破解 身份证号码
     * @param idCardNO 不完整的身份证号码
     */
    private static void decryptIdCardNO(String idCardNO) {
        String head = idCardNO.substring(0,idCardNO.indexOf("*"));
        String foot = idCardNO.substring(idCardNO.lastIndexOf("*")+1,idCardNO.length());
        System.out.println("head = " + head + ",foot = " + foot);
        int i = 0;
        List<String> validIdCards = new ArrayList<String>();
        for(String month : months){
            for(String day :days){
                i++;
                String idCardTemp = head + month+day+foot;
                if( IdcardUtil.validateIdCard18(idCardTemp) ){
                    validIdCards.add(idCardTemp);
                    System.out.println("身份证号码："+idCardTemp);
                } else {
                    System.out.println("身份证号码："+idCardTemp+",第"+i+"次破解失败...");
                }

            }
        }
        System.out.println("可能正确的身份证号码:"+validIdCards+",\n共"+validIdCards.size()+"个");
    }
}
