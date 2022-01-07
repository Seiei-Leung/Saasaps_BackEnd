package top.seiei.saasaps.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.seiei.saasaps.bean.ProductClass;
import top.seiei.saasaps.bean.ProductClassEfficiency;
import top.seiei.saasaps.bean.ProductStyle;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ProductClassEfficiencyMapper;
import top.seiei.saasaps.dao.ProductClassMapper;
import top.seiei.saasaps.dao.ProductStyleMapper;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("productClassService")
public class ProductClassService {

    @Resource
    private ProductClassMapper productClassMapper;

    @Resource
    private ProductStyleMapper productStyleMapper;

    @Resource
    private ProductClassEfficiencyMapper productClassEfficiencyMapper;

    /**
     * 获取所有排产品类概括
     * @return 所有排产品类
     */
    public ServerResponse selectAll() {
        List<ProductClass> productClassList = productClassMapper.selectAll();
        return ServerResponse.createdBySuccess(productClassList);
    }

    /**
     * 获去排产品类详情
      * @param productClassId 排产品类 id
     * @return
     */
    public ServerResponse getDetailById(Integer productClassId) {
        List<ProductClassEfficiency> productClassEfficiencyList = productClassEfficiencyMapper.selectByProductClassId(productClassId);
        return ServerResponse.createdBySuccess(productClassEfficiencyList);
    }

    /**
     * 删除排产品类
     * @param id 排产品类 ID
     * @return 是否删除成功
     */
    public ServerResponse deleteProductClass(Integer id) {
        ProductClass productClass = productClassMapper.selectByPrimaryKey(id);
        if (productClass == null) {
            return  ServerResponse.createdByError("该排产品类不存在");
        }
        int result = productClassMapper.deleteByPrimaryKey(id);
        productClassEfficiencyMapper.deleteByProductClassId(id);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");
    }

    /**
     * 删除产品类详情
     * @param id 详情 ID
     * @return
     */
    public ServerResponse deleteDetail(Integer id) {
        ProductClassEfficiency productClassEfficiency = productClassEfficiencyMapper.selectByPrimaryKey(id);
        if (productClassEfficiency == null) {
            return ServerResponse.createdByError("该详情不存在");
        }
        int result = productClassEfficiencyMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");

    }

    /**
     * 更新排产品类
     * @param user 操作用户信息
     * @param updateProductClass 更新排产品类信息
     * @return 是否更新成功
     */
    public ServerResponse updateProductClass(User user, ProductClass updateProductClass) {
        ProductClass productClass = productClassMapper.selectByPrimaryKey(updateProductClass.getId());
        if (productClass == null) {
            return ServerResponse.createdByError("该排产品类不存在");
        }
        updateProductClass.setUpdateUserId(user.getId());
        updateProductClass.setUpdateTime(new Date());
        int result = productClassMapper.updateByPrimaryKeySelective(updateProductClass);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 更新产品类详情
     * @param user 用户信息
     * @param updateProductClassEfficiency 产品类详情
     * @return
     */
    public ServerResponse updateDetail(User user, ProductClassEfficiency updateProductClassEfficiency) {
        ProductClassEfficiency productClassEfficiency = productClassEfficiencyMapper.selectByPrimaryKey(updateProductClassEfficiency.getId());
        if (productClassEfficiency == null) {
            return ServerResponse.createdByError("该产品类详情不存在");
        }
        ProductClass productClass = productClassMapper.selectByPrimaryKey(productClassEfficiency.getProductClassId());

        if (updateProductClassEfficiency.getEfficiency() == null || updateProductClassEfficiency.getStartQuantity() == null || updateProductClassEfficiency.getEndQuantity() == null) {
            return ServerResponse.createdByError("参数不能为空");
        }

        // 检查计件范围
        ServerResponse serverResponse = getProductClassEfficiencyByProductClassNameAndQtyPlan2(productClass.getName(), updateProductClassEfficiency.getStartQuantity());
        if (serverResponse.isSuccess() && ((ProductClassEfficiency) serverResponse.getData()).getId() != updateProductClassEfficiency.getId()) {
            return ServerResponse.createdByError("该产品类的计件范围效率与其它已定义的计件范围效率发生冲突");
        }
        serverResponse = getProductClassEfficiencyByProductClassNameAndQtyPlan2(productClass.getName(), updateProductClassEfficiency.getEndQuantity());
        if (serverResponse.isSuccess() && ((ProductClassEfficiency) serverResponse.getData()).getId() != updateProductClassEfficiency.getId()) {
            return ServerResponse.createdByError("该产品类的计件范围效率与其它已定义的计件范围效率发生冲突");
        }

        updateProductClassEfficiency.setUpdateTime(new Date());
        updateProductClassEfficiency.setUpdateUserId(user.getId());
        int result = productClassEfficiencyMapper.updateByPrimaryKeySelective(updateProductClassEfficiency);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccessMessage("更新成功");

    }

    /**
     * 新增排产品类
     * @param user 操作用户信息
     * @param insertProductClass 更新排产品类信息
     * @return 是否更新成功
     */
    public ServerResponse insertProductClass(User user, ProductClass insertProductClass) {
        ProductStyle productStyle = productStyleMapper.selectByStyleName(insertProductClass.getProductStyleName());
        ProductClass productClass = productClassMapper.selectByProductClassName(insertProductClass.getName());
        if (productClass != null) {
            return ServerResponse.createdByError("该产品类已存在");
        }
        if (
                productStyle == null ||
                insertProductClass.getEfficiency() == null ||
                StringUtils.isBlank(insertProductClass.getName()) ||
                insertProductClass.getProphaseLowEfficiency() == null
            ) {
            return ServerResponse.createdByError("参数错误");
        }
        insertProductClass.setUpdateUserId(user.getId());
        insertProductClass.setCreateTime(new Date());
        insertProductClass.setUpdateTime(new Date());
        int result = productClassMapper.insertSelective(insertProductClass);
        if (result == 0) {
            return ServerResponse.createdByError("添加失败");
        }
        return ServerResponse.createdBySuccess("添加成功", insertProductClass.getId());
    }

    /**
     * 新增详情
     * @param user 用户信息
     * @param productClassEfficiency 产品类详情
     * @return
     */
    public ServerResponse insertDetail(User user, ProductClassEfficiency productClassEfficiency) {
        ProductClass productClass = productClassMapper.selectByPrimaryKey(productClassEfficiency.getProductClassId());
        if (productClass == null) {
            return ServerResponse.createdByError("该排产类不存在");
        }
        if (
                productClassEfficiency.getStartQuantity() == null ||
                productClassEfficiency.getEndQuantity() == null ||
                productClassEfficiency.getEfficiency() == null
        ) {
            return ServerResponse.createdByError("参数不能为空");
        }
        ServerResponse serverResponse = getProductClassEfficiencyByProductClassNameAndQtyPlan(productClass.getName(), productClassEfficiency.getStartQuantity());
        if (serverResponse.isSuccess()) {
            if (productClass.getEfficiency().compareTo((BigDecimal) serverResponse.getData()) != 0) {
                return ServerResponse.createdByError("该产品类的计件范围效率与其它已定义的计件范围效率发生冲突");
            }
        }
        serverResponse = getProductClassEfficiencyByProductClassNameAndQtyPlan(productClass.getName(), productClassEfficiency.getEndQuantity());
        if (serverResponse.isSuccess()) {
            if (productClass.getEfficiency().compareTo((BigDecimal) serverResponse.getData()) != 0) {
                return ServerResponse.createdByError("该产品类的计件范围效率与其它已定义的计件范围效率发生冲突");
            }
        }
        productClassEfficiency.setUpdateUserId(user.getId());
        productClassEfficiency.setCreateTime(new Date());
        productClassEfficiency.setUpdateTime(new Date());
        int result = productClassEfficiencyMapper.insertSelective(productClassEfficiency);
        if (result == 0) {
            return ServerResponse.createdByError("添加失败");
        }
        return ServerResponse.createdBySuccess("添加成功", productClassEfficiency.getId());

    }

    /**
     * 根据产品类名，计划件数获取产品类的效率信息,如果没有对应的效率值，则返回默认效率值
     * @param productionClassName 产品类名
     * @param qtyPlan 计划件数
     * @return
     */
    public ServerResponse<BigDecimal> getProductClassEfficiencyByProductClassNameAndQtyPlan(String productionClassName, Integer qtyPlan) {
        ProductClass productClass = productClassMapper.selectByProductClassName(productionClassName);
        if (productClass == null) {
            return ServerResponse.createdByError("该产品类不存在");
        }
        List<ProductClassEfficiency> productClassEfficiencyList = productClassEfficiencyMapper.selectByProductClassId(productClass.getId());
        ProductClassEfficiency productClassEfficiency = null;
        for (ProductClassEfficiency item : productClassEfficiencyList) {
            if (item.getStartQuantity() <= qtyPlan && qtyPlan <= item.getEndQuantity()) {
                if (productClassEfficiency == null) {
                    productClassEfficiency = item;
                } else {
                    return ServerResponse.createdByError("该产品类当前计划件数对应的效率存在一个以上");
                }
            }
        }
        if (productClassEfficiency == null) {
            return ServerResponse.createdBySuccess(productClass.getEfficiency());
        }
        return ServerResponse.createdBySuccess(productClassEfficiency.getEfficiency());
    }

    /**
     * 根据产品类名，计划件数获取产品类的效率信息,如果没有对应的效率值，则返回错误信息
     * @param productionClassName 产品类名
     * @param qtyPlan 计划件数
     * @return
     */
    public ServerResponse<ProductClassEfficiency> getProductClassEfficiencyByProductClassNameAndQtyPlan2(String productionClassName, Integer qtyPlan) {
        ProductClass productClass = productClassMapper.selectByProductClassName(productionClassName);
        if (productClass == null) {
            return ServerResponse.createdByError("该产品类不存在");
        }
        List<ProductClassEfficiency> productClassEfficiencyList = productClassEfficiencyMapper.selectByProductClassId(productClass.getId());
        ProductClassEfficiency productClassEfficiency = null;
        for (ProductClassEfficiency item : productClassEfficiencyList) {
            if (item.getStartQuantity() <= qtyPlan && qtyPlan <= item.getEndQuantity()) {
                if (productClassEfficiency == null) {
                    productClassEfficiency = item;
                } else {
                    return ServerResponse.createdByError("该产品类当前计划件数对应的效率存在一个以上");
                }
            }
        }
        if (productClassEfficiency == null) {
            return ServerResponse.createdByError("没有定义该效率");
        }
        return ServerResponse.createdBySuccess(productClassEfficiency);
    }
}
