package top.seiei.saasaps.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import top.seiei.saasaps.bean.Festival;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 用于 Post 工厂日历
 */
public class WorkingDateSettingVO {

    private Integer id;

    private Integer year;

    private Boolean monday;

    private Boolean tuesday;

    private Boolean wednesday;

    private Boolean thursday;

    private Boolean friday;

    private Boolean saturday;

    private Boolean sunday;

    private List<Festival> festivalList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getMonday() {
        return monday;
    }

    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    public Boolean getTuesday() {
        return tuesday;
    }

    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    public Boolean getWednesday() {
        return wednesday;
    }

    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    public Boolean getThursday() {
        return thursday;
    }

    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    public Boolean getFriday() {
        return friday;
    }

    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    public Boolean getSaturday() {
        return saturday;
    }

    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
    }

    public Boolean getSunday() {
        return sunday;
    }

    public void setSunday(Boolean sunday) {
        this.sunday = sunday;
    }

    public List<Festival> getFestivalList() {
        return festivalList;
    }

    public void setFestivalList(List<Festival> festivalList) {
        this.festivalList = festivalList;
    }

    /**
     * 判断某日是否放假
     * @param timeStamp 某日的时间戳
     * @return
     */
    @JsonIgnore
    public Boolean isHoliday(Long timeStamp) {
        Date searchDate = new Date(timeStamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(searchDate);
        Integer weekday = calendar.get(Calendar.DAY_OF_WEEK);
        if (!((weekday == 1 && this.sunday) ||
                (weekday == 2 && this.monday) ||
                (weekday == 3 && this.tuesday) ||
                (weekday == 4 && this.wednesday) ||
                (weekday == 5 && this.thursday) ||
                (weekday == 6 && this.friday) ||
                (weekday == 7 && this.saturday)))
        {
            return true;
        }
        // 判断是否在节日中
        for (Festival festival : this.festivalList) {
            Long startTimeStamp = festival.getBeginDate().getTime();
            Long endTimeStamp = festival.getEndDate().getTime();
            if (startTimeStamp <= timeStamp && timeStamp <= endTimeStamp) {
                return true;
            }
        }
        return false;
    }
}
