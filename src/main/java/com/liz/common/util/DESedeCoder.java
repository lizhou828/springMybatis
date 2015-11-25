package com.liz.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;


public class DESedeCoder {
    /**
     * @param input
     * @return
     * @throws Exception
     */
    public static String encryptData(String input) throws Exception {
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = "ABCDEFGH".getBytes();
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, key, sr);
        byte[] cipherByte = c.doFinal(input.getBytes());
        String dec = new BASE64Encoder().encode(cipherByte);
        return dec;

    }

    /**
     * @param input
     * @return
     * @throws Exception
     */
    public static String decryptData(String input) throws Exception {
        byte[] dec = new BASE64Decoder().decodeBuffer(input);
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = "ABCDEFGH".getBytes();
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.DECRYPT_MODE, key, sr);
        byte[] clearByte = c.doFinal(dec);

        return new String(clearByte);

    }

    public static void main(String[] args) throws Exception {
        String s = "123";
        String s1 = encryptData(s);
        System.out.println(s1);
        System.out.println(decryptData(s1));
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 截取字符串
     * @param value  需要截取的字符串
     * @param length 保留的长度
     * @return        截取后的长度
     */
    public static String hiddenStr(String value, int length) throws Exception{
        byte[] bytes = value.getBytes("Unicode");
        int n = 0; // 表示当前的字节数
        int i = 2; // 要截取的字节数，从第3个字节开始
        for (; i < bytes.length && n < length; i++){
            if (i % 2 == 1){
                n++; // 在UCS2第二个字节时n加1
            }
            else{
                if (bytes[i] != 0){
                    n++;
                }
            }
        }
        if (i % 2 == 1){
            i = i + 1;
        }
        return new String(bytes, 0, i, "Unicode");
    }

}
