package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.ProductionPlanningDetail;

import java.util.List;

public interface ProductionPlanningDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionPlanningDetail record);

    int insertSelective(ProductionPlanningDetail record);

    ProductionPlanningDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionPlanningDetail record);

    int updateByPrimaryKey(ProductionPlanningDetail record);

    List<ProductionPlanningDetail> selectAllToDo();

    List<ProductionPlanningDetail>  selectByProductionPlanningId(Integer id);
}