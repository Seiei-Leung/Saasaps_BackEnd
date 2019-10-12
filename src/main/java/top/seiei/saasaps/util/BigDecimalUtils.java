package top.seiei.saasaps.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * BigDecimal 工具类，传入两个 double 类型的数值
 * 获取加减乘除的结果
 */
public class BigDecimalUtils {

    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal divide(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 0, BigDecimal.ROUND_CEILING); // 除不尽，不保留小数，四舍五入
    }

    public static void main(String[] args) {
        Date date = new Date(1490371200000L);
        System.out.println(date);
    }
}
