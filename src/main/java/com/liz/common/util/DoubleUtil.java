package com.liz.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA.
 * User: pengcg
 * Date: 15-2-4
 * Time: A.M 12:37
 */
public class DoubleUtil {

	/**
	 * 对double数据进行取精度.
	 * @param value  double数据.
	 * @param scale  精度位数(保留的小数位数).
	 * @param roundingMode  精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static double round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}


	/**
	 * double 相加
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sum(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();
	}


	/**
	 * double 相减
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}


	/**
	 * double 乘法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double mul(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	}


	/**
	 * double 除法
	 * @param d1
	 * @param d2
	 * @param scale 四舍五入 小数点位数
	 * @return
	 */
	public static double div(double d1, double d2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 计算提成
	 * @param orgPrice 供货价
	 * @param price 销售价
	 * @return 提成
	 */
	public static double ticheng(double orgPrice, double price) {
		return sub(1, div(orgPrice, price, 4));
	}

	/**
	 * 计算提成
	 * @param x 供货价
	 * @param y 销售价
	 * @return 提成
	 */
	public static float ticheng(float x, float y) {
		return Float.valueOf(ticheng(Double.valueOf(x + ""), Double.valueOf(y + "")) + "");
	}

	/**
	 * float 相减
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static float sub(float d1, float d2) {
		BigDecimal bd1 = new BigDecimal(Float.toString(d1));
		BigDecimal bd2 = new BigDecimal(Float.toString(d2));
		return bd1.subtract(bd2).floatValue();
	}

	/**
	 * float 乘法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static float mul(float d1, float d2) {
		BigDecimal bd1 = new BigDecimal(Float.toString(d1));
		BigDecimal bd2 = new BigDecimal(Float.toString(d2));
		return bd1.multiply(bd2).floatValue();
	}

    /**
     * 向下取 i 位小数
     * @param d 传入的double 类型值
     * @param i 位数
     * @return 向下取 i 位小数
     */
    public static double down(Double d, int i) {
        return Double.valueOf(BigDecimal.valueOf(d).setScale(i, RoundingMode.DOWN) + "");
    }
}
