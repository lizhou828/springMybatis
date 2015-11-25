package com.liz.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: lml
 * Date: 14-12-14
 * Time: P.M 2:53
 */
public class ValidateUtil {
    private static Log log = LogFactory.getLog(ValidateUtil.class);
    private static final String mobileAgents[] = {"240x320","acer","acoon","acs-","abacho","ahong","airness",
            "alcatel","amoi","android","anywhereyougo.com","applewebkit/525","applewebkit/532","asus","audio",
            "au-mic","avantogo","becker","benq","bilbo","bird","blackberry","blazer","bleu","cdm-","compal",
            "coolpad","danger","dbtel","dopod","elaine","eric","etouch","fly ","fly_","fly-","go.web",
            "goodaccess","gradiente","grundig","haier","hedy","hitachi","htc","huawei","hutchison","inno",
            "jbrowser","kddi","kgt","kwc","lenovo","lg ","lg2","lg3","lg4","lg5","lg7","lg8","lg9","lg-",
            "lge-","lge9","longcos","maemo","mercator","meridian","micromax","midp","mini","mitsu","mmm",
            "mmp","mobi","mot-","moto","nec-","netfront","newgen","nexian","nf-browser","nintendo","nitro",
            "nokia","nook","novarra","obigo","palm","panasonic","pantech","philips","phone","pg-","playstation",
            "pocket","pt-","qc-","qtek","rover","sagem","sama","samu","sanyo","samsung","sch-","scooter","sec-",
            "sendo","sgh-","sharp","siemens","sie-","softbank","sony","spice","sprint","spv","symbian","tablet",
            "talkabout","tcl-","teleca","telit","tianyu","tim-","toshiba","tsm","up.browser","utec","utstar",
            "verykool","virgin","vk-","voda","voxtel","vx","wap","wellco","wig browser","wii","windows ce",
            "wireless","xda","xde","zte"};


    /**
     * 验证邮箱地址是否正确
     * @param email 邮箱
     * @return 返回boolean true 正确的邮箱 false 不是邮箱
     */
    public static boolean checkEmail(String email) {
        boolean flag ;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            log.error("验证邮箱地址错误", e);
            flag = false;
        }
        return flag;
    }

    /**
     * 验证联系电话
     * @param tel 座机或手机号
     * @return boolean true 是手机号或座机 false 不是联系电话
     */
    public static boolean isTel(String tel) {
        boolean flag = false;
        try {
            Pattern t = Pattern.compile("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$");
            Matcher m = t.matcher(tel);
            Pattern p = Pattern.compile("^1[345789]\\d{9}$");
            Matcher m1 = p.matcher(tel);
            if (m.matches()) {
                flag = true;
            }
            if (m1.matches()) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("验证联系电话错误", e);
            flag = false;
        }
        return flag ;
    }

    /*
    * 验证座机格式
    */
    public static boolean isTelNO(String tel) {
        boolean flag ;
        try {
            Pattern p = Pattern.compile("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$");
            Matcher m = p.matcher(tel);
            flag =  m.matches();
        } catch (Exception e) {
            log.error("验证座机格式错误", e);
            flag = false;
        }
        return flag ;
    }

    /**
     * 验证手机号码
     * @param mobiles 手机号
     * @return  boolean  true 是手机号 false 不是手机号
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag ;
        try {
            Pattern p = Pattern.compile("^1[345789]\\d{9}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            log.error("验证手机号码错误", e);
            flag = false;
        }
        return flag;
    }

    //验证15 或18 位身份证号码
    public static boolean isShenfenzheng(String shenfenzheng) {
        try {
            if (IdcardUtil.validateIdCard15(shenfenzheng)) {
                return true;
            }
            if (IdcardUtil.validateIdCard18(shenfenzheng)) {
                return true;
            }
        } catch (Exception e) {
            log.error("验证身份证号码错误",e);
        }
        return false;
    }

    //验证银行卡号
    public static boolean isKahao(String kahao) {
        boolean flag ;
        try {
            Pattern p = Pattern.compile("^\\d{15,19}$");
            Matcher m  = p.matcher(kahao);
            flag = m.matches();
        } catch (Exception e) {
            log.error("验证银行卡号错误",e);
            flag = false;
        }
        return flag;
    }

    //验证邮政编码
    public static boolean isZipCode(String zipcode) {
        boolean flag ;
        try {
            Pattern p = Pattern.compile("/^\\d{6}$/");
            Matcher m = p.matcher(zipcode);
            flag = m.matches();
        } catch (Exception e) {
            log.error("邮政编码错误", e);
            flag = false;
        }
        return flag;

    }

    //判断是否是移动端还是pc端
    public static boolean  isMobile(String userAgent) {
        boolean isMobile = false;
        for (String mobileAgent : mobileAgents) {
            //判断是否是移动端设备
            if (userAgent.toLowerCase().indexOf(mobileAgent) > 0) {
                //若是ipad,则不认为是移动设备
                if (!userAgent.toLowerCase().contains("ipad")) {
                    isMobile = true;
                }
                break;
            }
        }
        return isMobile;
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param value
     *            指定的字符串
     * @return 字符串的长度
     */
    public static int getValueLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

}
