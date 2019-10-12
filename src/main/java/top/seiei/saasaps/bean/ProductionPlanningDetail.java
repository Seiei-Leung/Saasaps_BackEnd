package top.seiei.saasaps.bean;

import java.io.Serializable;
import java.util.Date;

public class ProductionPlanningDetail implements Serializable {
    private Integer id;

    private Boolean isToDo;

    private Integer productionPlanningId;

    private Integer productionLineId;

    private String custname;

    private String goodName;

    private String ordernum;

    private String season;

    private String color;

    private Integer qtyPlan;

    private Integer qtyFinish;

    private String productClassName;

    private Date leavingTime;

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

    public Boolean getIsToDo() {
        return isToDo;
    }

    public void setIsToDo(Boolean isToDo) {
        this.isToDo = isToDo;
    }

    public Integer getProductionPlanningId() {
        return productionPlanningId;
    }

    public void setProductionPlanningId(Integer productionPlanningId) {
        this.productionPlanningId = productionPlanningId;
    }

    public Integer getProductionLineId() {
        return productionLineId;
    }

    public void setProductionLineId(Integer productionLineId) {
        this.productionLineId = productionLineId;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname == null ? null : custname.trim();
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
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

    public String getProductClassName() {
        return productClassName;
    }

    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName == null ? null : productClassName.trim();
    }

    public Date getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Date leavingTime) {
        this.leavingTime = leavingTime;
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
        ProductionPlanningDetail other = (ProductionPlanningDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIsToDo() == null ? other.getIsToDo() == null : this.getIsToDo().equals(other.getIsToDo()))
            && (this.getProductionPlanningId() == null ? other.getProductionPlanningId() == null : this.getProductionPlanningId().equals(other.getProductionPlanningId()))
            && (this.getProductionLineId() == null ? other.getProductionLineId() == null : this.getProductionLineId().equals(other.getProductionLineId()))
            && (this.getCustname() == null ? other.getCustname() == null : this.getCustname().equals(other.getCustname()))
            && (this.getGoodName() == null ? other.getGoodName() == null : this.getGoodName().equals(other.getGoodName()))
            && (this.getOrdernum() == null ? other.getOrdernum() == null : this.getOrdernum().equals(other.getOrdernum()))
            && (this.getSeason() == null ? other.getSeason() == null : this.getSeason().equals(other.getSeason()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getQtyPlan() == null ? other.getQtyPlan() == null : this.getQtyPlan().equals(other.getQtyPlan()))
            && (this.getQtyFinish() == null ? other.getQtyFinish() == null : this.getQtyFinish().equals(other.getQtyFinish()))
            && (this.getProductClassName() == null ? other.getProductClassName() == null : this.getProductClassName().equals(other.getProductClassName()))
            && (this.getLeavingTime() == null ? other.getLeavingTime() == null : this.getLeavingTime().equals(other.getLeavingTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIsToDo() == null) ? 0 : getIsToDo().hashCode());
        result = prime * result + ((getProductionPlanningId() == null) ? 0 : getProductionPlanningId().hashCode());
        result = prime * result + ((getProductionLineId() == null) ? 0 : getProductionLineId().hashCode());
        result = prime * result + ((getCustname() == null) ? 0 : getCustname().hashCode());
        result = prime * result + ((getGoodName() == null) ? 0 : getGoodName().hashCode());
        result = prime * result + ((getOrdernum() == null) ? 0 : getOrdernum().hashCode());
        result = prime * result + ((getSeason() == null) ? 0 : getSeason().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getQtyPlan() == null) ? 0 : getQtyPlan().hashCode());
        result = prime * result + ((getQtyFinish() == null) ? 0 : getQtyFinish().hashCode());
        result = prime * result + ((getProductClassName() == null) ? 0 : getProductClassName().hashCode());
        result = prime * result + ((getLeavingTime() == null) ? 0 : getLeavingTime().hashCode());
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
        sb.append(", isToDo=").append(isToDo);
        sb.append(", productionPlanningId=").append(productionPlanningId);
        sb.append(", productionLineId=").append(productionLineId);
        sb.append(", custname=").append(custname);
        sb.append(", goodName=").append(goodName);
        sb.append(", ordernum=").append(ordernum);
        sb.append(", season=").append(season);
        sb.append(", color=").append(color);
        sb.append(", qtyPlan=").append(qtyPlan);
        sb.append(", qtyFinish=").append(qtyFinish);
        sb.append(", productClassName=").append(productClassName);
        sb.append(", leavingTime=").append(leavingTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}