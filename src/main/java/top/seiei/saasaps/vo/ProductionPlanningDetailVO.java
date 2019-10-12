package top.seiei.saasaps.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ProductionPlanningDetailVO {

    private Integer id;

    private Integer productionPlanningId;

    private String custname;

    private String goodName;

    private String ordernum;

    private String season;

    private String color;

    private Integer qtyPlan;

    private Integer qtyFinish;

    private String productClassName;

    private String styleName;

    private Date leavingTime;

    private BigDecimal efficiency;

    private BigDecimal productionClassEfficiency;

    private BigDecimal productionLineEfficiency;

    private Integer richan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductionPlanningId() {
        return productionPlanningId;
    }

    public void setProductionPlanningId(Integer productionPlanningId) {
        this.productionPlanningId = productionPlanningId;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        this.productClassName = productClassName;
    }

    public Date getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Date leavingTime) {
        this.leavingTime = leavingTime;
    }

    public BigDecimal getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(BigDecimal efficiency) {
        this.efficiency = efficiency;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleId) {
        this.styleName = styleId;
    }

    public BigDecimal getProductionClassEfficiency() {
        return productionClassEfficiency;
    }

    public void setProductionClassEfficiency(BigDecimal productionClassEfficiency) {
        this.productionClassEfficiency = productionClassEfficiency;
    }

    public Integer getRichan() {
        return richan;
    }

    public void setRichan(Integer richan) {
        this.richan = richan;
    }

    public BigDecimal getProductionLineEfficiency() {
        return productionLineEfficiency;
    }

    public void setProductionLineEfficiency(BigDecimal productionLineEfficiency) {
        this.productionLineEfficiency = productionLineEfficiency;
    }
}
