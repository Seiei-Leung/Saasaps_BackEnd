package top.seiei.saasaps.dao;

import org.apache.ibatis.annotations.Param;
import top.seiei.saasaps.bean.PeopleNumOfLine;

import java.util.Date;
import java.util.List;

public interface PeopleNumOfLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeopleNumOfLine record);

    int insertSelective(PeopleNumOfLine record);

    PeopleNumOfLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeopleNumOfLine record);

    int updateByPrimaryKey(PeopleNumOfLine record);

    PeopleNumOfLine selectPeopleNumByTime(@Param("lineId") Integer lineId, @Param("date") Date date);

    List<PeopleNumOfLine> selectByLineId(Integer lineId);

    List<PeopleNumOfLine> selectByLineIdAndTime(@Param("lineId") Integer lineId, @Param("time") Date time);
}