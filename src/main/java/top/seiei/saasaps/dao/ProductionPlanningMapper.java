package top.seiei.saasaps.dao;

import org.apache.ibatis.annotations.Param;
import top.seiei.saasaps.bean.ProductionPlanning;

import java.util.Date;
import java.util.List;

public interface ProductionPlanningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionPlanning record);

    int insertSelective(ProductionPlanning record);

    ProductionPlanning selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionPlanning record);

    int updateByPrimaryKey(ProductionPlanning record);

    List<ProductionPlanning> selectAllByProductLineIdList(@Param("startDate") Date startDate, @Param("idList") List<Integer> idList);
}