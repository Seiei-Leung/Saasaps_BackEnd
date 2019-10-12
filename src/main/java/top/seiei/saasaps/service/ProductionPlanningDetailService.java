package top.seiei.saasaps.service;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.seiei.saasaps.bean.EfficiencyOfLine;
import top.seiei.saasaps.bean.ProductClass;
import top.seiei.saasaps.bean.ProductionPlanningDetail;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ProductionPlanningDetailMapper;
import top.seiei.saasaps.util.BigDecimalUtils;
import top.seiei.saasaps.vo.ProductionLineVO;
import top.seiei.saasaps.vo.ProductionPlanningDetailVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("productionPlanningDetailService")
public class ProductionPlanningDetailService {

    @Resource
    private ProductionPlanningDetailMapper productionPlanningDetailMapper;

    @Resource
    private ProductClassService productClassService;

    @Resource
    private ProductionLineService productionLineService;

    /**
     * 获取所有没有添加到进度的生产计划详情
     * @return 生产计划详情列表
     */
    public ServerResponse selectAllToDo() {
        List<ProductionPlanningDetail> productionPlanningDetail = productionPlanningDetailMapper.selectAllToDo();
        return ServerResponse.createdBySuccess(productionPlanningDetail);
    }

    /**
     * 根据生产计划主要信息的 ID 获取生产计划详情
     * @param id 生产计划的 ID
     * @return 生产计划详情
     */
/*    public ServerResponse selectByProductionPlanningId(Integer id) {
        List<ProductionPlanningDetail> productionPlanningDetailList = productionPlanningDetailMapper.selectByProductionPlanningId(id);
        List<ProductionPlanningDetailVO> productionPlanningDetailVOList = new ArrayList<>();
        for (ProductionPlanningDetail item : productionPlanningDetailList) {
            ServerResponse<ProductionPlanningDetailVO> serverResponse = assembleProductionPlanningDetailVO(item);
            if (!serverResponse.isSuccess()) {
                return serverResponse;
            }
            productionPlanningDetailVOList.add(serverResponse.getData());
        }
        return ServerResponse.createdBySuccess(productionPlanningDetailVOList);
    }*/

    /**
     * 根据排产详情信息主键 ID 获取排产详情信息
     * @param id 排产详情主键 ID
     * @return 排产详情信息
     */
/*    public ServerResponse<ProductionPlanningDetailVO> selectByPrimaryKey(Integer id) {
        ProductionPlanningDetail productionPlanningDetail = productionPlanningDetailMapper.selectByPrimaryKey(id);
        return assembleProductionPlanningDetailVO(productionPlanningDetail);
    }*/

    /**
     * ProductionPlanningDetail 转化为 ProductionPlanningVO 对象
     * @param productionPlanningDetail ProductionPlanningDetail 对象
     * @return ServerResponse<ProductionPlanningVO>
     */
/*    public ServerResponse<ProductionPlanningDetailVO> assembleProductionPlanningDetailVO(ProductionPlanningDetail productionPlanningDetail) {
        ProductionPlanningDetailVO productionPlanningDetailVO = new ProductionPlanningDetailVO();
        productionPlanningDetailVO.setId(productionPlanningDetail.getId());
        productionPlanningDetailVO.setProductionPlanningId(productionPlanningDetail.getProductionPlanningId());
        productionPlanningDetailVO.setCustname(productionPlanningDetail.getCustname());
        productionPlanningDetailVO.setGoodName(productionPlanningDetail.getGoodName());
        productionPlanningDetailVO.setOrdernum(productionPlanningDetail.getOrdernum());
        productionPlanningDetailVO.setSeason(productionPlanningDetail.getSeason());
        productionPlanningDetailVO.setColor(productionPlanningDetail.getColor());
        productionPlanningDetailVO.setQtyPlan(productionPlanningDetail.getQtyPlan());
        productionPlanningDetailVO.setQtyFinish(productionPlanningDetail.getQtyFinish());
        productionPlanningDetailVO.setProductClassName(productionPlanningDetail.getProductClassName());
        productionPlanningDetailVO.setLeavingTime(productionPlanningDetail.getLeavingTime());
        // 计算效率
        ServerResponse<ProductClass> serverResponseForProductionClassEfficiency = productClassService.getProductClassByProductionClassNameAndQtyPlan(productionPlanningDetail.getProductClassName(), productionPlanningDetail.getQtyPlan());
        ProductClass productClass;
        if (!serverResponseForProductionClassEfficiency.isSuccess()) {
            return ServerResponse.createdByError("没有找到对应的产品类");
        }
        productClass = serverResponseForProductionClassEfficiency.getData();
        productionPlanningDetailVO.setStyleName(productClass.getStyleName());
        productionPlanningDetailVO.setProductionClassEfficiency(productClass.getEfficiency());
        // 默认效率为 1
        BigDecimal productionLineEfficiency = new BigDecimal("1");
        ServerResponse<ProductionLineVO> serverResponseForProductionLine = productionLineService.selectByLineId(productionPlanningDetail.getProductionLineId());
        if (!serverResponseForProductionLine.isSuccess()) {
            return ServerResponse.createdByError("没有找到对应的生产线");
        }
        List<EfficiencyOfLine> efficiencyOfLineList = serverResponseForProductionLine.getData().getEfficiencyOfLineList();
        for (EfficiencyOfLine item : efficiencyOfLineList) {
            if (StringUtils.equals(item.getStyleName(), productClass.getStyleName())) {
                productionLineEfficiency = new BigDecimal(Double.toString(item.getEfficiency()));
            }
        }
        productionPlanningDetailVO.setProductionLineEfficiency(productionLineEfficiency);
        productionPlanningDetailVO.setEfficiency(BigDecimalUtils.multiply(productionLineEfficiency.doubleValue(), productionPlanningDetailVO.getProductionClassEfficiency().doubleValue()));
        Double templ = new BigDecimal(serverResponseForProductionLine.getData().getPeopleNum() * serverResponseForProductionLine.getData().getWorkhours()).doubleValue();
        Integer richan = BigDecimalUtils.multiply(productionPlanningDetailVO.getEfficiency().doubleValue(), templ).intValue();
        productionPlanningDetailVO.setRichan(richan);
        return ServerResponse.createdBySuccess(productionPlanningDetailVO);
    }*/
}
