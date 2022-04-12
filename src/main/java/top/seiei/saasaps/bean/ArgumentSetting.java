package top.seiei.saasaps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ArgumentSetting implements Serializable {
    private Integer id;

    private Boolean afterMinusorchangeefficiencyHasremovegapmodel;

    private Boolean afterMinusHasamend;

    private BigDecimal workhours;

    private Integer peopleNum;

    private Integer checkMatchCutpartsDaynum;

    private Integer approveToStartcutDaynum;

    private Integer beforeMaterialInstorageDaynum;

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

    public Boolean getAfterMinusorchangeefficiencyHasremovegapmodel() {
        return afterMinusorchangeefficiencyHasremovegapmodel;
    }

    public void setAfterMinusorchangeefficiencyHasremovegapmodel(Boolean afterMinusorchangeefficiencyHasremovegapmodel) {
        this.afterMinusorchangeefficiencyHasremovegapmodel = afterMinusorchangeefficiencyHasremovegapmodel;
    }

    public Boolean getAfterMinusHasamend() {
        return afterMinusHasamend;
    }

    public void setAfterMinusHasamend(Boolean afterMinusHasamend) {
        this.afterMinusHasamend = afterMinusHasamend;
    }

    public BigDecimal getWorkhours() {
        return workhours;
    }

    public void setWorkhours(BigDecimal workhours) {
        this.workhours = workhours;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getCheckMatchCutpartsDaynum() {
        return checkMatchCutpartsDaynum;
    }

    public void setCheckMatchCutpartsDaynum(Integer checkMatchCutpartsDaynum) {
        this.checkMatchCutpartsDaynum = checkMatchCutpartsDaynum;
    }

    public Integer getApproveToStartcutDaynum() {
        return approveToStartcutDaynum;
    }

    public void setApproveToStartcutDaynum(Integer approveToStartcutDaynum) {
        this.approveToStartcutDaynum = approveToStartcutDaynum;
    }

    public Integer getBeforeMaterialInstorageDaynum() {
        return beforeMaterialInstorageDaynum;
    }

    public void setBeforeMaterialInstorageDaynum(Integer beforeMaterialInstorageDaynum) {
        this.beforeMaterialInstorageDaynum = beforeMaterialInstorageDaynum;
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
        ArgumentSetting other = (ArgumentSetting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAfterMinusorchangeefficiencyHasremovegapmodel() == null ? other.getAfterMinusorchangeefficiencyHasremovegapmodel() == null : this.getAfterMinusorchangeefficiencyHasremovegapmodel().equals(other.getAfterMinusorchangeefficiencyHasremovegapmodel()))
            && (this.getAfterMinusHasamend() == null ? other.getAfterMinusHasamend() == null : this.getAfterMinusHasamend().equals(other.getAfterMinusHasamend()))
            && (this.getWorkhours() == null ? other.getWorkhours() == null : this.getWorkhours().equals(other.getWorkhours()))
            && (this.getPeopleNum() == null ? other.getPeopleNum() == null : this.getPeopleNum().equals(other.getPeopleNum()))
            && (this.getCheckMatchCutpartsDaynum() == null ? other.getCheckMatchCutpartsDaynum() == null : this.getCheckMatchCutpartsDaynum().equals(other.getCheckMatchCutpartsDaynum()))
            && (this.getApproveToStartcutDaynum() == null ? other.getApproveToStartcutDaynum() == null : this.getApproveToStartcutDaynum().equals(other.getApproveToStartcutDaynum()))
            && (this.getBeforeMaterialInstorageDaynum() == null ? other.getBeforeMaterialInstorageDaynum() == null : this.getBeforeMaterialInstorageDaynum().equals(other.getBeforeMaterialInstorageDaynum()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAfterMinusorchangeefficiencyHasremovegapmodel() == null) ? 0 : getAfterMinusorchangeefficiencyHasremovegapmodel().hashCode());
        result = prime * result + ((getAfterMinusHasamend() == null) ? 0 : getAfterMinusHasamend().hashCode());
        result = prime * result + ((getWorkhours() == null) ? 0 : getWorkhours().hashCode());
        result = prime * result + ((getPeopleNum() == null) ? 0 : getPeopleNum().hashCode());
        result = prime * result + ((getCheckMatchCutpartsDaynum() == null) ? 0 : getCheckMatchCutpartsDaynum().hashCode());
        result = prime * result + ((getApproveToStartcutDaynum() == null) ? 0 : getApproveToStartcutDaynum().hashCode());
        result = prime * result + ((getBeforeMaterialInstorageDaynum() == null) ? 0 : getBeforeMaterialInstorageDaynum().hashCode());
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
        sb.append(", afterMinusorchangeefficiencyHasremovegapmodel=").append(afterMinusorchangeefficiencyHasremovegapmodel);
        sb.append(", afterMinusHasamend=").append(afterMinusHasamend);
        sb.append(", workhours=").append(workhours);
        sb.append(", peopleNum=").append(peopleNum);
        sb.append(", checkMatchCutpartsDaynum=").append(checkMatchCutpartsDaynum);
        sb.append(", approveToStartcutDaynum=").append(approveToStartcutDaynum);
        sb.append(", beforeMaterialInstorageDaynum=").append(beforeMaterialInstorageDaynum);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}