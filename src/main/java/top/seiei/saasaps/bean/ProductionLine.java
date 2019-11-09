package top.seiei.saasaps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductionLine implements Serializable {
    private Integer id;

    private String workgroup;

    private String workshop;

    private String lineCode;

    private String defaultStyleName;

    private Integer peopleNum;

    private BigDecimal workhours;

    private Boolean isinvalid;

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

    public String getWorkgroup() {
        return workgroup;
    }

    public void setWorkgroup(String workgroup) {
        this.workgroup = workgroup == null ? null : workgroup.trim();
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop == null ? null : workshop.trim();
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode == null ? null : lineCode.trim();
    }

    public String getDefaultStyleName() {
        return defaultStyleName;
    }

    public void setDefaultStyleName(String defaultStyleName) {
        this.defaultStyleName = defaultStyleName == null ? null : defaultStyleName.trim();
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public BigDecimal getWorkhours() {
        return workhours;
    }

    public void setWorkhours(BigDecimal workhours) {
        this.workhours = workhours;
    }

    public Boolean getIsinvalid() {
        return isinvalid;
    }

    public void setIsinvalid(Boolean isinvalid) {
        this.isinvalid = isinvalid;
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
        ProductionLine other = (ProductionLine) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWorkgroup() == null ? other.getWorkgroup() == null : this.getWorkgroup().equals(other.getWorkgroup()))
            && (this.getWorkshop() == null ? other.getWorkshop() == null : this.getWorkshop().equals(other.getWorkshop()))
            && (this.getLineCode() == null ? other.getLineCode() == null : this.getLineCode().equals(other.getLineCode()))
            && (this.getDefaultStyleName() == null ? other.getDefaultStyleName() == null : this.getDefaultStyleName().equals(other.getDefaultStyleName()))
            && (this.getPeopleNum() == null ? other.getPeopleNum() == null : this.getPeopleNum().equals(other.getPeopleNum()))
            && (this.getWorkhours() == null ? other.getWorkhours() == null : this.getWorkhours().equals(other.getWorkhours()))
            && (this.getIsinvalid() == null ? other.getIsinvalid() == null : this.getIsinvalid().equals(other.getIsinvalid()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWorkgroup() == null) ? 0 : getWorkgroup().hashCode());
        result = prime * result + ((getWorkshop() == null) ? 0 : getWorkshop().hashCode());
        result = prime * result + ((getLineCode() == null) ? 0 : getLineCode().hashCode());
        result = prime * result + ((getDefaultStyleName() == null) ? 0 : getDefaultStyleName().hashCode());
        result = prime * result + ((getPeopleNum() == null) ? 0 : getPeopleNum().hashCode());
        result = prime * result + ((getWorkhours() == null) ? 0 : getWorkhours().hashCode());
        result = prime * result + ((getIsinvalid() == null) ? 0 : getIsinvalid().hashCode());
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
        sb.append(", workgroup=").append(workgroup);
        sb.append(", workshop=").append(workshop);
        sb.append(", lineCode=").append(lineCode);
        sb.append(", defaultStyleName=").append(defaultStyleName);
        sb.append(", peopleNum=").append(peopleNum);
        sb.append(", workhours=").append(workhours);
        sb.append(", isinvalid=").append(isinvalid);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}