package top.seiei.saasaps.dao;

import org.apache.ibatis.annotations.Param;
import top.seiei.saasaps.bean.EfficiencyOfLine;

import java.util.List;

public interface EfficiencyOfLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EfficiencyOfLine record);

    int insertSelective(EfficiencyOfLine record);

    EfficiencyOfLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EfficiencyOfLine record);

    int updateByPrimaryKey(EfficiencyOfLine record);

    List<EfficiencyOfLine> selectByLineId(Integer lineId);

    EfficiencyOfLine selectByLineIdAndStyleName(@Param("lineId") Integer lineId, @Param("styleName") String styleName);
}