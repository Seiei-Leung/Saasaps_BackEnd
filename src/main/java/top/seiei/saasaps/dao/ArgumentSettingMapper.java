package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.ArgumentSetting;

import java.util.List;

public interface ArgumentSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArgumentSetting record);

    int insertSelective(ArgumentSetting record);

    ArgumentSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArgumentSetting record);

    int updateByPrimaryKey(ArgumentSetting record);

    List<ArgumentSetting> getAll();
}