package top.seiei.saasaps.bean;

import java.io.Serializable;
import java.util.Date;

public class ProductionPlanning implements Serializable {
    private Integer id;

    private Integer productionLineId;

    private Date startTime;

    private Date endTime;

    private Integer qtyPlan;

    private Integer qtyFinish;

    private String custnames;

    private String ordernums;

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

    public Integer getProductionLineId() {
        return productionLineId;
    }

    public void setProductionLineId(Integer productionLineId) {
        this.productionLineId = productionLineId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getQtyPlan() {
        return qtyPlan;
    }

    public void setQtyPlan(Integer qtyPlan) {
        this.qtyPlan = qtyPlan;
    }

    public Integer getQtyFinish() {
        return qtyFinish;
    }

    public void setQtyFinish(Integer qtyFinish) {
        this.qtyFinish = qtyFinish;
    }

    public String getCustnames() {
        return custnames;
    }

    public void setCustnames(String custnames) {
        this.custnames = custnames == null ? null : custnames.trim();
    }

    public String getOrdernums() {
        return ordernums;
    }

    public void setOrdernums(String ordernums) {
        this.ordernums = ordernums == null ? null : ordernums.trim();
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
        ProductionPlanning other = (ProductionPlanning) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductionLineId() == null ? other.getProductionLineId() == null : this.getProductionLineId().equals(other.getProductionLineId()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getQtyPlan() == null ? other.getQtyPlan() == null : this.getQtyPlan().equals(other.getQtyPlan()))
            && (this.getQtyFinish() == null ? other.getQtyFinish() == null : this.getQtyFinish().equals(other.getQtyFinish()))
            && (this.getCustnames() == null ? other.getCustnames() == null : this.getCustnames().equals(other.getCustnames()))
            && (this.getOrdernums() == null ? other.getOrdernums() == null : this.getOrdernums().equals(other.getOrdernums()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductionLineId() == null) ? 0 : getProductionLineId().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getQtyPlan() == null) ? 0 : getQtyPlan().hashCode());
        result = prime * result + ((getQtyFinish() == null) ? 0 : getQtyFinish().hashCode());
        result = prime * result + ((getCustnames() == null) ? 0 : getCustnames().hashCode());
        result = prime * result + ((getOrdernums() == null) ? 0 : getOrdernums().hashCode());
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
        sb.append(", productionLineId=").append(productionLineId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", qtyPlan=").append(qtyPlan);
        sb.append(", qtyFinish=").append(qtyFinish);
        sb.append(", custnames=").append(custnames);
        sb.append(", ordernums=").append(ordernums);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}