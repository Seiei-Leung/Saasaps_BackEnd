package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.ColorSetting;

public interface ColorSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ColorSetting record);

    int insertSelective(ColorSetting record);

    ColorSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ColorSetting record);

    int updateByPrimaryKey(ColorSetting record);

    ColorSetting selectByUserId(Integer userId);

    ColorSetting selectDefault();
}