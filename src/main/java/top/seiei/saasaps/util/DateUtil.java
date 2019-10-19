package top.seiei.saasaps.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

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

}
