package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.ProductClass;

import java.util.List;

public interface ProductClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductClass record);

    int insertSelective(ProductClass record);

    ProductClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductClass record);

    int updateByPrimaryKey(ProductClass record);

    List<ProductClass> selectAll();

    ProductClass selectByProductClassName(String name);
}