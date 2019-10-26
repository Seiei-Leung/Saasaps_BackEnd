package top.seiei.saasaps.service;

import top.seiei.saasaps.bean.EfficiencyOfLine;
import top.seiei.saasaps.bean.ProductionPlanningDetail;
import top.seiei.saasaps.bean.WorkhoursOfLine;
import top.seiei.saasaps.bean.PeopleNumOfLine;

import org.springframework.stereotype.Service;
import top.seiei.saasaps.bean.*;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.*;
import top.seiei.saasaps.util.DateUtil;
import top.seiei.saasaps.vo.ProductionLineIncludePPD;
import top.seiei.saasaps.vo.ProductionLineVO;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("productionLineService")
public class ProductionLineService {

    @Resource
    private ProductionLineMapper productionLineMapper;

    @Resource
    private ProductionLineRightMapper productionLineRightMapper;

    @Resource
    private EfficiencyOfLineMapper efficiencyOfLineMapper;

    @Resource
    private PeopleNumOfLineMapper peopleNumOfLineMapper;

    @Resource
    private WorkhoursOfLineMapper workhoursOfLineMapper;

    @Resource
    private ProductStyleMapper productStyleMapper;

    @Resource
    private ProductionPlanningDetailMapper productionPlanningDetailMapper;

    @Resource
    private ProductionPlanningDetailService productionPlanningDetailService;

    /**
     * 	获取所有生产线列表
     * @return 所有生产线列表
     */
    public ServerResponse getall() {
        List<ProductionLine> productionLineList = productionLineMapper.getAll();
        List<ProductionLineVO> productionLineVOList = new ArrayList<>();
        for (ProductionLine item : productionLineList) {
            productionLineVOList.add(assembleProductionLineVO(item, null));
        }
        return ServerResponse.createdBySuccess(productionLineVOList);
    }

    /**
     * 根据用户 ID 获取获取权限生产线
     * @param userId 用户 ID
     * @return 权限生产线列表
     */
    public ServerResponse selectAllByUserId(Integer userId, Integer year) {
        List<ProductionLineRight> productionLineRightList = productionLineRightMapper.selectByUserId(userId);
        List<Integer> idList = new ArrayList<>();
        productionLineRightList.forEach((productionLineRight -> {
            idList.add(productionLineRight.getProductLineId());
        }));
        List<ProductionLine> productionLineList = productionLineMapper.selectByPrimaryKeyList(idList);
        if (productionLineList == null) {
            return ServerResponse.createdByError("该用户没有权限生产线");
        }
        List<ProductionLineVO> productionLineVOList = new ArrayList<>();
        for (ProductionLine item : productionLineList) {
            ProductionLineVO productionLineVO = assembleProductionLineVO(item, year);
            productionLineVOList.add(productionLineVO);
        }
        return ServerResponse.createdBySuccess(productionLineVOList);
    }

    /**
     * 根据生产线 ID 获取生产线详情
     * @param lineId 生产线 ID
     * @return 生产线详情
     */
    public ServerResponse selectDetailByLineId(Integer lineId) {
        ProductionLine productionLine = productionLineMapper.selectByPrimaryKey(lineId);
        if (productionLine == null) {
            return ServerResponse.createdByError("该生产线不存在");
        }
        ProductionLineVO productionLineVO = assembleProductionLineVO(productionLine, null);
        return ServerResponse.createdBySuccess(productionLineVO);
    }

    /**
     * 更新生产线主体信息
     * @param user 操作用户
     * @param updateProductionLine 更新生产线信息
     * @return 是否更新成功
     */
    public ServerResponse update(User user, ProductionLine updateProductionLine) {
        if (
                updateProductionLine.getWorkgroup() == null ||
                updateProductionLine.getWorkshop() == null ||
                updateProductionLine.getLineCode() == null ||
                updateProductionLine.getPeopleNum() == null ||
                updateProductionLine.getWorkhours() == null ||
                updateProductionLine.getPeopleNum() == 0 ||
                updateProductionLine.getWorkhours().compareTo(new BigDecimal("0")) == -1
        ) {
            return ServerResponse.createdByError("更新失败，参数不能为空且默认人数及效率不能为零");
        }
        ProductionLine productionLine = productionLineMapper.selectByPrimaryKey(updateProductionLine.getId());
        ProductionLine productionLineTemp = productionLineMapper.selectByName(updateProductionLine.getWorkgroup(), updateProductionLine.getWorkshop(), updateProductionLine.getLineCode());
        if (productionLineTemp != null && productionLineTemp.getId() != productionLine.getId()) {
            return ServerResponse.createdByError("更新失败，该组别、车间及生产线的名称与现有的生产线发生冲突");
        }
        if (productionLine == null) {
            return ServerResponse.createdByError("没有该生产线");
        }
        updateProductionLine.setUpdateUserId(user.getId());
        updateProductionLine.setUpdateTime(new Date());
        int result = productionLineMapper.updateByPrimaryKeySelective(updateProductionLine);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 新增生产线
     * @param user 操作用户信息
     * @param productionLine 新增信息
     * @return 新增是否成功
     */
    public ServerResponse add(User user, ProductionLine productionLine) {
        if (
                productionLine.getWorkgroup() == null ||
                productionLine.getWorkshop() == null ||
                productionLine.getLineCode() == null ||
                productionLine.getPeopleNum() == null ||
                productionLine.getWorkhours() == null ||
                productionLine.getPeopleNum() == 0 ||
                productionLine.getWorkhours().compareTo(new BigDecimal("0")) == -1
        ) {
            return ServerResponse.createdByError("更新失败，参数不能为空且默认人数及效率不能为零");
        }
        ProductionLine productionLineTemp = productionLineMapper.selectByName(productionLine.getWorkgroup(), productionLine.getWorkshop(), productionLine.getLineCode());
        if (productionLineTemp != null && productionLineTemp.getId() != productionLine.getId()) {
            return ServerResponse.createdByError("更新失败，该组别、车间及生产线的名称与现有的生产线发生冲突");
        }
        productionLine.setUpdateUserId(user.getId());
        productionLine.setInvalid(Const.ProductionLineStatus.WORKING);
        productionLine.setCreateTime(new Date());
        productionLine.setUpdateTime(new Date());
        int result = productionLineMapper.insertSelective(productionLine);
        if (result == 0) {
            return ServerResponse.createdByError("新增失败");
        }
        return ServerResponse.createdBySuccess("新增成功", productionLine.getId());
    }

    /**
     * 删除生产线
     * @param id 生产线 ID
     * @return 是否删除成功
     */
    public ServerResponse delete(Integer id) {
        ProductionLine productionLine = productionLineMapper.selectByPrimaryKey(id);
        if (productionLine == null) {
            return ServerResponse.createdByError("该生产线不存在");
        }
        // 删除生产线
        productionLine.setInvalid(Const.ProductionLineStatus.NOT_WORKING);
        int result = productionLineMapper.updateByPrimaryKeySelective(productionLine);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");
    }

    /**
     * 更新生产线效率信息
     * @param user 操作用户
     * @param updateEfficiencyOfLine 更新生产线效率信息
     * @return 是否更新成功
     */
    public ServerResponse updateEfficiency(User user, EfficiencyOfLine updateEfficiencyOfLine) {
        if (updateEfficiencyOfLine == null || updateEfficiencyOfLine.getId() == null) {
            return ServerResponse.createdByError("参数错误");
        }
        EfficiencyOfLine efficiencyOfLine = efficiencyOfLineMapper.selectByPrimaryKey(updateEfficiencyOfLine.getId());
        if (efficiencyOfLine == null) {
            return ServerResponse.createdByError("生产线没有该属性效率");
        }
        updateEfficiencyOfLine.setUpdateUserId(user.getId());
        updateEfficiencyOfLine.setUpdateTime(new Date());
        int result = efficiencyOfLineMapper.updateByPrimaryKeySelective(updateEfficiencyOfLine);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 删除生产线属性效率
     * @param id 该生产线属性效率的 ID
     * @return 是否删除成功
     */
    public ServerResponse deleteEfficiency(Integer id) {
        EfficiencyOfLine efficiencyOfLine = efficiencyOfLineMapper.selectByPrimaryKey(id);
        if (efficiencyOfLine == null) {
            return ServerResponse.createdByError("生产线没有该属性效率");
        }
        int result = efficiencyOfLineMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");
    }

    /**
     * 新增生产线属性效率
     * @param user 操作用户
     * @param efficiencyOfLine 更新生产线效率信息
     * @return 是否成功
     */
    public ServerResponse addEfficiency(User user, EfficiencyOfLine efficiencyOfLine) {
        if (efficiencyOfLine == null) {
            return ServerResponse.createdByError("参数错误");
        }
        ProductStyle productStyle = productStyleMapper.selectByStyleName(efficiencyOfLine.getStyleName());
        ProductionLine productionLine = productionLineMapper.selectByPrimaryKey(efficiencyOfLine.getProductionLineId());
        if (productStyle == null || efficiencyOfLine.getEfficiency() == null || efficiencyOfLine.getProductionLineId() == null || productionLine == null) {
            return ServerResponse.createdByError("参数错误");
        }
        EfficiencyOfLine updateEfficiencyOfLine = efficiencyOfLineMapper.selectByLineIdAndStyleName(efficiencyOfLine.getProductionLineId(), efficiencyOfLine.getStyleName());
        if (updateEfficiencyOfLine != null) {
            return ServerResponse.createdByError("该属性效率已经定义");
        }
        efficiencyOfLine.setUpdateUserId(user.getId());
        efficiencyOfLine.setUpdateTime(new Date());
        efficiencyOfLine.setCreateTime(new Date());
        int result = efficiencyOfLineMapper.insertSelective(efficiencyOfLine);
        if (result == 0) {
            return ServerResponse.createdByError("新增失败");
        }
        return ServerResponse.createdBySuccess("新增成功", efficiencyOfLine.getId());
    }

    /**
     * 更新工作时间
     * @param user 操作用户
     * @param id 属性 ID
     * @param startTime 属性有效开始时间
     * @param endTime 属性有效结束时间
     * @param workhours 工时
     * @return 是否更新成功
     */
    public ServerResponse updateWorkhours(User user, Integer id, Long startTime, Long endTime, Double workhours) {
        if (id == null || startTime == null || endTime == null || workhours == null) {
            return  ServerResponse.createdByError("参数不能为空");
        }
        if (startTime > endTime) {
            return ServerResponse.createdByError("起始时间不能比结束时间后");
        }
        WorkhoursOfLine workhoursOfLine = workhoursOfLineMapper.selectByPrimaryKey(id);
        if (workhoursOfLine == null) {
            return ServerResponse.createdByError("生产线没有该工作时间属性");
        }
        Date stime = DateUtil.zeroSetting(new Date(startTime));
        Date etime = DateUtil.zeroSetting(new Date(endTime));
        WorkhoursOfLine workhoursOfLineTemp = workhoursOfLineMapper.selectWorkHoursByTime(workhoursOfLine.getProductionLineId(), stime);
        if (workhoursOfLineTemp != null && workhoursOfLineTemp.getId() != id) {
            return ServerResponse.createdByError("该生产线在当前时间设置发生重叠冲突");
        }
        workhoursOfLineTemp = workhoursOfLineMapper.selectWorkHoursByTime(workhoursOfLine.getProductionLineId(), etime);
        if (workhoursOfLineTemp != null && workhoursOfLineTemp.getId() != id){
            return ServerResponse.createdByError("该生产线在当前时间设置发生重叠冲突");
        }
        workhoursOfLine.setUpdateUserId(user.getId());
        workhoursOfLine.setStartTime(stime);
        workhoursOfLine.setEndTime(etime);
        workhoursOfLine.setWorkhours(new BigDecimal(Double.toString(workhours)));
        workhoursOfLine.setUpdateTime(new Date());
        int result = workhoursOfLineMapper.updateByPrimaryKeySelective(workhoursOfLine);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 删除工作时间
     * @param id 该生产线工作时间属性的 ID
     * @return 是否删除成功
     */
    public ServerResponse deleteWorkhours(Integer id) {
        WorkhoursOfLine workhoursOfLine = workhoursOfLineMapper.selectByPrimaryKey(id);
        if (workhoursOfLine == null) {
            return ServerResponse.createdByError("生产线没有该属性效率");
        }
        int result = workhoursOfLineMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");
    }

    /**
     * 新增生产线工作时间属性
     * @param user 操作用户
     * @param productionLineId 生产线 Id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param workhours 工作时间
     * @return 是否成功
     */
    public ServerResponse addWorkhours(User user, Integer productionLineId, Long startTime, Long endTime, Double workhours) {
        if (productionLineId == null || productionLineMapper.selectByPrimaryKey(productionLineId) == null || workhours == null || startTime == null || endTime == null) {
            return ServerResponse.createdByError("参数错误");
        }
        if (startTime > endTime) {
            return ServerResponse.createdByError("起始时间不能比结束时间后");
        }
        Date stime = DateUtil.zeroSetting(new Date(startTime));
        Date etime = DateUtil.zeroSetting(new Date(endTime));
        WorkhoursOfLine workhoursOfLineTemp = workhoursOfLineMapper.selectWorkHoursByTime(productionLineId, stime);
        if (workhoursOfLineTemp != null) {
            return ServerResponse.createdByError("该生产线在当前时间设置发生重叠冲突");
        }
        workhoursOfLineTemp = workhoursOfLineMapper.selectWorkHoursByTime(productionLineId, etime);
        if (workhoursOfLineTemp != null){
            return ServerResponse.createdByError("该生产线在当前时间设置发生重叠冲突");
        }
        WorkhoursOfLine workhoursOfLine = new WorkhoursOfLine();
        workhoursOfLine.setWorkhours(new BigDecimal(Double.toString(workhours)));
        workhoursOfLine.setStartTime(stime);
        workhoursOfLine.setEndTime(etime);
        workhoursOfLine.setProductionLineId(productionLineId);
        workhoursOfLine.setUpdateUserId(user.getId());
        workhoursOfLine.setCreateTime(new Date());
        workhoursOfLine.setUpdateTime(new Date());
        int result = workhoursOfLineMapper.insertSelective(workhoursOfLine);
        if (result == 0) {
            return ServerResponse.createdByError("新增失败");
        }
        return ServerResponse.createdBySuccess("新增成功", workhoursOfLine.getId());
    }

    /**
     * 更新生产线工作人数属性
     * @param user 操作用户
     * @param id 更新工作人数属性 Id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param peopleNum 人数
     * @return 是否更新成功
     */
    public ServerResponse updatePeopleNum(User user, Integer id, Long startTime, Long endTime, Integer peopleNum) {
        if (id == null || startTime == null || endTime == null || peopleNum == null) {
            return  ServerResponse.createdByError("参数不能为空");
        }
        if (startTime > endTime) {
            return ServerResponse.createdByError("起始时间不能比结束时间后");
        }
        Date stime = new Date(startTime);
        Date etime = new Date(endTime);
        PeopleNumOfLine peopleNumOfLine = peopleNumOfLineMapper.selectByPrimaryKey(id);
        if (peopleNumOfLine == null) {
            return ServerResponse.createdByError("生产线没有该工作人数属性");
        }
        PeopleNumOfLine peopleNumOfLineTemp = peopleNumOfLineMapper.selectPeopleNumByTime(peopleNumOfLine.getProductionLineId(), stime);
        if (peopleNumOfLineTemp != null && peopleNumOfLineTemp.getId() != id) {
            return ServerResponse.createdByError("该生产线在当前时间设置发生重叠冲突");
        }
        peopleNumOfLineTemp = peopleNumOfLineMapper.selectPeopleNumByTime(peopleNumOfLine.getProductionLineId(), etime);
        if (peopleNumOfLineTemp != null && peopleNumOfLineTemp.getId() != id) {
            return ServerResponse.createdByError("该生产线在当前时间设置发生重叠冲突");
        }
        peopleNumOfLine.setStartTime(stime);
        peopleNumOfLine.setEndTime(etime);
        peopleNumOfLine.setPeopleNum(peopleNum);
        peopleNumOfLine.setUpdateUserId(user.getId());
        peopleNumOfLine.setUpdateTime(new Date());
        int result = peopleNumOfLineMapper.updateByPrimaryKeySelective(peopleNumOfLine);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 新增生产线工作人数属性
     * @param user 操作用户 ID
     * @param productionLineId 生产线 ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param peopleNum 人数
     * @return 是否更新成功
     */
    public ServerResponse addPeopleNum(User user, Integer productionLineId, Long startTime, Long endTime, Integer peopleNum) {
        if (productionLineId == null || productionLineMapper.selectByPrimaryKey(productionLineId) == null || peopleNum == null || startTime == null || endTime == null) {
            return ServerResponse.createdByError("参数错误");
        }
        if (startTime > endTime) {
            return ServerResponse.createdByError("起始时间不能比结束时间后");
        }
        Date stime = new Date(startTime);
        Date etime = new Date(endTime);
        PeopleNumOfLine peopleNumOfLineTemp = peopleNumOfLineMapper.selectPeopleNumByTime(productionLineId, stime);
        if (peopleNumOfLineTemp != null) {
            return ServerResponse.createdByError("该生产线在当前时间设置发生重叠冲突");
        }
        peopleNumOfLineTemp = peopleNumOfLineMapper.selectPeopleNumByTime(productionLineId, etime);
        if (peopleNumOfLineTemp != null){
            return ServerResponse.createdByError("该生产线在当前时间设置发生重叠冲突");
        }
        PeopleNumOfLine peopleNumOfLine = new PeopleNumOfLine();
        peopleNumOfLine.setPeopleNum(peopleNum);
        peopleNumOfLine.setStartTime(stime);
        peopleNumOfLine.setEndTime(etime);
        peopleNumOfLine.setProductionLineId(productionLineId);
        peopleNumOfLine.setUpdateUserId(user.getId());
        peopleNumOfLine.setCreateTime(new Date());
        peopleNumOfLine.setUpdateTime(new Date());
        int result = peopleNumOfLineMapper.insertSelective(peopleNumOfLine);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccess("更新成功", peopleNumOfLine.getId());
    }

    /**
     * 删除生产线工作人数属性
     * @param id 该生产线工作时间属性的 ID
     * @return 是否删除成功
     */
    public ServerResponse deletePeopleNum(Integer id) {
        PeopleNumOfLine peopleNumOfLine = peopleNumOfLineMapper.selectByPrimaryKey(id);
        if (peopleNumOfLine == null) {
            return ServerResponse.createdByError("生产线没有该属性效率");
        }
        int result = peopleNumOfLineMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdBySuccessMessage("删除成功");
    }

    /**
     * 排产器获取生产线信息，里头包装了排产详情
     * @param userId 用户Id
     * @param year 最早年份
     * @return
     */
    public ServerResponse getResourceDataByUserId(Integer userId, Integer year) {
        ServerResponse serverResponse = selectAllByUserId(userId, year);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        List<ProductionLineVO> productionLineVOList = (List<ProductionLineVO>) serverResponse.getData();
        List<ProductionLineIncludePPD> productionLineIncludePPDList = new ArrayList<>();
        for (ProductionLineVO item : productionLineVOList) {
            ProductionLineIncludePPD productionLineIncludePPD = assembleProductionLineIncludePPD(item, year);
            productionLineIncludePPDList.add(productionLineIncludePPD);
        }
        return ServerResponse.createdBySuccess(productionLineIncludePPDList);
    }

    /**
     * productionLine 转换为 ProductionLineVO
     * @param productionLine productionLine 对象
     * @return ProductionLineVO 对象
     */
    private ProductionLineVO assembleProductionLineVO(ProductionLine productionLine, Integer year) {
        ProductionLineVO productionLineVO = new ProductionLineVO();
        productionLineVO.setId(productionLine.getId());
        productionLineVO.setWorkgroup(productionLine.getWorkgroup());
        productionLineVO.setWorkshop(productionLine.getWorkshop());
        productionLineVO.setLineCode(productionLine.getLineCode());
        productionLineVO.setInvalid(productionLine.getInvalid());
        productionLineVO.setWorkhours(productionLine.getWorkhours());
        productionLineVO.setPeopleNum(productionLine.getPeopleNum());
        if (year == null) {
            productionLineVO.setPeopleNumOfLineList(peopleNumOfLineMapper.selectByLineId(productionLine.getId()));
            productionLineVO.setWorkhoursOfLineList(workhoursOfLineMapper.selectByLineId(productionLine.getId()));
            productionLineVO.setEfficiencyOfLineList(efficiencyOfLineMapper.selectByLineId(productionLine.getId()));
        } else {
            Date time = null;
            try {
                time = DateUtil.getFirstDayOfYear(year);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            productionLineVO.setPeopleNumOfLineList(peopleNumOfLineMapper.selectByLineIdAndTime(productionLine.getId(), time));
            productionLineVO.setWorkhoursOfLineList(workhoursOfLineMapper.selectByLineIdAndTime(productionLine.getId(), time));
            productionLineVO.setEfficiencyOfLineList(efficiencyOfLineMapper.selectByLineId(productionLine.getId()));
        }
        return productionLineVO;
    }

    /**
     * 转化为 ProductionLineIncludePPD 对象
     * @param productionLineVO productionLineVO 对象
     * @param year 最早年份
     * @return
     */
    private ProductionLineIncludePPD assembleProductionLineIncludePPD(ProductionLineVO productionLineVO, Integer year) {
        ProductionLineIncludePPD productionLineIncludePPD = new ProductionLineIncludePPD();
        productionLineIncludePPD.setId(productionLineVO.getId());
        productionLineIncludePPD.setWorkgroup(productionLineVO.getWorkgroup());
        productionLineIncludePPD.setWorkshop(productionLineVO.getWorkshop());
        productionLineIncludePPD.setLineCode(productionLineVO.getLineCode());
        productionLineIncludePPD.setWorkhours(productionLineVO.getWorkhours());
        productionLineIncludePPD.setPeopleNum(productionLineVO.getPeopleNum());
        productionLineIncludePPD.setInvalid(productionLineVO.getInvalid());
        productionLineIncludePPD.setEfficiencyOfLineList(productionLineVO.getEfficiencyOfLineList());
        productionLineIncludePPD.setPeopleNumOfLineList(productionLineVO.getPeopleNumOfLineList());
        productionLineIncludePPD.setWorkhoursOfLineList(productionLineVO.getWorkhoursOfLineList());
        Date time = null;
        try {
            time = DateUtil.getFirstDayOfYear(year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 赋值品类效率，品类
        List<ProductionPlanningDetail> productionPlanningDetailList = productionPlanningDetailMapper.selectByLineIdAndTime(productionLineVO.getId(), time);
        List<ProductionPlanningDetail> resultProductionPlanningDetailList = productionPlanningDetailService.setEfficiencyOfClassAndProductStyleName(productionPlanningDetailList);
        productionLineIncludePPD.setProductionPlanningDetailList(resultProductionPlanningDetailList);
        return productionLineIncludePPD;
    }


}
