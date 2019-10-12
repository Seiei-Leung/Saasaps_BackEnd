package top.seiei.saasaps.bo;

import java.io.Serializable;
import java.util.Date;

public class ProductionPlanningForUpdate implements Serializable {

    private Integer id;

    private Integer productionLineId;

    private Integer productionPlanningDetailId;

    private Date startTime;

    private Date endTime;

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

    public Integer getProductionPlanningDetailId() {
        return productionPlanningDetailId;
    }

    public void setProductionPlanningDetailId(Integer productionPlanningDetailId) {
        this.productionPlanningDetailId = productionPlanningDetailId;
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
}
