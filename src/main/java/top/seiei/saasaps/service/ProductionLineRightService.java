package top.seiei.saasaps.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.seiei.saasaps.bean.ProductionLine;
import top.seiei.saasaps.bean.ProductionLineRight;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.bo.TreeBeanForProductionLineRight;
import top.seiei.saasaps.dao.ProductionLineMapper;
import top.seiei.saasaps.dao.ProductionLineRightMapper;
import top.seiei.saasaps.dao.UserMapper;

import javax.annotation.Resource;
import java.util.*;

@Service("productionLineRightService")
public class ProductionLineRightService {

    @Resource
    private ProductionLineRightMapper productionLineRightMapper;

    @Resource
    private ProductionLineMapper productionLineMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 	获取全部生产线树状图结构的数据
     * @return 全部生产线树状图结构的数据
     */
    public ServerResponse getAll() {
        // 获取生产线数据
        List<ProductionLine> productionLineList = productionLineMapper.getAll();
        // 制作组别字段唯一 set
        Set<String> workgroupSet = new HashSet<>();
        for (ProductionLine item : productionLineList) {
            workgroupSet.add(item.getWorkgroup());
        }
        // 返回数据
        List<TreeBeanForProductionLineRight> resultList = new ArrayList<>();
        workgroupSet.forEach((String workGroupTitle) -> {
            // 获取指定的组别字段的所有生产线
            List<ProductionLine> productionLineListForWorkGroup = selectByWorkGroupName(productionLineList, workGroupTitle);
            // 制作组别级别的节点 treebean
            TreeBeanForProductionLineRight treeBeanForWorkGroup = new TreeBeanForProductionLineRight();
            treeBeanForWorkGroup.setTitle(workGroupTitle);
            List<TreeBeanForProductionLineRight> childrenForWorkGroup = new ArrayList<>();
            // 设置车间字段唯一 set
            Set<String> workshopSet = new HashSet<>();
            for (ProductionLine workshopSetItem : productionLineListForWorkGroup) {
                workshopSet.add(workshopSetItem.getWorkshop());
            }
            // 循环车间字段 set
            workshopSet.forEach((String workShopTitle) -> {
                // 获取指定组别，车间的全部生产线
                List<ProductionLine> planDateGroupListForWorkshop = selectByWorkGroupAndWorkshop(productionLineListForWorkGroup, workGroupTitle, workShopTitle);
                // 设置车间级别的节点 treebean
                TreeBeanForProductionLineRight treeBeanForWorkshop = new TreeBeanForProductionLineRight();
                treeBeanForWorkshop.setTitle(workShopTitle);
                List<TreeBeanForProductionLineRight> childrenForWorkshop = new ArrayList<>();
                // 获取生产线级别的节点 treebean
                planDateGroupListForWorkshop.forEach((ProductionLine itemForLine) -> {
                    TreeBeanForProductionLineRight treeBeanForLine = new TreeBeanForProductionLineRight();
                    treeBeanForLine.setTitle(itemForLine.getLineCode());
                    treeBeanForLine.setId(itemForLine.getId());
                    childrenForWorkshop.add(treeBeanForLine);
                });
                treeBeanForWorkshop.setChildren(childrenForWorkshop);
                childrenForWorkGroup.add(treeBeanForWorkshop);
            });

            treeBeanForWorkGroup.setChildren(childrenForWorkGroup);
            resultList.add(treeBeanForWorkGroup);
        });
        TreeBeanForProductionLineRight resultBean = new TreeBeanForProductionLineRight();
        resultBean.setTitle("全部");
        resultBean.setChildren(resultList);
        return ServerResponse.createdBySuccess(resultBean);
    }

    /**
     * 	根据用户 ID 获取生产线权限
     * @param id 用户 ID
     * @return 权限列表
     */
    @Transactional
    public ServerResponse<List<ProductionLineRight>> selectByUserId(Integer id) {
        List<ProductionLineRight> productionLineRightList = productionLineRightMapper.selectByUserId(id);
        return ServerResponse.createdBySuccess(productionLineRightList);
    }

    /**
     * 根据用户 ID 获取生产线权限，生产线 ID 列表
     * @param id 用户 ID
     * @return 权限列表
     */
    public ServerResponse<List<Integer>> selectProductionLineIdByUserId(Integer id) {
        List<Integer> productionLineIdList = productionLineRightMapper.selectProductionLineIdByUserId(id);
        return ServerResponse.createdBySuccess(productionLineIdList);
    }

    /**
     * 更新权限
     * @param admin 管理员
     * @param userId 被更新的用户 ID
     * @param list 权限列表
     * @return 是否更新成功
     */
    @Transactional
    public ServerResponse<String> update(User admin, Integer userId, List<Integer> list) {
        if (userId == null) {
            return ServerResponse.createdByError( "参数错误");
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createdByError("查无该用户");
        }
        ServerResponse<List<ProductionLineRight>> serverResponse = selectByUserId(userId);
        List<Integer> listFromDataBase = new ArrayList<>();
        int resultCount;
        if (serverResponse.isSuccess()) {
            for (ProductionLineRight item : serverResponse.getData()) {
                listFromDataBase.add(item.getId());
                if (!list.contains(item.getId())) {
                    resultCount = productionLineRightMapper.deleteByPrimaryKey(item.getId());
                    if (resultCount == 0) {
                        return ServerResponse.createdByError("更新失败");
                    }
                }
            }
        } else {
            return ServerResponse.createdByError("更新失败");
        }
        if (list != null ) {
            for (Integer item : list) {
                if (!listFromDataBase.contains(item)) {
                    ProductionLineRight productionLineRight = new ProductionLineRight();
                    productionLineRight.setUserId(userId);
                    productionLineRight.setProductLineId(item);
                    productionLineRight.setUpdateUserId(admin.getId());
                    productionLineRight.setCreateTime(new Date());
                    productionLineRight.setUpdateTime(new Date());
                    resultCount = productionLineRightMapper.insertSelective(productionLineRight);
                    if (resultCount == 0) {
                        return ServerResponse.createdByError("更新失败");
                    }
                }
            }
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 根据组别名称筛选生产线
     * @param productionLineSource 原生产线集合
     * @param workGroup 组别名称
     * @return 筛选生产线集合
     */
    private List<ProductionLine> selectByWorkGroupName(List<ProductionLine> productionLineSource, String workGroup) {
        List<ProductionLine> result = new ArrayList<>();
        for (ProductionLine item : productionLineSource) {
            if (StringUtils.equals(item.getWorkgroup(), workGroup)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 根据组别名称和车间名称筛选生产线
     * @param productionLineSource 原生产线集合
     * @param workGroup 组别名称
     * @param workShop 车间名称
     * @return 筛选生产线集合
     */
    private List<ProductionLine> selectByWorkGroupAndWorkshop(List<ProductionLine> productionLineSource, String workGroup, String workShop) {
        List<ProductionLine> result = new ArrayList<>();
        for (ProductionLine item : productionLineSource) {
            if (StringUtils.equals(item.getWorkgroup(), workGroup) && StringUtils.equals(item.getWorkshop(), workShop)) {
                result.add(item);
            }
        }
        return result;
    }
}
