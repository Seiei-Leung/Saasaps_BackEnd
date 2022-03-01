package top.seiei.saasaps.util;

import top.seiei.saasaps.bean.ProductionPlanningDetail;

public class ProductionPlanningDetailUtils {

    public static Double getDayNumOfRemain(ProductionPlanningDetail productionPlanningDetail) {
        Integer dayNumOfRemain = productionPlanningDetail.getBackpartDaynum() == null ? 0 : productionPlanningDetail.getBackpartDaynum();
        Long timeStamp = productionPlanningDetail.getDeliveryoffactoryTime().getTime() - productionPlanningDetail.getEndTime().getTime() - dayNumOfRemain * DateUtil.timeStampOfOneDay();
        Double dayCount = Math.ceil(timeStamp/DateUtil.timeStampOfOneDay());
        return dayCount;
    }
}
