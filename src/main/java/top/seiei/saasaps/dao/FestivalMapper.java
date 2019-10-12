package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.Festival;

import java.util.List;

public interface FestivalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Festival record);

    int insertSelective(Festival record);

    Festival selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Festival record);

    int updateByPrimaryKey(Festival record);

    List<Festival> selectByWorkingSettingId(Integer id);

    int deleteByWorkingSettingId(Integer id);
}