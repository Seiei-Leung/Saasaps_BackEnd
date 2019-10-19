package top.seiei.saasaps.dao;

import org.apache.ibatis.annotations.Param;
import top.seiei.saasaps.bean.WorkhoursOfLine;

import java.util.Date;
import java.util.List;

public interface WorkhoursOfLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkhoursOfLine record);

    int insertSelective(WorkhoursOfLine record);

    WorkhoursOfLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkhoursOfLine record);

    int updateByPrimaryKey(WorkhoursOfLine record);

    WorkhoursOfLine selectWorkHoursByTime(@Param("lineId") Integer lineId, @Param("date") Date date);

    WorkhoursOfLine selectWorkHoursByRecentTime(Integer lineId);

    List<WorkhoursOfLine> selectByLineId(Integer lineId);

    List<WorkhoursOfLine> selectByLineIdAndTime(@Param("lineId") Integer lineId, @Param("time") Date time);
}