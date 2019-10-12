package top.seiei.saasaps.service;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.seiei.saasaps.bean.*;
import top.seiei.saasaps.bo.ProductionPlanningForUpdate;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ProductClassMapper;
import top.seiei.saasaps.dao.ProductionLineMapper;
import top.seiei.saasaps.dao.ProductionPlanningDetailMapper;
import top.seiei.saasaps.dao.ProductionPlanningMapper;
import top.seiei.saasaps.util.BigDecimalUtils;
import top.seiei.saasaps.vo.ProductionLineVO;
import top.seiei.saasaps.vo.ProductionPlanningDetailVO;

import javax.annotation.Resource;
import java.util.*;

@Service("productionPlanningService")
public class ProductionPlanningService {

    @Resource
    private ProductionPlanningMapper productionPlanningMapper;

    @Resource
    private ProductionPlanningDetailMapper productionPlanningDetailMapper;

    @Resource
    private ProductionLineMapper productionLineMapper;

    @Resource
    private ProductionLineRightService productionLineRightService;

    @Resource
    private ProductionLineService productionLineService;

    @Resource
    private ProductionPlanningDetailService productionPlanningDetailService;

    @Resource
    private ProductClassService productClassService;

    @Resource
    private ProductClassMapper productClassMapper;

    /**
     * 获取该用户所有权限生产线的所有进度条
     * @param user 用户信息
     * @return 所有权限生产线的所有进度条
     */
    public ServerResponse selectProductionPlanningByUser(User user) {
        // 获取用户权限的生产线 ID 列表
        List<Integer> productionLineIdList = productionLineRightService.selectProductionLineIdByUserId(user.getId()).getData();
        return selectProductionPlanningByProductionLineIdList(productionLineIdList);
    }

    /**
     * 根据 生产线ID 列表获取对应的排产信息
     * @param productionLineIdList 生产线 ID 列表
     * @return 对应的排产信息
     */
    private ServerResponse selectProductionPlanningByProductionLineIdList(List<Integer> productionLineIdList) {
        // 获取上一个月一日的 date 对象
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        Date startDate = calendar.getTime();

        // 获取用户权限的生产计划信息列表
        List<ProductionPlanning> productionPlanningList = productionPlanningMapper.selectAllByProductLineIdList(startDate, productionLineIdList);

        // 初始化响应结果
        Map<Integer, List<ProductionPlanning>> data = new HashMap<>();
        // 初始化
        for (Integer item : productionLineIdList) {
            data.put(item, new ArrayList<>());
        }
        for (ProductionPlanning item : productionPlanningList) {
            data.get(item.getProductionLineId()).add(item);
        }
        return ServerResponse.createdBySuccess(data);
    }

    /**
     * 更新排产计划，新增排产计划
     * @param user 操作用户信息
     * @param request 更新排产信息
     * @return 是否更新成功
     * @throws Exception 用于作为事务回溯
     */
    @Transactional(rollbackFor=Exception.class)
    public ServerResponse updateProductionPlanning(User user, Map<String, List<ProductionPlanningForUpdate>> request) throws Exception {
        Set<Map.Entry<String, List<ProductionPlanningForUpdate>>> productionPlanningSet = request.entrySet();
        for (Map.Entry<String, List<ProductionPlanningForUpdate>> productionPlanningList : productionPlanningSet) {
            for (ProductionPlanningForUpdate item : productionPlanningList.getValue()) {
                int productionLineCount = productionLineService.selectCountByPrimaryKey(item.getProductionLineId());
                if (productionLineCount == 0 || item.getStartTime() == null || item.getEndTime() == null || item.getProductionLineId() == null) {
                    throw new Exception("更新失败，参数错误");
                }
                ProductionPlanning productionPlanningFromDataBase = productionPlanningMapper.selectByPrimaryKey(item.getId());
                // 更新生产计划
                if (productionPlanningFromDataBase != null) {
                    productionPlanningFromDataBase.setStartTime(item.getStartTime());
                    productionPlanningFromDataBase.setEndTime(item.getEndTime());
                    productionPlanningFromDataBase.setProductionLineId(item.getProductionLineId());
                    productionPlanningFromDataBase.setUpdateUserId(user.getId());
                    int result = productionPlanningMapper.updateByPrimaryKeySelective(productionPlanningFromDataBase);
                    if (result == 0) {
                        throw new Exception("更新失败，更新排产计划发生错误");
                    }
                }
                // 新增生产计划
                else {
                    if (item.getProductionPlanningDetailId() == null) {
                        throw new Exception("更新失败，参数错误");
                    }
                    ProductionPlanningDetail productionPlanningDetail = productionPlanningDetailMapper.selectByPrimaryKey(item.getProductionPlanningDetailId());
                    if (productionPlanningDetail == null && productionPlanningDetail.getIsToDo()) {
                        throw new Exception("更新失败，参数错误");
                    }
                    // 新增排产计划
                    ProductionPlanning productionPlanningForAdd = new ProductionPlanning();
                    productionPlanningForAdd.setId(item.getProductionLineId());
                    productionPlanningForAdd.setProductionLineId(item.getProductionLineId());
                    productionPlanningForAdd.setStartTime(item.getStartTime());
                    productionPlanningForAdd.setEndTime(item.getEndTime());
                    productionPlanningForAdd.setQtyPlan(productionPlanningDetail.getQtyPlan());
                    productionPlanningForAdd.setQtyFinish(0);
                    productionPlanningForAdd.setCustnames(productionPlanningDetail.getCustname());
                    productionPlanningForAdd.setOrdernums(productionPlanningDetail.getOrdernum());
                    productionPlanningForAdd.setUpdateUserId(user.getId());
                    Integer productionPlanningId = productionPlanningMapper.insertSelective(productionPlanningForAdd);
                    if (productionPlanningId == null || productionPlanningId == 0) {
                        throw new Exception("更新失败，新增排产计划发生错误");
                    }
                    // 新增排产计划详情
                    productionPlanningDetail.setIsToDo(false);
                    productionPlanningDetail.setProductionPlanningId(productionPlanningId);
                    int result = productionPlanningDetailMapper.updateByPrimaryKeySelective(productionPlanningDetail);
                    if (result == 0) {
                        throw new Exception("更新失败，更新排产计划详情发生错误");
                    }
                }
            }
        }
        return ServerResponse.createdBySuccess();
    }

    /**
     * 减数功能
     * @param user 操作用户信息
     * @param productionPlanningDetailIdString 产品ID列表字符串
     * @param qtyFinishString 完成数列表字符串
     * @param endTime 结束时间
     * @return 是否成功
     * @throws Exception 用于作为事务回溯
     */
    @Transactional(rollbackFor=Exception.class)
    public ServerResponse updateQtyFinish(User user, String productionPlanningDetailIdString, String qtyFinishString, Date endTime) throws Exception {

        String[] IdStringList = productionPlanningDetailIdString.split("/");
        String[] qtyFinishStringList = qtyFinishString.split("/");

        if (IdStringList.length == qtyFinishStringList.length) {
            return ServerResponse.createdByError("参数错误");
        }
        for (int i=0; i<IdStringList.length; i++) {
            ProductionPlanningDetail productionPlanningDetailFromDataBase = productionPlanningDetailMapper.selectByPrimaryKey(Integer.parseInt(IdStringList[i]));
            if (productionPlanningDetailFromDataBase == null) {
                throw new Exception("更新失败，查无该生产计划详情信息");
            }
            int qtyFinish = Integer.parseInt(qtyFinishStringList[i]);
            if (qtyFinish < 0 || qtyFinish > productionPlanningDetailFromDataBase.getQtyPlan()) {
                throw new Exception("更新失败，完成数不得多于计划完成数或小于零");
            }
            productionPlanningDetailFromDataBase.setQtyFinish(qtyFinish);
            productionPlanningDetailFromDataBase.setUpdateUserId(user.getId());
            int result = productionPlanningDetailMapper.updateByPrimaryKeySelective(productionPlanningDetailFromDataBase);
            if (result == 0) {
                throw new Exception("更新失败，更新排产计划详情发生错误");
            }
            ProductionPlanning productionPlanning = productionPlanningMapper.selectByPrimaryKey(productionPlanningDetailFromDataBase.getProductionPlanningId());
            productionPlanning.setEndTime(endTime);
            productionPlanningMapper.updateByPrimaryKeySelective(productionPlanning);
        }
        return ServerResponse.createdBySuccess();
    }

    /**
     * 拆单
     * @param user 操作用户信息
     * @param productionPlanningDetailId 排产详情信息 ID
     * @param productionLineId 生产线 ID
     * @param qtyPlan 拆分的数量
     * @return 更新后排产信息
     * @throws Exception
     */
/*    @Transactional(rollbackFor = Exception.class)
    public ServerResponse takeApart(User user, Integer productionPlanningDetailId, Integer productionLineId, Integer qtyPlan) throws Exception {
        ProductionPlanningDetail updateProductionPlanningDetail = productionPlanningDetailMapper.selectByPrimaryKey(productionPlanningDetailId);
        ProductionLine productionLine = productionLineMapper.selectByPrimaryKey(productionLineId);
        if (productionLine == null || updateProductionPlanningDetail == null) {
            return ServerResponse.createdByError("更新失败，勾选的生产线不存在或该生产订单不存在");
        }
        Integer qtyDifference = updateProductionPlanningDetail.getQtyPlan() - updateProductionPlanningDetail.getQtyFinish();
        if (qtyDifference.compareTo(qtyPlan) < 0) {
            return ServerResponse.createdByError("分配数不得大于要拆分订单的计划数与完成数之差");
        }
        // 更新拆分的原生产计划详情
        updateProductionPlanningDetail.setUpdateUserId(user.getId());
        updateProductionPlanningDetail.setQtyPlan(updateProductionPlanningDetail.getQtyPlan() - qtyPlan);
        int result = productionPlanningDetailMapper.updateByPrimaryKeySelective(updateProductionPlanningDetail);
        if (result == 0) {
            throw new Exception("更新失败，更新已拆分的生产计划详情发生错误");
        }
        // 计算拆分的原生产计划的结束时间并更新
        ProductionPlanning updateProductionPlanning = productionPlanningMapper.selectByPrimaryKey(updateProductionPlanningDetail.getProductionPlanningId());
        List<ProductionPlanningDetailVO> productionPlanningDetailList = (List<ProductionPlanningDetailVO>) productionPlanningDetailService.selectByProductionPlanningId(updateProductionPlanningDetail.getProductionPlanningId()).getData();

        updateProductionPlanning.setUpdateUserId(user.getId());
        updateProductionPlanning.setQtyPlan(updateProductionPlanning.getQtyPlan() - qtyPlan);
        int needDayNum = 0;
        for (ProductionPlanningDetailVO item : productionPlanningDetailList) {
            // 获取拆单后的产品类对应的效率
            if (item.getId() == updateProductionPlanningDetail.getId()) {
                ProductClass updateProductClass = productClassMapper.selectByProductionClassNameAndQtyPlan(updateProductionPlanningDetail.getProductClassName(), (updateProductionPlanningDetail.getQtyPlan() - qtyPlan));
                BigDecimal efficicencyOfTempl = BigDecimalUtils.multiply(updateProductClass.getEfficiency().doubleValue(), item.getProductionLineEfficiency().doubleValue());
                ServerResponse<ProductionLineVO> serverResponseForProductionLine = productionLineService.selectByLineId(updateProductionPlanning.getProductionLineId());
                if (!serverResponseForProductionLine.isSuccess()) {
                    throw new Exception("更新失败，获取效率时，没有找到对应的生产线");
                }
                ProductionLineVO productionLineVO = serverResponseForProductionLine.getData();
                Double templ = new BigDecimal(productionLineVO.getPeopleNum() * productionLineVO.getWorkhours()).doubleValue();
                int richan = BigDecimalUtils.multiply((efficicencyOfTempl).doubleValue(), templ).intValue();
                needDayNum += Math.ceil((item.getQtyPlan() - item.getQtyFinish())/richan);
            } else {
                needDayNum += Math.ceil((item.getQtyPlan() - item.getQtyFinish())/item.getRichan());
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date sDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, needDayNum);
        Date eDate = calendar.getTime();
        updateProductionPlanning.setEndTime(eDate);
        result = productionPlanningMapper.updateByPrimaryKeySelective(updateProductionPlanning);
        if (result == 0) {
            throw new Exception("更新失败，更新已拆分的生产计划结束时间发生错误");
        }
        // 添加因拆分而新增的生产计划
        ProductionPlanning insertProductionPlanning = new ProductionPlanning();
        insertProductionPlanning.setProductionLineId(productionLineId);
        insertProductionPlanning.setStartTime(sDate);
        // 计算因拆分而新增的生产计划的结束时间
        ServerResponse<ProductClass> serverResponseForProductionClassEfficiency = productClassService.getProductClassByProductionClassNameAndQtyPlan(updateProductionPlanningDetail.getProductClassName(), qtyPlan);
        if (!serverResponseForProductionClassEfficiency.isSuccess()) {
            throw new Exception("更新失败，更新");
        }
        ProductClass insertProductClass = serverResponseForProductionClassEfficiency.getData();
        ServerResponse<ProductionLineVO> serverResponseForProductionLine = productionLineService.selectByLineId(productionLineId);
        if (!serverResponseForProductionLine.isSuccess()) {
            throw new Exception("更新失败，获取效率时，没有找到对应的生产线");
        }
        ProductionLineVO insertProductionLineVO = serverResponseForProductionLine.getData();
        List<EfficiencyOfLine> efficiencyOfLineList = insertProductionLineVO.getEfficiencyOfLineList();
        BigDecimal productionLineEfficiency = new BigDecimal("1");
        for (EfficiencyOfLine item : efficiencyOfLineList) {
            if (StringUtils.equals(item.getStyleName(), insertProductClass.getStyleName())) {
                productionLineEfficiency = new BigDecimal(Double.toString(item.getEfficiency()));
            }
        }
        BigDecimal templ = BigDecimalUtils.multiply(insertProductClass.getEfficiency().doubleValue(), productionLineEfficiency.doubleValue());
        Double templ2 = new BigDecimal(insertProductionLineVO.getPeopleNum() * insertProductionLineVO.getWorkhours()).doubleValue();
        int richan = BigDecimalUtils.multiply(templ.doubleValue(), templ2).intValue();
        needDayNum = new Double(Math.ceil(qtyPlan/richan)).intValue();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, needDayNum);
        Date eDate1 = calendar.getTime();
        insertProductionPlanning.setEndTime(eDate1);
        insertProductionPlanning.setQtyPlan(qtyPlan);
        insertProductionPlanning.setQtyFinish(0);
        insertProductionPlanning.setCustnames(updateProductionPlanningDetail.getCustname());
        insertProductionPlanning.setOrdernums(updateProductionPlanningDetail.getOrdernum());
        insertProductionPlanning.setUpdateUserId(user.getId());
        result = productionPlanningMapper.insertSelective(insertProductionPlanning);
        if (result == 0) {
            throw new Exception("更新失败，更新因拆分而新增的生产计划发生错误");
        }
        ProductionPlanningDetail insertProductionPlanningDetail = new ProductionPlanningDetail();
        insertProductionPlanningDetail.setIsToDo(true);
        insertProductionPlanningDetail.setProductionPlanningId(productionLineId);
        insertProductionPlanningDetail.setProductionLineId(productionLineId);
        insertProductionPlanningDetail.setCustname(updateProductionPlanning.getCustnames());
        insertProductionPlanningDetail.setGoodName(updateProductionPlanningDetail.getGoodName());
        insertProductionPlanningDetail.setOrdernum(updateProductionPlanningDetail.getOrdernum());
        insertProductionPlanningDetail.setSeason(updateProductionPlanningDetail.getSeason());
        insertProductionPlanningDetail.setColor(updateProductionPlanningDetail.getColor());
        insertProductionPlanningDetail.setQtyPlan(qtyPlan);
        insertProductionPlanningDetail.setQtyFinish(0);
        insertProductionPlanningDetail.setProductClassName(updateProductionPlanningDetail.getProductClassName());
        insertProductionPlanningDetail.setLeavingTime(updateProductionPlanningDetail.getLeavingTime());
        insertProductionPlanningDetail.setUpdateUserId(user.getId());
        result = productionPlanningDetailMapper.insertSelective(insertProductionPlanningDetail);
        if (result == 0) {
            throw new Exception("更新失败，更新因拆分而新增的生产计划详情发生错误");
        }
        return ServerResponse.createdBySuccess();
    }*/
}
