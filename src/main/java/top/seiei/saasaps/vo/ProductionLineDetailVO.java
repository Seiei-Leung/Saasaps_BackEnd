package top.seiei.saasaps.vo;

import top.seiei.saasaps.bean.EfficiencyOfLine;
import top.seiei.saasaps.bean.PeopleNumOfLine;
import top.seiei.saasaps.bean.WorkhoursOfLine;

import java.util.List;

public class ProductionLineDetailVO {

    List<EfficiencyOfLine> efficiencyOfLineList;

    List<PeopleNumOfLine> peopleNumOfLineList;

    List<WorkhoursOfLine> workhoursOfLineList;

    public List<EfficiencyOfLine> getEfficiencyOfLineList() {
        return efficiencyOfLineList;
    }

    public void setEfficiencyOfLineList(List<EfficiencyOfLine> efficiencyOfLineList) {
        this.efficiencyOfLineList = efficiencyOfLineList;
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
}
