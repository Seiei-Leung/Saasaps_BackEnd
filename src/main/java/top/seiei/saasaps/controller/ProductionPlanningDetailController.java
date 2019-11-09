package top.seiei.saasaps.controller;

import java.util.Date;

import org.apache.logging.log4j.core.jmx.Server;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.seiei.saasaps.bean.ProductionPlanningDetail;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.Const;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.service.ProductionPlanningDetailService;
import top.seiei.saasaps.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/productionplanningdetail/")
public class ProductionPlanningDetailController {

    @Resource
    private ProductionPlanningDetailService productionPlanningDetailService;

    /**
     * 导入 Excel
     * @param session session 对象
     * @param excelFile Excel 文件
     * @return
     */
    @RequestMapping(value="uploadExcel", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse uploadExcel(HttpSession session, @RequestParam("excelFile") MultipartFile excelFile) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        try {
            return productionPlanningDetailService.uploadExcel(user, excelFile);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createdByError("读取Excel发生错误");
        }
    }

    /**
     * 根据主表 Id 获取详情列表
     * @param id 主表 Id
     * @return
     */
    @RequestMapping("getDetailBySummaryId")
    @ResponseBody
    public ServerResponse getDetailBySummaryId(Integer id) {
        return productionPlanningDetailService.getDetailBySummaryId(id);
    }

    /**
     *  更新详情
     * @param session session session 对象
     * @param params 详情信息
     * @return
     */
    @RequestMapping(value = "updateDetail", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateDetail(HttpSession session, @RequestBody Map<String, String> params) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        ProductionPlanningDetail productionPlanningDetail = new ProductionPlanningDetail();
        productionPlanningDetail.setId(Integer.parseInt(params.get("id")));
        productionPlanningDetail.setSeason(params.get("season"));
        productionPlanningDetail.setClientname(params.get("clientname"));
        productionPlanningDetail.setClientstyleno(params.get("clientstyleno"));
        productionPlanningDetail.setOrderno(params.get("orderno"));
        productionPlanningDetail.setOrderkind(params.get("orderkind"));
        // todo
        productionPlanningDetail.setStyleno(null);
        productionPlanningDetail.setEmbroider(null);
        productionPlanningDetail.setPrintafterembroider(null);
        productionPlanningDetail.setPrintafterembroiderDaynum(null);
        productionPlanningDetail.setFactoryPrint2(null);
        // todo
        productionPlanningDetail.setGoodname(params.get("goodname"));
        productionPlanningDetail.setStyle(params.get("style"));
        productionPlanningDetail.setOrdernum(StringUtil.strToInteger(params.get("ordernum")));
        productionPlanningDetail.setDeliveryofcontractTime(StringUtil.strToDate(params.get("deliveryofcontractTime")));
        productionPlanningDetail.setDeliveryoffactoryTime(StringUtil.strToDate(params.get("deliveryoffactoryTime")));
        productionPlanningDetail.setArrivewarehouseTime(StringUtil.strToDate(params.get("arrivewarehouseTime")));
        productionPlanningDetail.setQtyofbatcheddelivery(StringUtil.strToInteger(params.get("qtyofbatcheddelivery")));
        productionPlanningDetail.setLining(params.get("lining"));
        productionPlanningDetail.setLiningofstitchingTime(StringUtil.strToDate(params.get("liningofstitchingTime")));
        productionPlanningDetail.setSuppliesoflining(params.get("suppliesoflining"));
        productionPlanningDetail.setClothTime(StringUtil.strToDate(params.get("clothTime")));
        productionPlanningDetail.setSam(StringUtil.strToBigDeciaml(params.get("sam")));
        productionPlanningDetail.setSamoflocal(StringUtil.strToBigDeciaml(params.get("samoflocal")));
        productionPlanningDetail.setSah(StringUtil.strToBigDeciaml(params.get("sah")));
        productionPlanningDetail.setApproveTime(StringUtil.strToDate(params.get("approveTime")));
        productionPlanningDetail.setEmbroiderDaynum(StringUtil.strToInteger(params.get("embroiderDaynum")));
        productionPlanningDetail.setEmbroiderTime(StringUtil.strToDate(params.get("embroiderTime")));
        productionPlanningDetail.setFactoryEmbroider(params.get("factoryEmbroider"));
        productionPlanningDetail.setFactoryEmbroider2(params.get("factoryEmbroider2"));
        productionPlanningDetail.setFactoryPrint(params.get("factoryPrint"));
        productionPlanningDetail.setBackpartDaynum(StringUtil.strToInteger(params.get("backpartDaynum")));
        productionPlanningDetail.setMemo(params.get("memo"));
        productionPlanningDetail.setCuttingqty(StringUtil.strToInteger(params.get("cuttingqty")));
        productionPlanningDetail.setIsFinishCutting(StringUtil.strToBoolean(params.get("isFinishCutting")));
        productionPlanningDetail.setAdvancecuttingDaynum(StringUtil.strToInteger(params.get("advancecuttingDaynum")));
        productionPlanningDetail.setColor(params.get("color"));
        productionPlanningDetail.setSizes(params.get("sizes"));
        productionPlanningDetail.setUpdateUserId(user.getId());
        productionPlanningDetail.setUpdateTime(new Date());
        return productionPlanningDetailService.updateDetail(user, productionPlanningDetail);
    }

    /**
     * 删除详情
     * @param session session 对象
     * @param id 主键
     * @return
     */
    @RequestMapping("deleteDetail")
    @ResponseBody
    public ServerResponse deleteDetail(HttpSession session, Integer id) {
        return productionPlanningDetailService.deleteById(id);
    }

    // ---------------------------------------------- 排产器页面 -------------------------------------------------------

    /**
     * 获取所有等待排产的进度条
     * @param session session 对象
     * @return
     */
    @RequestMapping("getAllForAddProgress")
    @ResponseBody
    public ServerResponse getAllForAddProgress(HttpSession session) {
        return productionPlanningDetailService.getAllForAddProgress();
    }

    /**
     * 新增排产、移动排产信息数据更新
     * @param session session 对象
     * @param params 数据列表
     * @return
     */
    @RequestMapping(value = "updateProgress", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateProgress(HttpSession session,@RequestBody List<Map<String, String>> params) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionPlanningDetailService.updateProgress(user, params);
    }

    /**
     * 排产器删除已排产计划
     * @param session session 对象
     * @param id 排产进度 Id
     * @return
     */
    @RequestMapping("resetProgress")
    @ResponseBody
    public ServerResponse resetProgress(HttpSession session, Integer id) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return productionPlanningDetailService.resetProgress(user, id);
    }
}
