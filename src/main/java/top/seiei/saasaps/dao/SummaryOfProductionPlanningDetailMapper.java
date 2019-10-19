package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.SummaryOfProductionPlanningDetail;

import java.util.List;

public interface SummaryOfProductionPlanningDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SummaryOfProductionPlanningDetail record);

    int insertSelective(SummaryOfProductionPlanningDetail record);

    SummaryOfProductionPlanningDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SummaryOfProductionPlanningDetail record);

    int updateByPrimaryKey(SummaryOfProductionPlanningDetail record);

    List<SummaryOfProductionPlanningDetail> selectByLikeBillNo(String billNo);

    List<SummaryOfProductionPlanningDetail> selectAll();
}