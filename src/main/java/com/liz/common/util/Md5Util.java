package com.liz.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: shuen
 * Date: 13-8-19
 * Time: A.M.11:16
 */
public class Md5Util {

    public static String md5Trans(String source){
        String dest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] charArray = source.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (int i=0; i<charArray.length; i++)
                byteArray[i] = (byte) charArray[i];
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i=0; i<md5Bytes.length; i++)
            {
                int val = ((int) md5Bytes[i] ) & 0xff;
                if (val < 16) hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            dest = hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("md5Trans error!", e);
        }
        return dest;
    }
}
