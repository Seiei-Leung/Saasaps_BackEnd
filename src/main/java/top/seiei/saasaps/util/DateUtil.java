package top.seiei.saasaps.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    /**
     * 一天的时间戳
     * @return
     */
    public static Integer timeStampOfOneDay() {
        return 86400000;
    }

    /**
     * 时间重置为零点零分零秒
     * @param date 重置时间
     * @return
     */
    public static Date zeroSetting(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            calendar.setTime(simpleDateFormat.parse(year + "-" + month + "-" + day + " 00:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTime();
    }

    /**
     * 获取单号日期格式
     * @return
     */
    public static String getBillFormat() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return Integer.toString(year).substring(2) + StringUtil.zeroFill(Integer.toString(month), 2) + StringUtil.zeroFill(Integer.toString(day), 2);
    }

    /**
     * 获取某年第一天零时零分零点的时间戳
     * @param year 年
     * @return
     */
    public static Date getFirstDayOfYear(Integer year) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(year + "-01-01 00:00:00");
    }

    /**
     * 基于今天的时间戳，加减天数从而获取对应的日期
     * @param dayNum 加减的天数
     * @return
     */
    public static Date getTimeStampByAddDayNumBaseOnToday(Integer dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, dayNum);//当前时间加减对应的天数
        return zeroSetting(calendar.getTime());
    }

    /**
     * 基于某天的时间戳的基础上，添加几个月获取对应的日期
     * @param date 基础时间戳
     * @param monthsNum 加减的月数
     * @return
     */
    public static Date getTimeStampByAddMonths(Date date, Integer monthsNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthsNum);
        return zeroSetting(calendar.getTime());
    }

    /**
     * 通过两个时间戳的差值转化为它们之间的 日期列表
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<Long> getTimeStampListByCompareTimeStamp(Date startTime, Date endTime) {
        List<Long> timeStampList = new ArrayList<>();
        Long startTimeStamp = startTime.getTime();
        Long endTimeStamp = endTime.getTime();
        if (endTimeStamp - startTimeStamp < 0) {
            return timeStampList;
        }
        Integer dayNum = (new Double(Math.floor((endTimeStamp - startTimeStamp)/DateUtil.timeStampOfOneDay()))).intValue();
        for (int i=0; i<dayNum; i++) {
            timeStampList.add(startTimeStamp+i*DateUtil.timeStampOfOneDay());
        }
        return timeStampList;
    }

    /**
     * 获取当月份对应的第一天时间对象
     * @param year 年份
     * @param month 月份
     * @return
     */
    public static Date getFirstDateOfMonth(Integer year, Integer month) throws ParseException {
        String dateStr = zeroFillOfMonthOrDay(year) + "-" + zeroFillOfMonthOrDay(month) + "-01 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(dateStr);
    }

    /**
     * 获取某月份最后一天的时间对象
     * @param year 年份
     * @param month 月份
     * @return
     * @throws ParseException
     */
    public static Date getLastDateOfMonth(Integer year, Integer month) throws ParseException {
        String dateStr = zeroFillOfMonthOrDay(year) + "-" + zeroFillOfMonthOrDay(month) + "-01 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dateStr = zeroFillOfMonthOrDay(year) + "-" + zeroFillOfMonthOrDay(month) + "-" + zeroFillOfMonthOrDay(lastDay) + " 00:00:00";
        return simpleDateFormat.parse(dateStr);
    }

    /**
     * 数字补零
     * @param num 月份或者日 数字
     * @return
     */
    public static String zeroFillOfMonthOrDay(Integer num) {
        String str = num.toString();
        return str.length() == 1 ? "0" + str : str;
    }
}
