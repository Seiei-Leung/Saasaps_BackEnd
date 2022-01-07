package top.seiei.saasaps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductClass implements Serializable {
    private Integer id;

    private String name;

    private String productStyleName;

    private BigDecimal efficiency;

    private Integer updateUserId;

    private Date createTime;

    private Date updateTime;

    private BigDecimal prophaseLowEfficiency;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProductStyleName() {
        return productStyleName;
    }

    public void setProductStyleName(String productStyleName) {
        this.productStyleName = productStyleName == null ? null : productStyleName.trim();
    }

    public BigDecimal getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(BigDecimal efficiency) {
        this.efficiency = efficiency;
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

    public BigDecimal getProphaseLowEfficiency() {
        return prophaseLowEfficiency;
    }

    public void setProphaseLowEfficiency(BigDecimal prophaseLowEfficiency) {
        this.prophaseLowEfficiency = prophaseLowEfficiency;
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
        ProductClass other = (ProductClass) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getProductStyleName() == null ? other.getProductStyleName() == null : this.getProductStyleName().equals(other.getProductStyleName()))
            && (this.getEfficiency() == null ? other.getEfficiency() == null : this.getEfficiency().equals(other.getEfficiency()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getProphaseLowEfficiency() == null ? other.getProphaseLowEfficiency() == null : this.getProphaseLowEfficiency().equals(other.getProphaseLowEfficiency()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getProductStyleName() == null) ? 0 : getProductStyleName().hashCode());
        result = prime * result + ((getEfficiency() == null) ? 0 : getEfficiency().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getProphaseLowEfficiency() == null) ? 0 : getProphaseLowEfficiency().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", productStyleName=").append(productStyleName);
        sb.append(", efficiency=").append(efficiency);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", prophaseLowEfficiency=").append(prophaseLowEfficiency);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}