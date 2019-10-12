package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.WorkingDateSetting;

import java.util.List;

public interface WorkingDateSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkingDateSetting record);

    int insertSelective(WorkingDateSetting record);

    WorkingDateSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkingDateSetting record);

    int updateByPrimaryKey(WorkingDateSetting record);

    List<WorkingDateSetting> selectAll();

    WorkingDateSetting selectByYear(Integer year);

}