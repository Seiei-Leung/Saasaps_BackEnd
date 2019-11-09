package top.seiei.saasaps.bean;

import java.io.Serializable;
import java.util.Date;

public class ColorSetting implements Serializable {
    private Integer id;

    private String defaultColor;

    private String defaultDelayColor;

    private String defaultAdvanceColor;

    private String advanceColor;

    private String delayColor;

    private Integer advanceDaynum;

    private Integer delayDaynum;

    private Integer userId;

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

    public String getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(String defaultColor) {
        this.defaultColor = defaultColor == null ? null : defaultColor.trim();
    }

    public String getDefaultDelayColor() {
        return defaultDelayColor;
    }

    public void setDefaultDelayColor(String defaultDelayColor) {
        this.defaultDelayColor = defaultDelayColor == null ? null : defaultDelayColor.trim();
    }

    public String getDefaultAdvanceColor() {
        return defaultAdvanceColor;
    }

    public void setDefaultAdvanceColor(String defaultAdvanceColor) {
        this.defaultAdvanceColor = defaultAdvanceColor == null ? null : defaultAdvanceColor.trim();
    }

    public String getAdvanceColor() {
        return advanceColor;
    }

    public void setAdvanceColor(String advanceColor) {
        this.advanceColor = advanceColor == null ? null : advanceColor.trim();
    }

    public String getDelayColor() {
        return delayColor;
    }

    public void setDelayColor(String delayColor) {
        this.delayColor = delayColor == null ? null : delayColor.trim();
    }

    public Integer getAdvanceDaynum() {
        return advanceDaynum;
    }

    public void setAdvanceDaynum(Integer advanceDaynum) {
        this.advanceDaynum = advanceDaynum;
    }

    public Integer getDelayDaynum() {
        return delayDaynum;
    }

    public void setDelayDaynum(Integer delayDaynum) {
        this.delayDaynum = delayDaynum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        ColorSetting other = (ColorSetting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDefaultColor() == null ? other.getDefaultColor() == null : this.getDefaultColor().equals(other.getDefaultColor()))
            && (this.getDefaultDelayColor() == null ? other.getDefaultDelayColor() == null : this.getDefaultDelayColor().equals(other.getDefaultDelayColor()))
            && (this.getDefaultAdvanceColor() == null ? other.getDefaultAdvanceColor() == null : this.getDefaultAdvanceColor().equals(other.getDefaultAdvanceColor()))
            && (this.getAdvanceColor() == null ? other.getAdvanceColor() == null : this.getAdvanceColor().equals(other.getAdvanceColor()))
            && (this.getDelayColor() == null ? other.getDelayColor() == null : this.getDelayColor().equals(other.getDelayColor()))
            && (this.getAdvanceDaynum() == null ? other.getAdvanceDaynum() == null : this.getAdvanceDaynum().equals(other.getAdvanceDaynum()))
            && (this.getDelayDaynum() == null ? other.getDelayDaynum() == null : this.getDelayDaynum().equals(other.getDelayDaynum()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDefaultColor() == null) ? 0 : getDefaultColor().hashCode());
        result = prime * result + ((getDefaultDelayColor() == null) ? 0 : getDefaultDelayColor().hashCode());
        result = prime * result + ((getDefaultAdvanceColor() == null) ? 0 : getDefaultAdvanceColor().hashCode());
        result = prime * result + ((getAdvanceColor() == null) ? 0 : getAdvanceColor().hashCode());
        result = prime * result + ((getDelayColor() == null) ? 0 : getDelayColor().hashCode());
        result = prime * result + ((getAdvanceDaynum() == null) ? 0 : getAdvanceDaynum().hashCode());
        result = prime * result + ((getDelayDaynum() == null) ? 0 : getDelayDaynum().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
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
        sb.append(", defaultColor=").append(defaultColor);
        sb.append(", defaultDelayColor=").append(defaultDelayColor);
        sb.append(", defaultAdvanceColor=").append(defaultAdvanceColor);
        sb.append(", advanceColor=").append(advanceColor);
        sb.append(", delayColor=").append(delayColor);
        sb.append(", advanceDaynum=").append(advanceDaynum);
        sb.append(", delayDaynum=").append(delayDaynum);
        sb.append(", userId=").append(userId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}