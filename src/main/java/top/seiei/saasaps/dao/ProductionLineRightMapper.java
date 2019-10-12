package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.ProductionLineRight;

import java.util.List;

public interface ProductionLineRightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionLineRight record);

    int insertSelective(ProductionLineRight record);

    ProductionLineRight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionLineRight record);

    int updateByPrimaryKey(ProductionLineRight record);

    List<ProductionLineRight> selectByUserId(Integer userId);

    List<Integer> selectProductionLineIdByUserId(Integer userId);
}