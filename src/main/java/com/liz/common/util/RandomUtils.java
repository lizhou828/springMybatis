package com.liz.common.util;

import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: pengcg
 * Date: 14-5-28
 * Time: P.M 9:28
 */
public class RandomUtils {

    /**
     * 获得UUID
     * @return UUID
     */
	public static String shortUUID() {
		return UUID.randomUUID().toString().split("-")[0];
	}

    /**
     * 获得指定范围的随机数
     * @param min 最小值
     * @param max 最大值
     * @return 随机数
     */
    public static int rand(Integer min, Integer max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }


    public static void main(String[] args) {
        System.out.println(DateUtil.format(DateUtils.addHours(new Date(), -24), "yyyy-MM-dd HH:mm:ss"));
    }

}
