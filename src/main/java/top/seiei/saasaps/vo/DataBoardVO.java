package top.seiei.saasaps.vo;

import java.math.BigDecimal;

public class DataBoardVO {

    private Integer onLineNumber;

    private Integer schedulingNumberOfToday;

    private Integer deliveryOrderNumberOfWeek;

    private Integer completeSewingNumberOfToday;

    private Integer abnormalOrderNumber;

    private BigDecimal capacityOfNextMonth;

    private BigDecimal capacityOfNextThreeMonth;

    private BigDecimal capacityOfNextSixMonth;

    public Integer getOnLineNumber() {
        return onLineNumber;
    }

    public void setOnLineNumber(Integer onLineNumber) {
        this.onLineNumber = onLineNumber;
    }

    public Integer getSchedulingNumberOfToday() {
        return schedulingNumberOfToday;
    }

    public void setSchedulingNumberOfToday(Integer schedulingNumberOfToday) {
        this.schedulingNumberOfToday = schedulingNumberOfToday;
    }

    public Integer getDeliveryOrderNumberOfWeek() {
        return deliveryOrderNumberOfWeek;
    }

    public void setDeliveryOrderNumberOfWeek(Integer deliveryOrderNumberOfWeek) {
        this.deliveryOrderNumberOfWeek = deliveryOrderNumberOfWeek;
    }

    public Integer getCompleteSewingNumberOfToday() {
        return completeSewingNumberOfToday;
    }

    public void setCompleteSewingNumberOfToday(Integer completeSewingNumberOfToday) {
        this.completeSewingNumberOfToday = completeSewingNumberOfToday;
    }

    public Integer getAbnormalOrderNumber() {
        return abnormalOrderNumber;
    }

    public void setAbnormalOrderNumber(Integer abnormalOrderNumber) {
        this.abnormalOrderNumber = abnormalOrderNumber;
    }

    public BigDecimal getCapacityOfNextMonth() {
        return capacityOfNextMonth;
    }

    public void setCapacityOfNextMonth(BigDecimal capacityOfNextMonth) {
        this.capacityOfNextMonth = capacityOfNextMonth;
    }

    public BigDecimal getCapacityOfNextThreeMonth() {
        return capacityOfNextThreeMonth;
    }

    public void setCapacityOfNextThreeMonth(BigDecimal capacityOfNextThreeMonth) {
        this.capacityOfNextThreeMonth = capacityOfNextThreeMonth;
    }

    public BigDecimal getCapacityOfNextSixMonth() {
        return capacityOfNextSixMonth;
    }

    public void setCapacityOfNextSixMonth(BigDecimal capacityOfNextSixMonth) {
        this.capacityOfNextSixMonth = capacityOfNextSixMonth;
    }
}
