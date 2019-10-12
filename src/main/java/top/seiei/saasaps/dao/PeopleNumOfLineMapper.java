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

    PeopleNumOfLine selectPeopleNumByRecentTime(Integer lineId);

    List<PeopleNumOfLine> selectByLineId(Integer lineId);
}