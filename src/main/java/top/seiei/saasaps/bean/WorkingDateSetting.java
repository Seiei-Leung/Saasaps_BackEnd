package top.seiei.saasaps.bean;

import java.io.Serializable;
import java.util.Date;

public class WorkingDateSetting implements Serializable {
    private Integer id;

    private Integer effectiveYear;

    private Boolean monday;

    private Boolean tuesday;

    private Boolean wednesday;

    private Boolean thursday;

    private Boolean friday;

    private Boolean saturday;

    private Boolean sunday;

    private Integer updateUserId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEffectiveYear() {
        return effectiveYear;
    }

    public void setEffectiveYear(Integer effectiveYear) {
        this.effectiveYear = effectiveYear;
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

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WorkingDateSetting other = (WorkingDateSetting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEffectiveYear() == null ? other.getEffectiveYear() == null : this.getEffectiveYear().equals(other.getEffectiveYear()))
            && (this.getMonday() == null ? other.getMonday() == null : this.getMonday().equals(other.getMonday()))
            && (this.getTuesday() == null ? other.getTuesday() == null : this.getTuesday().equals(other.getTuesday()))
            && (this.getWednesday() == null ? other.getWednesday() == null : this.getWednesday().equals(other.getWednesday()))
            && (this.getThursday() == null ? other.getThursday() == null : this.getThursday().equals(other.getThursday()))
            && (this.getFriday() == null ? other.getFriday() == null : this.getFriday().equals(other.getFriday()))
            && (this.getSaturday() == null ? other.getSaturday() == null : this.getSaturday().equals(other.getSaturday()))
            && (this.getSunday() == null ? other.getSunday() == null : this.getSunday().equals(other.getSunday()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEffectiveYear() == null) ? 0 : getEffectiveYear().hashCode());
        result = prime * result + ((getMonday() == null) ? 0 : getMonday().hashCode());
        result = prime * result + ((getTuesday() == null) ? 0 : getTuesday().hashCode());
        result = prime * result + ((getWednesday() == null) ? 0 : getWednesday().hashCode());
        result = prime * result + ((getThursday() == null) ? 0 : getThursday().hashCode());
        result = prime * result + ((getFriday() == null) ? 0 : getFriday().hashCode());
        result = prime * result + ((getSaturday() == null) ? 0 : getSaturday().hashCode());
        result = prime * result + ((getSunday() == null) ? 0 : getSunday().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", effectiveYear=").append(effectiveYear);
        sb.append(", monday=").append(monday);
        sb.append(", tuesday=").append(tuesday);
        sb.append(", wednesday=").append(wednesday);
        sb.append(", thursday=").append(thursday);
        sb.append(", friday=").append(friday);
        sb.append(", saturday=").append(saturday);
        sb.append(", sunday=").append(sunday);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}