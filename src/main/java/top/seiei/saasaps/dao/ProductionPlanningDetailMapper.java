package top.seiei.saasaps.dao;

import org.apache.ibatis.annotations.Param;
import top.seiei.saasaps.bean.ProductionPlanningDetail;

import java.util.Date;
import java.util.List;

public interface ProductionPlanningDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionPlanningDetail record);

    int insertSelective(ProductionPlanningDetail record);

    ProductionPlanningDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionPlanningDetail record);

    int updateByPrimaryKey(ProductionPlanningDetail record);

    List<ProductionPlanningDetail> selectBySummaryId(Integer id);

    int deleteBySummaryId(Integer id);

    List<ProductionPlanningDetail> selectByLineIdAndTime(@Param("id") Integer id, @Param("time") Date time);

    List<ProductionPlanningDetail> selectAllForAddProgress();
}