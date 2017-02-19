package cn.sqh.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * UUID生成工具
 * Created by Administrator on 2017/2/17.
 */
public class UUIDUtil {
    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

}
