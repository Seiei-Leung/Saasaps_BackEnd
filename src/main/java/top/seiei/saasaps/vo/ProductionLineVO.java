package top.seiei.saasaps.vo;

import top.seiei.saasaps.bean.EfficiencyOfLine;
import top.seiei.saasaps.bean.PeopleNumOfLine;
import top.seiei.saasaps.bean.WorkhoursOfLine;

import java.math.BigDecimal;
import java.util.List;

public class ProductionLineVO {

    private Integer id;

    private String workgroup;

    private String workshop;

    private String lineCode;

    private BigDecimal workhours;

    private Integer peopleNum;

    private Boolean isInvalid;

    private List<EfficiencyOfLine> efficiencyOfLineList;

    private List<PeopleNumOfLine> peopleNumOfLineList;

    private List<WorkhoursOfLine> workhoursOfLineList;

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
        this.workgroup = workgroup;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public List<EfficiencyOfLine> getEfficiencyOfLineList() {
        return efficiencyOfLineList;
    }

    public void setEfficiencyOfLineList(List<EfficiencyOfLine> efficiencyOfLineList) {
        this.efficiencyOfLineList = efficiencyOfLineList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public List<PeopleNumOfLine> getPeopleNumOfLineList() {
        return peopleNumOfLineList;
    }

    public void setPeopleNumOfLineList(List<PeopleNumOfLine> peopleNumOfLineList) {
        this.peopleNumOfLineList = peopleNumOfLineList;
    }

    public List<WorkhoursOfLine> getWorkhoursOfLineList() {
        return workhoursOfLineList;
    }

    public void setWorkhoursOfLineList(List<WorkhoursOfLine> workhoursOfLineList) {
        this.workhoursOfLineList = workhoursOfLineList;
    }

    public Boolean getInvalid() {
        return isInvalid;
    }

    public void setInvalid(Boolean invalid) {
        isInvalid = invalid;
    }
}
