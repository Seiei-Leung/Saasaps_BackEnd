package top.seiei.saasaps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductionPlanningDetail implements Serializable {
    private Integer id;

    private Integer summaryid;

    private Boolean isPlanning;

    private Integer productionlineid;

    private Date startTime;

    private Date endTime;

    private Integer qtyFinish;

    private String season;

    private String clientname;

    private String clientstyleno;

    private String orderno;

    private Integer ordernum;

    private String orderkind;

    private String styleno;

    private String goodname;

    private String style;

    private Date deliveryofcontractTime;

    private Date deliveryoffactoryTime;

    private Date arrivewarehouseTime;

    private Integer qtyofbatcheddelivery;

    private String lining;

    private Date liningofstitchingTime;

    private String suppliesoflining;

    private Date clothTime;

    private BigDecimal sam;

    private BigDecimal samoflocal;

    private BigDecimal sah;

    private Date approveTime;

    private String embroider;

    private Integer embroiderDaynum;

    private Date embroiderTime;

    private String factoryEmbroider;

    private String factoryEmbroider2;

    private String printafterembroider;

    private Integer printafterembroiderDaynum;

    private String factoryPrint;

    private String factoryPrint2;

    private Integer backpartDaynum;

    private String memo;

    private Integer cuttingqty;

    private Boolean isFinishCutting;

    private Integer advancecuttingDaynum;

    private Integer updateUserId;

    private Date createTime;

    private Date updateTime;

    private BigDecimal efficiencyOfClass; // 效率

    private String productStyleName; // 所属的款式分类

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSummaryid() {
        return summaryid;
    }

    public void setSummaryid(Integer summaryid) {
        this.summaryid = summaryid;
    }

    public Boolean getIsPlanning() {
        return isPlanning;
    }

    public void setIsPlanning(Boolean isPlanning) {
        this.isPlanning = isPlanning;
    }

    public Integer getProductionlineid() {
        return productionlineid;
    }

    public void setProductionlineid(Integer productionlineid) {
        this.productionlineid = productionlineid;
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

    public Integer getQtyFinish() {
        return qtyFinish;
    }

    public void setQtyFinish(Integer qtyFinish) {
        this.qtyFinish = qtyFinish;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname == null ? null : clientname.trim();
    }

    public String getClientstyleno() {
        return clientstyleno;
    }

    public void setClientstyleno(String clientstyleno) {
        this.clientstyleno = clientstyleno == null ? null : clientstyleno.trim();
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getOrderkind() {
        return orderkind;
    }

    public void setOrderkind(String orderkind) {
        this.orderkind = orderkind == null ? null : orderkind.trim();
    }

    public String getStyleno() {
        return styleno;
    }

    public void setStyleno(String styleno) {
        this.styleno = styleno == null ? null : styleno.trim();
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname == null ? null : goodname.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public Date getDeliveryofcontractTime() {
        return deliveryofcontractTime;
    }

    public void setDeliveryofcontractTime(Date deliveryofcontractTime) {
        this.deliveryofcontractTime = deliveryofcontractTime;
    }

    public Date getDeliveryoffactoryTime() {
        return deliveryoffactoryTime;
    }

    public void setDeliveryoffactoryTime(Date deliveryoffactoryTime) {
        this.deliveryoffactoryTime = deliveryoffactoryTime;
    }

    public Date getArrivewarehouseTime() {
        return arrivewarehouseTime;
    }

    public void setArrivewarehouseTime(Date arrivewarehouseTime) {
        this.arrivewarehouseTime = arrivewarehouseTime;
    }

    public Integer getQtyofbatcheddelivery() {
        return qtyofbatcheddelivery;
    }

    public void setQtyofbatcheddelivery(Integer qtyofbatcheddelivery) {
        this.qtyofbatcheddelivery = qtyofbatcheddelivery;
    }

    public String getLining() {
        return lining;
    }

    public void setLining(String lining) {
        this.lining = lining == null ? null : lining.trim();
    }

    public Date getLiningofstitchingTime() {
        return liningofstitchingTime;
    }

    public void setLiningofstitchingTime(Date liningofstitchingTime) {
        this.liningofstitchingTime = liningofstitchingTime;
    }

    public String getSuppliesoflining() {
        return suppliesoflining;
    }

    public void setSuppliesoflining(String suppliesoflining) {
        this.suppliesoflining = suppliesoflining == null ? null : suppliesoflining.trim();
    }

    public Date getClothTime() {
        return clothTime;
    }

    public void setClothTime(Date clothTime) {
        this.clothTime = clothTime;
    }

    public BigDecimal getSam() {
        return sam;
    }

    public void setSam(BigDecimal sam) {
        this.sam = sam;
    }

    public BigDecimal getSamoflocal() {
        return samoflocal;
    }

    public void setSamoflocal(BigDecimal samoflocal) {
        this.samoflocal = samoflocal;
    }

    public BigDecimal getSah() {
        return sah;
    }

    public void setSah(BigDecimal sah) {
        this.sah = sah;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getEmbroider() {
        return embroider;
    }

    public void setEmbroider(String embroider) {
        this.embroider = embroider == null ? null : embroider.trim();
    }

    public Integer getEmbroiderDaynum() {
        return embroiderDaynum;
    }

    public void setEmbroiderDaynum(Integer embroiderDaynum) {
        this.embroiderDaynum = embroiderDaynum;
    }

    public Date getEmbroiderTime() {
        return embroiderTime;
    }

    public void setEmbroiderTime(Date embroiderTime) {
        this.embroiderTime = embroiderTime;
    }

    public String getFactoryEmbroider() {
        return factoryEmbroider;
    }

    public void setFactoryEmbroider(String factoryEmbroider) {
        this.factoryEmbroider = factoryEmbroider == null ? null : factoryEmbroider.trim();
    }

    public String getFactoryEmbroider2() {
        return factoryEmbroider2;
    }

    public void setFactoryEmbroider2(String factoryEmbroider2) {
        this.factoryEmbroider2 = factoryEmbroider2 == null ? null : factoryEmbroider2.trim();
    }

    public String getPrintafterembroider() {
        return printafterembroider;
    }

    public void setPrintafterembroider(String printafterembroider) {
        this.printafterembroider = printafterembroider == null ? null : printafterembroider.trim();
    }

    public Integer getPrintafterembroiderDaynum() {
        return printafterembroiderDaynum;
    }

    public void setPrintafterembroiderDaynum(Integer printafterembroiderDaynum) {
        this.printafterembroiderDaynum = printafterembroiderDaynum;
    }

    public String getFactoryPrint() {
        return factoryPrint;
    }

    public void setFactoryPrint(String factoryPrint) {
        this.factoryPrint = factoryPrint == null ? null : factoryPrint.trim();
    }

    public String getFactoryPrint2() {
        return factoryPrint2;
    }

    public void setFactoryPrint2(String factoryPrint2) {
        this.factoryPrint2 = factoryPrint2 == null ? null : factoryPrint2.trim();
    }

    public Integer getBackpartDaynum() {
        return backpartDaynum;
    }

    public void setBackpartDaynum(Integer backpartDaynum) {
        this.backpartDaynum = backpartDaynum;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getCuttingqty() {
        return cuttingqty;
    }

    public void setCuttingqty(Integer cuttingqty) {
        this.cuttingqty = cuttingqty;
    }

    public Boolean getIsFinishCutting() {
        return isFinishCutting;
    }

    public void setIsFinishCutting(Boolean isFinishCutting) {
        this.isFinishCutting = isFinishCutting;
    }

    public Integer getAdvancecuttingDaynum() {
        return advancecuttingDaynum;
    }

    public void setAdvancecuttingDaynum(Integer advancecuttingDaynum) {
        this.advancecuttingDaynum = advancecuttingDaynum;
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
            && (this.getSummaryid() == null ? other.getSummaryid() == null : this.getSummaryid().equals(other.getSummaryid()))
            && (this.getIsPlanning() == null ? other.getIsPlanning() == null : this.getIsPlanning().equals(other.getIsPlanning()))
            && (this.getProductionlineid() == null ? other.getProductionlineid() == null : this.getProductionlineid().equals(other.getProductionlineid()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getQtyFinish() == null ? other.getQtyFinish() == null : this.getQtyFinish().equals(other.getQtyFinish()))
            && (this.getSeason() == null ? other.getSeason() == null : this.getSeason().equals(other.getSeason()))
            && (this.getClientname() == null ? other.getClientname() == null : this.getClientname().equals(other.getClientname()))
            && (this.getClientstyleno() == null ? other.getClientstyleno() == null : this.getClientstyleno().equals(other.getClientstyleno()))
            && (this.getOrderno() == null ? other.getOrderno() == null : this.getOrderno().equals(other.getOrderno()))
            && (this.getOrdernum() == null ? other.getOrdernum() == null : this.getOrdernum().equals(other.getOrdernum()))
            && (this.getOrderkind() == null ? other.getOrderkind() == null : this.getOrderkind().equals(other.getOrderkind()))
            && (this.getStyleno() == null ? other.getStyleno() == null : this.getStyleno().equals(other.getStyleno()))
            && (this.getGoodname() == null ? other.getGoodname() == null : this.getGoodname().equals(other.getGoodname()))
            && (this.getStyle() == null ? other.getStyle() == null : this.getStyle().equals(other.getStyle()))
            && (this.getDeliveryofcontractTime() == null ? other.getDeliveryofcontractTime() == null : this.getDeliveryofcontractTime().equals(other.getDeliveryofcontractTime()))
            && (this.getDeliveryoffactoryTime() == null ? other.getDeliveryoffactoryTime() == null : this.getDeliveryoffactoryTime().equals(other.getDeliveryoffactoryTime()))
            && (this.getArrivewarehouseTime() == null ? other.getArrivewarehouseTime() == null : this.getArrivewarehouseTime().equals(other.getArrivewarehouseTime()))
            && (this.getQtyofbatcheddelivery() == null ? other.getQtyofbatcheddelivery() == null : this.getQtyofbatcheddelivery().equals(other.getQtyofbatcheddelivery()))
            && (this.getLining() == null ? other.getLining() == null : this.getLining().equals(other.getLining()))
            && (this.getLiningofstitchingTime() == null ? other.getLiningofstitchingTime() == null : this.getLiningofstitchingTime().equals(other.getLiningofstitchingTime()))
            && (this.getSuppliesoflining() == null ? other.getSuppliesoflining() == null : this.getSuppliesoflining().equals(other.getSuppliesoflining()))
            && (this.getClothTime() == null ? other.getClothTime() == null : this.getClothTime().equals(other.getClothTime()))
            && (this.getSam() == null ? other.getSam() == null : this.getSam().equals(other.getSam()))
            && (this.getSamoflocal() == null ? other.getSamoflocal() == null : this.getSamoflocal().equals(other.getSamoflocal()))
            && (this.getSah() == null ? other.getSah() == null : this.getSah().equals(other.getSah()))
            && (this.getApproveTime() == null ? other.getApproveTime() == null : this.getApproveTime().equals(other.getApproveTime()))
            && (this.getEmbroider() == null ? other.getEmbroider() == null : this.getEmbroider().equals(other.getEmbroider()))
            && (this.getEmbroiderDaynum() == null ? other.getEmbroiderDaynum() == null : this.getEmbroiderDaynum().equals(other.getEmbroiderDaynum()))
            && (this.getEmbroiderTime() == null ? other.getEmbroiderTime() == null : this.getEmbroiderTime().equals(other.getEmbroiderTime()))
            && (this.getFactoryEmbroider() == null ? other.getFactoryEmbroider() == null : this.getFactoryEmbroider().equals(other.getFactoryEmbroider()))
            && (this.getFactoryEmbroider2() == null ? other.getFactoryEmbroider2() == null : this.getFactoryEmbroider2().equals(other.getFactoryEmbroider2()))
            && (this.getPrintafterembroider() == null ? other.getPrintafterembroider() == null : this.getPrintafterembroider().equals(other.getPrintafterembroider()))
            && (this.getPrintafterembroiderDaynum() == null ? other.getPrintafterembroiderDaynum() == null : this.getPrintafterembroiderDaynum().equals(other.getPrintafterembroiderDaynum()))
            && (this.getFactoryPrint() == null ? other.getFactoryPrint() == null : this.getFactoryPrint().equals(other.getFactoryPrint()))
            && (this.getFactoryPrint2() == null ? other.getFactoryPrint2() == null : this.getFactoryPrint2().equals(other.getFactoryPrint2()))
            && (this.getBackpartDaynum() == null ? other.getBackpartDaynum() == null : this.getBackpartDaynum().equals(other.getBackpartDaynum()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getCuttingqty() == null ? other.getCuttingqty() == null : this.getCuttingqty().equals(other.getCuttingqty()))
            && (this.getIsFinishCutting() == null ? other.getIsFinishCutting() == null : this.getIsFinishCutting().equals(other.getIsFinishCutting()))
            && (this.getAdvancecuttingDaynum() == null ? other.getAdvancecuttingDaynum() == null : this.getAdvancecuttingDaynum().equals(other.getAdvancecuttingDaynum()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSummaryid() == null) ? 0 : getSummaryid().hashCode());
        result = prime * result + ((getIsPlanning() == null) ? 0 : getIsPlanning().hashCode());
        result = prime * result + ((getProductionlineid() == null) ? 0 : getProductionlineid().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getQtyFinish() == null) ? 0 : getQtyFinish().hashCode());
        result = prime * result + ((getSeason() == null) ? 0 : getSeason().hashCode());
        result = prime * result + ((getClientname() == null) ? 0 : getClientname().hashCode());
        result = prime * result + ((getClientstyleno() == null) ? 0 : getClientstyleno().hashCode());
        result = prime * result + ((getOrderno() == null) ? 0 : getOrderno().hashCode());
        result = prime * result + ((getOrdernum() == null) ? 0 : getOrdernum().hashCode());
        result = prime * result + ((getOrderkind() == null) ? 0 : getOrderkind().hashCode());
        result = prime * result + ((getStyleno() == null) ? 0 : getStyleno().hashCode());
        result = prime * result + ((getGoodname() == null) ? 0 : getGoodname().hashCode());
        result = prime * result + ((getStyle() == null) ? 0 : getStyle().hashCode());
        result = prime * result + ((getDeliveryofcontractTime() == null) ? 0 : getDeliveryofcontractTime().hashCode());
        result = prime * result + ((getDeliveryoffactoryTime() == null) ? 0 : getDeliveryoffactoryTime().hashCode());
        result = prime * result + ((getArrivewarehouseTime() == null) ? 0 : getArrivewarehouseTime().hashCode());
        result = prime * result + ((getQtyofbatcheddelivery() == null) ? 0 : getQtyofbatcheddelivery().hashCode());
        result = prime * result + ((getLining() == null) ? 0 : getLining().hashCode());
        result = prime * result + ((getLiningofstitchingTime() == null) ? 0 : getLiningofstitchingTime().hashCode());
        result = prime * result + ((getSuppliesoflining() == null) ? 0 : getSuppliesoflining().hashCode());
        result = prime * result + ((getClothTime() == null) ? 0 : getClothTime().hashCode());
        result = prime * result + ((getSam() == null) ? 0 : getSam().hashCode());
        result = prime * result + ((getSamoflocal() == null) ? 0 : getSamoflocal().hashCode());
        result = prime * result + ((getSah() == null) ? 0 : getSah().hashCode());
        result = prime * result + ((getApproveTime() == null) ? 0 : getApproveTime().hashCode());
        result = prime * result + ((getEmbroider() == null) ? 0 : getEmbroider().hashCode());
        result = prime * result + ((getEmbroiderDaynum() == null) ? 0 : getEmbroiderDaynum().hashCode());
        result = prime * result + ((getEmbroiderTime() == null) ? 0 : getEmbroiderTime().hashCode());
        result = prime * result + ((getFactoryEmbroider() == null) ? 0 : getFactoryEmbroider().hashCode());
        result = prime * result + ((getFactoryEmbroider2() == null) ? 0 : getFactoryEmbroider2().hashCode());
        result = prime * result + ((getPrintafterembroider() == null) ? 0 : getPrintafterembroider().hashCode());
        result = prime * result + ((getPrintafterembroiderDaynum() == null) ? 0 : getPrintafterembroiderDaynum().hashCode());
        result = prime * result + ((getFactoryPrint() == null) ? 0 : getFactoryPrint().hashCode());
        result = prime * result + ((getFactoryPrint2() == null) ? 0 : getFactoryPrint2().hashCode());
        result = prime * result + ((getBackpartDaynum() == null) ? 0 : getBackpartDaynum().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getCuttingqty() == null) ? 0 : getCuttingqty().hashCode());
        result = prime * result + ((getIsFinishCutting() == null) ? 0 : getIsFinishCutting().hashCode());
        result = prime * result + ((getAdvancecuttingDaynum() == null) ? 0 : getAdvancecuttingDaynum().hashCode());
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
        sb.append(", summaryid=").append(summaryid);
        sb.append(", isPlanning=").append(isPlanning);
        sb.append(", productionlineid=").append(productionlineid);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", qtyFinish=").append(qtyFinish);
        sb.append(", season=").append(season);
        sb.append(", clientname=").append(clientname);
        sb.append(", clientstyleno=").append(clientstyleno);
        sb.append(", orderno=").append(orderno);
        sb.append(", ordernum=").append(ordernum);
        sb.append(", orderkind=").append(orderkind);
        sb.append(", styleno=").append(styleno);
        sb.append(", goodname=").append(goodname);
        sb.append(", style=").append(style);
        sb.append(", deliveryofcontractTime=").append(deliveryofcontractTime);
        sb.append(", deliveryoffactoryTime=").append(deliveryoffactoryTime);
        sb.append(", arrivewarehouseTime=").append(arrivewarehouseTime);
        sb.append(", qtyofbatcheddelivery=").append(qtyofbatcheddelivery);
        sb.append(", lining=").append(lining);
        sb.append(", liningofstitchingTime=").append(liningofstitchingTime);
        sb.append(", suppliesoflining=").append(suppliesoflining);
        sb.append(", clothTime=").append(clothTime);
        sb.append(", sam=").append(sam);
        sb.append(", samoflocal=").append(samoflocal);
        sb.append(", sah=").append(sah);
        sb.append(", approveTime=").append(approveTime);
        sb.append(", embroider=").append(embroider);
        sb.append(", embroiderDaynum=").append(embroiderDaynum);
        sb.append(", embroiderTime=").append(embroiderTime);
        sb.append(", factoryEmbroider=").append(factoryEmbroider);
        sb.append(", factoryEmbroider2=").append(factoryEmbroider2);
        sb.append(", printafterembroider=").append(printafterembroider);
        sb.append(", printafterembroiderDaynum=").append(printafterembroiderDaynum);
        sb.append(", factoryPrint=").append(factoryPrint);
        sb.append(", factoryPrint2=").append(factoryPrint2);
        sb.append(", backpartDaynum=").append(backpartDaynum);
        sb.append(", memo=").append(memo);
        sb.append(", cuttingqty=").append(cuttingqty);
        sb.append(", isFinishCutting=").append(isFinishCutting);
        sb.append(", advancecuttingDaynum=").append(advancecuttingDaynum);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Boolean getFinishCutting() {
        return isFinishCutting;
    }

    public void setFinishCutting(Boolean finishCutting) {
        isFinishCutting = finishCutting;
    }

    public BigDecimal getEfficiencyOfClass() {
        return efficiencyOfClass;
    }

    public void setEfficiencyOfClass(BigDecimal efficiencyOfClass) {
        this.efficiencyOfClass = efficiencyOfClass;
    }

    public String getProductStyleName() {
        return productStyleName;
    }

    public void setProductStyleName(String productStyleName) {
        this.productStyleName = productStyleName;
    }
}