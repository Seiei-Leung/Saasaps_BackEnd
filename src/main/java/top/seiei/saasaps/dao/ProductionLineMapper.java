package top.seiei.saasaps.dao;

import org.apache.ibatis.annotations.Param;
import top.seiei.saasaps.bean.ProductionLine;

import java.util.List;

public interface ProductionLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductionLine record);

    int insertSelective(ProductionLine record);

    ProductionLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductionLine record);

    int updateByPrimaryKey(ProductionLine record);

    List<ProductionLine> getAll();

    ProductionLine selectByName(@Param("workGroup") String workGroup, @Param("workShop") String workShop, @Param("line") String line);

    List<ProductionLine> selectByPrimaryKeyList(List<Integer> idList);

    int selectCountByPrimaryKey(Integer id);
}