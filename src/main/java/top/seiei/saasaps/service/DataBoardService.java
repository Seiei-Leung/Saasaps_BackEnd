package top.seiei.saasaps.service;

import org.springframework.stereotype.Service;
import top.seiei.saasaps.bean.ProductionLine;
import top.seiei.saasaps.bean.ProductionPlanningDetail;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ProductionLineMapper;
import top.seiei.saasaps.dao.ProductionPlanningDetailMapper;
import top.seiei.saasaps.pv.AppSessionContext;
import top.seiei.saasaps.util.DateUtil;
import top.seiei.saasaps.util.ProductionPlanningDetailUtils;
import top.seiei.saasaps.vo.DataBoardVO;
import top.seiei.saasaps.vo.ProductionLineVO;
import top.seiei.saasaps.vo.SpareCapacityOfProductLineVO;
import top.seiei.saasaps.vo.WorkingDateSettingVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Service("dataBoardService")
public class DataBoardService {

    @Resource
    private ProductionPlanningDetailMapper productionPlanningDetailMapper;

    @Resource
    private ProductionLineMapper productionLineMapper;

    @Resource
    private ProductionLineService productionLineService;

    @Resource
    private FactoryCalendarService factoryCalendarService;

    /**
     * 获取数据看板数据
     * @param searchtime 查询日期（逻辑上是今天的时间戳）
     * @return
     */
    public ServerResponse getAll(long searchtime) {
        Date searchDate = new Date(searchtime);
        DataBoardVO dataBoardVO = new DataBoardVO();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(searchDate);
        Integer searchYear = calendar.get(Calendar.YEAR);

        // 获取在线人数
        HashMap<String, HttpSession> mapOfSession = AppSessionContext.mapOfSession;
        dataBoardVO.setOnLineNumber(mapOfSession.keySet().size());

        // 今日排单数量
        Integer schedulingNumberOfToday = productionPlanningDetailMapper.getSchedulingNumberOfToday(DateUtil.getTimeStampByAddDayNumBaseOnToday(-1), DateUtil.getTimeStampByAddDayNumBaseOnToday(1));
        dataBoardVO.setSchedulingNumberOfToday(schedulingNumberOfToday);

        // 未来一周的交期订单数
        Integer deliveryOrderNumberOfWeek = productionPlanningDetailMapper.getDeliveryOrderNumberOfWeek(DateUtil.zeroSetting(new Date()), DateUtil.getTimeStampByAddDayNumBaseOnToday(7));
        dataBoardVO.setDeliveryOrderNumberOfWeek(deliveryOrderNumberOfWeek);

        // 交期异常订单数
        List<ProductionLine> productionLineList = productionLineMapper.getAll();
        Integer abnormalOrderNumber = 0; // 交期异常订单数
        BigDecimal capacityOfNextMonth = new BigDecimal(0); // 未来一个月的空闲产能
        Date dateOfNextMonth = DateUtil.getTimeStampByAddMonths(searchDate, 1); // 未来一个月的结束时间
        BigDecimal capacityOfNextThreeMonth = new BigDecimal(0); // 未来一个月的空闲产能
        Date dateOfNextThreeMonth = DateUtil.getTimeStampByAddMonths(searchDate, 3); // 未来三个月的结束时间
        BigDecimal capacityOfNextSixMonth = new BigDecimal(0); // 未来一个月的空闲产能
        Date dateOfNextSixMonth = DateUtil.getTimeStampByAddMonths(searchDate, 6); // 未来六个月的结束时间

        // 循环生产线
        for (ProductionLine item : productionLineList) {
            ProductionLineVO productionLineVO = productionLineService.assembleProductionLineVO(item, searchYear);
            // 该生产线的排产详情列表
            List<ProductionPlanningDetail> productionPlanningDetailList = productionPlanningDetailMapper.selectByLineIdAndTime(productionLineVO.getId(), searchDate);
            // 循环该生产线的排产详情列表
            for (Integer indexOfProductionPlanningDetail=0; indexOfProductionPlanningDetail<productionPlanningDetailList.size(); indexOfProductionPlanningDetail++)
            {
                ProductionPlanningDetail productionPlanningDetail = productionPlanningDetailList.get(indexOfProductionPlanningDetail);
                // 交期异常订单数
                if (ProductionPlanningDetailUtils.getDayNumOfRemain(productionPlanningDetail) < -5) {
                    abnormalOrderNumber += 1;
                }
            }
            // 计算产能
            capacityOfNextMonth = capacityOfNextMonth.add(getSpareCapacityByProductLineIdAndTimes(productionLineVO, searchDate, dateOfNextMonth));
            capacityOfNextThreeMonth = capacityOfNextThreeMonth.add(getSpareCapacityByProductLineIdAndTimes(productionLineVO, searchDate, dateOfNextThreeMonth));
            capacityOfNextSixMonth = capacityOfNextSixMonth.add(getSpareCapacityByProductLineIdAndTimes(productionLineVO, searchDate, dateOfNextSixMonth));
        }
        dataBoardVO.setAbnormalOrderNumber(abnormalOrderNumber);
        dataBoardVO.setCapacityOfNextMonth(capacityOfNextMonth);
        dataBoardVO.setCapacityOfNextThreeMonth(capacityOfNextThreeMonth);
        dataBoardVO.setCapacityOfNextSixMonth(capacityOfNextSixMonth);
        dataBoardVO.setCompleteSewingNumberOfToday(0);
        return ServerResponse.createdBySuccess(dataBoardVO);
    }

    /**
     *  排产器的 “接单分析”，获取所有生产线的某时间段内的空闲产能
     * @param searchTimeList 查询时间 “年-月” 列表
     * @return
     */
    public ServerResponse getAllByTime(Integer userId, List<String> searchTimeList) throws ParseException {
        if (searchTimeList == null || searchTimeList.size() == 0) {
            return ServerResponse.createdByError("参数不能为空！");
        }
        List<SpareCapacityOfProductLineVO> result = new ArrayList<>();   // 返回数据

        // 取第一个月对应的年份
        Integer firstYear = Integer.parseInt(searchTimeList.get(0).split("-")[0]);

        // 获取该用户对应的生产线数据
        ServerResponse resultOfProductionLine = productionLineService.selectAllByUserId(userId, firstYear);
        List<ProductionLineVO> productionLineVOList = new ArrayList<>();
        if (resultOfProductionLine.isSuccess()) {
            productionLineVOList = (List<ProductionLineVO>) resultOfProductionLine.getData();
        }

        // 循环生产线
        for (ProductionLineVO productionLineVO : productionLineVOList) {
            SpareCapacityOfProductLineVO spareCapacityOfProductLineVO = new SpareCapacityOfProductLineVO();
            BigDecimal spareCapacity = new BigDecimal(0);
            // 循环查询 “年-月” 列表
            for (String searchTimeStr : searchTimeList) {
                Integer year = Integer.parseInt(searchTimeStr.split("-")[0]);
                Integer month = Integer.parseInt(searchTimeStr.split("-")[1]);
                // 获取对应的时间节点（开始时间和结束时间）
                Date startDate = DateUtil.getFirstDateOfMonth(year, month);
                Date endDate = DateUtil.getLastDateOfMonth(year, month);
                spareCapacity = spareCapacity.add(getSpareCapacityByProductLineIdAndTimes(productionLineVO, startDate, endDate));
            }
            spareCapacityOfProductLineVO.setSpareCapacity(spareCapacity);
            spareCapacityOfProductLineVO.setProductLineName(productionLineVO.getWorkgroup() + "-" + productionLineVO.getWorkshop() + "-" + productionLineVO.getLineCode());
            result.add(spareCapacityOfProductLineVO);
        }
        return ServerResponse.createdBySuccess(result);
    }

    /**
     * 获取指定生产线的某段时间内的空闲产能
     * @param productionLineVO 生产线信息
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    private BigDecimal getSpareCapacityByProductLineIdAndTimes(ProductionLineVO productionLineVO, Date startDate, Date endDate) {
        // 如果查询开始时间早于今天，则查询开始时间改为今天日期
        Date now = DateUtil.zeroSetting(new Date());
        if (startDate.compareTo(now) < 0) {
            startDate = now;
        }

        BigDecimal spareCapacity = new BigDecimal(0); // 这段时间内该空闲产能
        /**
        * 获取工厂日历列表
        */
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        Integer searchYear = calendar.get(Calendar.YEAR);
        List<Integer> yearList = new ArrayList<>();
        yearList.add(searchYear);
        calendar.setTime(endDate);
        Integer yearAfterSixMonth = calendar.get(Calendar.YEAR);
        if (searchYear.compareTo(yearAfterSixMonth) != 0) {
            yearList.add(yearAfterSixMonth);
        }
        ServerResponse<List<WorkingDateSettingVO>> resultOfFactoryCalendar = factoryCalendarService.getFactoryCalendarByYear(yearList);
        List<WorkingDateSettingVO> workingDateSettingVOListList = resultOfFactoryCalendar.isSuccess() ? resultOfFactoryCalendar.getData() : new ArrayList<>();

        /**
         * 1、获取空闲时间日期列表
         */
        // 获取生产线对应的排产信息列表
        List<ProductionPlanningDetail> productionPlanningDetailList = productionPlanningDetailMapper.selectByLineIdAndTime(productionLineVO.getId(), startDate);
        Date compareDate = startDate; // 用于比较
        List<Long> spareCapacityDateList = new ArrayList<>(); // 空闲时间的日期时间戳列表
        Boolean isExist = false; // 这段时间内是否拥有进度条
        // 循环排产信息列表，查看空闲的时间，并组成列表
        for (int indexOfProductionPlanningDetailList = 0; indexOfProductionPlanningDetailList < productionPlanningDetailList.size(); indexOfProductionPlanningDetailList++) {
            ProductionPlanningDetail productionPlanningDetail = productionPlanningDetailList.get(indexOfProductionPlanningDetailList);

            // 检测进度条的开始时间是否在该月的范围内
            if (productionPlanningDetail.getStartTime().getTime() <= endDate.getTime()) {
                isExist = true;
                Date startTimeOfProductionPlanningDetail = productionPlanningDetail.getStartTime();// 获取当前排产进度条的开始时间戳
                Date endTimeOfProductionPlanningDetail = productionPlanningDetail.getEndTime(); // 获取当前排产进度条的结束时间戳

                // 比较时间戳。获取空闲的日期时间戳
                List<Long> scopedDateListTemp = DateUtil.getTimeStampListByCompareTimeStamp(compareDate, startTimeOfProductionPlanningDetail);

                // 如果列表不为空，则添加到三个空闲的日期时间戳列表中
                if (scopedDateListTemp.size() != 0) {
                    spareCapacityDateList.addAll(scopedDateListTemp);
                }
                // 将结束时间作为下一个比较的时间戳
                compareDate = endTimeOfProductionPlanningDetail;
            } else {
                break;
            }
        }
        // 上述逻辑只是比较开始时间，这里有些情况还没有纳入到逻辑中，需要做最后的修正
        // 1、如果这段时间内，没有一条进度条
        // 2、如果这段时间内的最后一条进度条与结算的时间还有一段空闲时间
        List<Long> scopedDateListTemp = DateUtil.getTimeStampListByCompareTimeStamp(compareDate, endDate);
        if (scopedDateListTemp.size() != 0) {
            spareCapacityDateList.addAll(scopedDateListTemp);
        }
        // 3、由于时间比较时，比较的是零时零点，所以月的最后一天就需要进行修正，看是否要把最后一天的空闲时间补上
        if (compareDate.compareTo(endDate) < 0){
            spareCapacityDateList.add(endDate.getTime());
        }

        /**
         * 2、计算空闲产能
         */
        // 循环空闲时间日期列表
        for (Long timeStamp : spareCapacityDateList) {
            // 检查是否为节假日
            for (WorkingDateSettingVO workingDateSettingVO : workingDateSettingVOListList) {
                if (!workingDateSettingVO.isHoliday(timeStamp)) {
                    // 不是节假日则计算产能
                    spareCapacity = spareCapacity.add(productionLineVO.getWorkhours().multiply(new BigDecimal(productionLineVO.getPeopleNum())));
                }
            }
        }
        return spareCapacity;
    }
}
