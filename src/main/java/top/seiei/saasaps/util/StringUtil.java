package top.seiei.saasaps.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

public class StringUtil {

    /**
     * 补零
     * @param str 补零字符串
     * @param length 补零的长度
     * @return
     */
    public static String zeroFill(String str, Integer length) {
        if (str.length() == length) {
            return str;
        }
        Integer balance = length - str.length();
        for (int i=0; i<balance; i++) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 字符串转Integer
     * @param str 字符串
     * @return
     */
    public static Integer strToInteger(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return Integer.parseInt(str);
    }

    /**
     * Long类型的字符串转Date
     * @param str 字符串
     * @return
     */
    public static Date strToDate(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Long timeStamp = Long.parseLong(str);
        return DateUtil.zeroSetting(new Date(timeStamp));
    }

    /**
     * 字符串转 BigDecimal
     * @param str 字符串
     * @return
     */
    public static BigDecimal strToBigDeciaml(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return new BigDecimal(str);
    }

    /**
     * 字符串转 Boolean
     * @param str 字符串
     * @return
     */
    public static Boolean strToBoolean(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (StringUtils.equals(str, "true")) {
            return true;
        }
        return false;
    }
}
