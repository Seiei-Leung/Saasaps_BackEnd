package top.seiei.saasaps.dao;

import top.seiei.saasaps.bean.ProductStyle;

import java.util.List;

public interface ProductStyleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductStyle record);

    int insertSelective(ProductStyle record);

    ProductStyle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductStyle record);

    int updateByPrimaryKey(ProductStyle record);

    List<ProductStyle> selectAll();

    ProductStyle selectByStyleName(String styleName);


}