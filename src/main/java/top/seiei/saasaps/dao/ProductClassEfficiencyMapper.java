package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.ProductClassEfficiency;

import java.util.List;

public interface ProductClassEfficiencyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductClassEfficiency record);

    int insertSelective(ProductClassEfficiency record);

    ProductClassEfficiency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductClassEfficiency record);

    int updateByPrimaryKey(ProductClassEfficiency record);

    List<ProductClassEfficiency> selectByProductClassId(Integer productClassId);

    int deleteByProductClassId(Integer productClassId);
}