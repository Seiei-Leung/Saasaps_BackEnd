package top.seiei.saasaps.service;
import java.util.Date;
import java.math.BigDecimal;
import java.util.*;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.seiei.saasaps.bean.ProductClass;
import top.seiei.saasaps.bean.ProductionPlanningDetail;
import top.seiei.saasaps.bean.SummaryOfProductionPlanningDetail;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ProductClassMapper;
import top.seiei.saasaps.dao.ProductionPlanningDetailMapper;
import top.seiei.saasaps.dao.SummaryOfProductionPlanningDetailMapper;
import top.seiei.saasaps.util.DateUtil;
import top.seiei.saasaps.util.ExcelUtil;
import top.seiei.saasaps.util.PropertiesUtil;
import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.*;
import top.seiei.saasaps.util.StringUtil;
import top.seiei.saasaps.vo.ProductionLineVO;


@Service("productionPlanningDetailService")
public class ProductionPlanningDetailService {

    @Resource
    private ProductionPlanningDetailMapper productionPlanningDetailMapper;

    @Resource
    private SummaryOfProductionPlanningDetailMapper summaryOfProductionPlanningDetailMapper;

    @Resource
    private ProductClassService productClassService;

    @Resource
    private ProductClassMapper productClassMapper;

    /**
     * 导入 excel
     * @param user 用户信息
     * @param excelFile excel 文件
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor=Exception.class)
    public ServerResponse uploadExcel(User user, MultipartFile excelFile) throws Exception {
        String billNo = getBillNo();
        if (excelFile != null) {
            // 获取文件的扩展名
            String ext = FilenameUtils.getExtension(excelFile.getOriginalFilename()); // 图片文件后缀名
            if (!StringUtils.equals(ext, ExcelUtil.OFFICE_EXCEL_XLS) && !StringUtils.equals(ext, ExcelUtil.OFFICE_EXCEL_XLSX)) {
                return ServerResponse.createdByError("上传文件的格式不正确");
            }
            try {
                String pathName = PropertiesUtil.getProperty("uploadExcelPath") + billNo + "." + ext;
                excelFile.transferTo(new File(pathName));
                Sheet sheet = ExcelUtil.getSheet(pathName, 0); // sheet 表
                int rowCount = sheet.getLastRowNum() + 1; // 表格的总行数
                SummaryOfProductionPlanningDetail summaryOfProductionPlanningDetail = new SummaryOfProductionPlanningDetail();
                summaryOfProductionPlanningDetail.setBillno(billNo);
                summaryOfProductionPlanningDetail.setUpdateUserId(user.getId());
                summaryOfProductionPlanningDetail.setUpdateTime(new Date());
                summaryOfProductionPlanningDetail.setCreateTime(new Date());
                summaryOfProductionPlanningDetailMapper.insertSelective(summaryOfProductionPlanningDetail);
                for (int rowIndex=1; rowIndex<rowCount; rowIndex++) {
                    Row rowItem = sheet.getRow(rowIndex);
                    if (StringUtils.isBlank(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.season")))) {
                        break;
                    }
                    ProductionPlanningDetail productionPlanningDetail = new ProductionPlanningDetail();
                    productionPlanningDetail.setSummaryid(summaryOfProductionPlanningDetail.getId());
                    productionPlanningDetail.setSeason(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.season")));
                    productionPlanningDetail.setClientname(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.clientName")));
                    productionPlanningDetail.setClientstyleno(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.clientStyleNo")));
                    productionPlanningDetail.setOrderno(ExcelUtil.getPrueStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.orderNo")));
                    productionPlanningDetail.setOrdernum(ExcelUtil.getIntegerCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.orderNum")));
                    productionPlanningDetail.setOrderkind(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.orderKind")));
                    productionPlanningDetail.setStyleno(ExcelUtil.getPrueStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.styleNo")));
                    productionPlanningDetail.setGoodname(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.goodName")));
                    productionPlanningDetail.setStyle(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.style")));
                    productionPlanningDetail.setDeliveryofcontractTime(ExcelUtil.getDateCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.deliveryOfContract_time")));
                    productionPlanningDetail.setDeliveryoffactoryTime(ExcelUtil.getDateCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.deliveryOfFactory_time")));
                    productionPlanningDetail.setArrivewarehouseTime(ExcelUtil.getDateCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.arriveWarehouse_time")));
                    productionPlanningDetail.setQtyofbatcheddelivery(ExcelUtil.getIntegerCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.qtyOfBatchedDelivery")));
                    productionPlanningDetail.setLining(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.lining")));
                    productionPlanningDetail.setLiningofstitchingTime(ExcelUtil.getDateCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.liningOfStitching_time")));
                    productionPlanningDetail.setSuppliesoflining(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.suppliesOfLining")));
                    productionPlanningDetail.setClothTime(ExcelUtil.getDateCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.cloth_time")));
                    productionPlanningDetail.setColor(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.color")));
                    productionPlanningDetail.setSizes(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.sizes")));
                    if (ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.sam")) == null) {
                        productionPlanningDetail.setSam(null);
                    } else {
                        productionPlanningDetail.setSam(new BigDecimal(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.sam"))));
                    }
                    if (ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.samOfLocal")) == null || StringUtils.isBlank(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.samOfLocal")))) {
                        productionPlanningDetail.setSamoflocal(null);
                    } else {
                        productionPlanningDetail.setSamoflocal(new BigDecimal(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.samOfLocal"))));
                    }
                    if (ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.sah")) == null) {
                        productionPlanningDetail.setSah(null);
                    } else {
                        productionPlanningDetail.setSah(new BigDecimal(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.sah"))));
                    }
                    productionPlanningDetail.setApproveTime(ExcelUtil.getDateCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.approve_time")));
                    productionPlanningDetail.setEmbroider(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.embroider")));
                    productionPlanningDetail.setEmbroiderDaynum(ExcelUtil.getIntegerCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.embroider_dayNum")));
                    productionPlanningDetail.setEmbroiderTime(ExcelUtil.getDateCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.embroider_time")));
                    productionPlanningDetail.setFactoryEmbroider(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.factory_embroider")));
                    productionPlanningDetail.setFactoryEmbroider2(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.factory_embroider2")));
                    productionPlanningDetail.setPrintafterembroider(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.printAfterembroider")));
                    productionPlanningDetail.setPrintafterembroiderDaynum(ExcelUtil.getIntegerCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.printAfterembroider_dayNum")));
                    productionPlanningDetail.setFactoryPrint(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.factory_print")));
                    productionPlanningDetail.setFactoryPrint2(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.factory_print2")));
                    productionPlanningDetail.setBackpartDaynum(ExcelUtil.getIntegerCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.backPart_dayNum")));
                    productionPlanningDetail.setMemo(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.memo")));
                    productionPlanningDetail.setCuttingqty(ExcelUtil.getIntegerCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.cuttingQty")));
                    productionPlanningDetail.setAdvancecuttingDaynum(ExcelUtil.getIntegerCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.advanceCutting_dayNum")));

                    productionPlanningDetail.setIsPlanning(false);
                    productionPlanningDetail.setIsFinishCutting(false);
                    productionPlanningDetail.setUpdateUserId(user.getId());
                    productionPlanningDetail.setCreateTime(new Date());
                    productionPlanningDetail.setUpdateTime(new Date());
                    int result = productionPlanningDetailMapper.insertSelective(productionPlanningDetail);
                    if (result == 0) {
                        throw new Exception("插入数据错误，事务回滚");
                    }
                }
                return ServerResponse.createdBySuccess(summaryOfProductionPlanningDetail);
            } catch (IOException e) {
                e.printStackTrace();
                return ServerResponse.createdByError(e.getMessage());
            }
        } else {
            return ServerResponse.createdByError("Excel 文件不能为空");
        }
    }

    /**
     * 根据主表 Id 获取详情列表
     * @param id 主表 Id
     * @return
     */
    public ServerResponse getDetailBySummaryId(Integer id) {
        SummaryOfProductionPlanningDetail summaryOfProductionPlanningDetail = summaryOfProductionPlanningDetailMapper.selectByPrimaryKey(id);
        if (summaryOfProductionPlanningDetail == null) {
            return ServerResponse.createdByError("没有对应的信息");
        }
        List<ProductionPlanningDetail> productionPlanningDetailList = productionPlanningDetailMapper.selectBySummaryId(id);
        return ServerResponse.createdBySuccess(productionPlanningDetailList);
    }

    /**
     * 更新详情
     * @param user 用户信息
     * @param productionPlanningDetail 详情信息
     * @return
     */
    public ServerResponse updateDetail(User user, ProductionPlanningDetail productionPlanningDetail) {
        ProductionPlanningDetail productionPlanningDetailTemp = productionPlanningDetailMapper.selectByPrimaryKey(productionPlanningDetail.getId());
        if (productionPlanningDetailTemp == null) {
            return ServerResponse.createdByError("查无该详情信息");
        }
        productionPlanningDetail.setUpdateUserId(user.getId());
        productionPlanningDetail.setUpdateTime(new Date());
        int result = productionPlanningDetailMapper.updateByPrimaryKeySelective(productionPlanningDetail);
        if (result == 0) {
            return ServerResponse.createdByError("更新失败");
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 删除详情
     * @param id 主键
     * @return
     */
    public ServerResponse deleteById(Integer id) {
        ProductionPlanningDetail productionPlanningDetailTemp = productionPlanningDetailMapper.selectByPrimaryKey(id);
        if (productionPlanningDetailTemp == null) {
            return ServerResponse.createdByError("查无该详情信息");
        }
        int result = productionPlanningDetailMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createdByError("删除失败");
        }
        return ServerResponse.createdByError("删除成功");
    }

    /**
     * 获取单号
     * @return
     */
    private String getBillNo() {
        String billno = "PD" + DateUtil.getBillFormat();
        List<SummaryOfProductionPlanningDetail> summaryOfProductionPlanningDetailList = summaryOfProductionPlanningDetailMapper.selectByLikeBillNo("%" + billno + "%");
        if (summaryOfProductionPlanningDetailList.size() == 0) {
            billno += "001";
        } else {
            Integer count = Integer.parseInt(summaryOfProductionPlanningDetailList.get(0).getBillno().substring(8)) + 1;
            billno = billno + StringUtil.zeroFill(Integer.toString(count), 3);
        }
        return billno;
    }

    // ---------------------------------------------- 排产器页面 -------------------------------------------------------

    /**
     * 获取所有等待排产的进度条
     * @return
     */
    public ServerResponse getAllForAddProgress() {
        List<ProductionPlanningDetail> productionPlanningDetailList = productionPlanningDetailMapper.selectAllForAddProgress();
        List<ProductionPlanningDetail> resultProductionPlanningDetailList = setEfficiencyOfClassAndProductStyleName(productionPlanningDetailList);
        return ServerResponse.createdBySuccess(resultProductionPlanningDetailList);
    }

    /**
     * 为详情添加效率和产品类名
     * @param productionPlanningDetailList
     * @return
     */
    public List<ProductionPlanningDetail> setEfficiencyOfClassAndProductStyleName(List<ProductionPlanningDetail> productionPlanningDetailList) {
        List<ProductionPlanningDetail> resultProductionPlanningDetailList = new ArrayList<>();
        List<ProductClass> productClassList = productClassMapper.selectAll();
        for (ProductionPlanningDetail item : productionPlanningDetailList) {
            ServerResponse serverResponse = productClassService.getProductClassEfficiencyByProductClassNameAndQtyPlan(item.getGoodname(), item.getQtyofbatcheddelivery());
            if (serverResponse.isSuccess()) {
                item.setEfficiencyOfClass((BigDecimal) serverResponse.getData());
                for (ProductClass item2 : productClassList) {
                    if (StringUtils.equals(item2.getName(), item.getGoodname())) {
                        item.setProductStyleName(item2.getProductStyleName());
                        item.setProphaseLowEfficiencyOfClass(item2.getProphaseLowEfficiency());
                    }
                }
                resultProductionPlanningDetailList.add(item);
            }
        }
        return resultProductionPlanningDetailList;
    }

    /**
     * 新增排产、移动排产信息数据更新
     * @param user 用户信息
     * @param dataList 数据列表
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public ServerResponse updateProgress(User user, List<Map<String, String>> dataList) {
        List<ProductionPlanningDetail> productionPlanningDetailList = new ArrayList<>();
        List<Map<String, String>> dataListOfInsert = new ArrayList<>(); // 拆单而新增的排产进度条列表
        List<Integer> listOfIDForLogicallyDelete = new ArrayList<>(); // 需要逻辑删除的排产进度条的 ID 列表
        List<Integer> listOfIDForPhysicallyDelete = new ArrayList<>(); // 需要物理删除的排产进度条的 ID 列表
        for (Map<String, String> item : dataList) {
            String idStr = item.get("id");
            // 判断是否为拆单所产生的新增排产进度条
            if (idStr.contains("NEW_")) {
                dataListOfInsert.add(item);
                continue;
            }
            // 判断是否为需要逻辑删除的进度条 ID 信息
            if (idStr.contains("LOGICALLY_DELETE_")) {
                listOfIDForLogicallyDelete.add(Integer.parseInt(idStr.replace("LOGICALLY_DELETE_", "")));
                continue;
            }
            // 判断是否为需要物理删除的精度条 ID 信息
            if (item.get("id").contains("PHYSICALLY_DELETE_")) {
                listOfIDForPhysicallyDelete.add(Integer.parseInt(idStr.replace("PHYSICALLY_DELETE_", "")));
                continue;
            }

            Integer id = Integer.parseInt(item.get("id"));
            String orderno = item.get("orderno");
            Date startTime = new Date(Long.parseLong(item.get("startTime")));
            Date endTime = new Date(Long.parseLong(item.get("endTime")));
            Integer productionLineId = Integer.parseInt(item.get("productionLineId"));
            Integer qtyFinish = Integer.parseInt(item.get("qtyFinish")); // 更新工作完成数量
            Integer qtyofbatcheddelivery = Integer.parseInt(item.get("qtyofbatcheddelivery")); // 更新计划数量
            BigDecimal efficiencyBySetting = StringUtil.strToBigDeciaml(item.get("efficiencyOfSetting")); // 更新自选效率

            ProductionPlanningDetail productionPlanningDetail = productionPlanningDetailMapper.selectByPrimaryKey(id);
            if (productionPlanningDetail == null) {
                return ServerResponse.createdByError("某条进度排产不存在");
            }
            ProductionPlanningDetail productionPlanningDetail1ForUpdate = new ProductionPlanningDetail();
            productionPlanningDetail1ForUpdate.setId(id);
            productionPlanningDetail1ForUpdate.setOrderno(orderno);
            productionPlanningDetail1ForUpdate.setIsPlanning(true);
            productionPlanningDetail1ForUpdate.setProductionlineid(productionLineId);
            productionPlanningDetail1ForUpdate.setStartTime(startTime);
            productionPlanningDetail1ForUpdate.setEndTime(endTime);
            productionPlanningDetail1ForUpdate.setQtyFinish(qtyFinish);
            productionPlanningDetail1ForUpdate.setQtyofbatcheddelivery(qtyofbatcheddelivery);
            productionPlanningDetail1ForUpdate.setUpdateUserId(user.getId());
            productionPlanningDetail1ForUpdate.setUpdateTime(new Date());
            productionPlanningDetail1ForUpdate.setEfficiencyBySetting(efficiencyBySetting);
            productionPlanningDetailList.add(productionPlanningDetail1ForUpdate);
        }
        for (ProductionPlanningDetail item : productionPlanningDetailList) {
            productionPlanningDetailMapper.updateByPrimaryKeySelective(item);
        }
        // 拆单而新增的排产进度条
        for (Map<String, String> item : dataListOfInsert) {
            String orderno = item.get("orderno");
            Date startTime = new Date(Long.parseLong(item.get("startTime")));
            Date endTime = new Date(Long.parseLong(item.get("endTime")));
            Integer productionLineId = Integer.parseInt(item.get("productionLineId"));
            Integer qtyFinish = Integer.parseInt(item.get("qtyFinish")); // 更新工作完成数量
            Integer qtyofbatcheddelivery = Integer.parseInt(item.get("qtyofbatcheddelivery")); // 更新计划数量
            BigDecimal efficiencyBySetting = StringUtil.strToBigDeciaml(item.get("efficiencyOfSetting")); // 更新自选效率
            Integer parentId = Integer.parseInt(item.get("parentId"));
            ProductionPlanningDetail productionPlanningDetailOfParent = productionPlanningDetailMapper.selectByPrimaryKey(parentId); // 拆单前的原排产
            productionPlanningDetailOfParent.setId(null);
            productionPlanningDetailOfParent.setOrderno(orderno);
            productionPlanningDetailOfParent.setStartTime(startTime);
            productionPlanningDetailOfParent.setEndTime(endTime);
            productionPlanningDetailOfParent.setProductionlineid(productionLineId);
            productionPlanningDetailOfParent.setQtyofbatcheddelivery(qtyofbatcheddelivery);
            productionPlanningDetailOfParent.setQtyFinish(0);
            productionPlanningDetailOfParent.setCreateTime(new Date());
            productionPlanningDetailOfParent.setUpdateTime(new Date());
            productionPlanningDetailOfParent.setUpdateUserId(user.getId());
            productionPlanningDetailOfParent.setEfficiencyBySetting(efficiencyBySetting);
            productionPlanningDetailOfParent.setQtyFinish(qtyFinish);
            productionPlanningDetailMapper.insertSelective(productionPlanningDetailOfParent);
        }
        // 执行逻辑删除逻辑
        for (Integer idForLogicallyDelete : listOfIDForLogicallyDelete) {
            resetProgress(user, idForLogicallyDelete);
        }
        // 执行物理删除逻辑
        for (Integer idForPhysicallyDelete : listOfIDForPhysicallyDelete) {
            productionPlanningDetailMapper.deleteByPrimaryKey(idForPhysicallyDelete);
        }
        return ServerResponse.createdBySuccessMessage("更新成功");
    }

    /**
     * 排产器删除已排产计划
     * @param user 用户信息
     * @param id 排产进度 Id
     * @return
     */
    public ServerResponse resetProgress(User user, Integer id) {
        ProductionPlanningDetail productionPlanningDetail = productionPlanningDetailMapper.selectByPrimaryKey(id);
        if (productionPlanningDetail == null) {
            return ServerResponse.createdByError("该排产进度不存在");
        }
        productionPlanningDetail.setIsPlanning(false);
        productionPlanningDetail.setQtyFinish(0);
        productionPlanningDetail.setStartTime(null);
        productionPlanningDetail.setEndTime(null);
        productionPlanningDetail.setProductionlineid(null);
        productionPlanningDetail.setEfficiencyBySetting(null);
        productionPlanningDetail.setUpdateUserId(user.getId());
        productionPlanningDetailMapper.updateByPrimaryKey(productionPlanningDetail);
        return ServerResponse.createdBySuccess("删除成功");
    }

    /**
     * 更新排产计划的批注
     * @param user 修改用户信息
     * @param params 参数
     * @return
     */
    public ServerResponse updateMemoOfProgress(User user, Map<String, String> params) {
        if (StringUtils.isBlank(params.get("id"))) {
            return ServerResponse.createdByError("传入的排产计划主键不能为空！！");
        }
        Integer id = Integer.parseInt(params.get("id")); // 排产计划主键
        ProductionPlanningDetail productionPlanningDetail = productionPlanningDetailMapper.selectByPrimaryKey(id);
        if (productionPlanningDetail == null) {
            return ServerResponse.createdByError("该排产进度不存在");
        }
        String memo = StringUtils.isBlank(params.get("memo")) ? "" : params.get("memo");
        productionPlanningDetail.setMemo(memo);
        productionPlanningDetail.setUpdateUserId(user.getId());
        productionPlanningDetail.setUpdateTime(new Date());
        productionPlanningDetailMapper.updateByPrimaryKeySelective(productionPlanningDetail);
        return ServerResponse.createdBySuccess();
    }

    /**
     * 删除排产进度 Memo
     * @param user 用户
     * @param id 排产进度主键
     * @return
     */
    public ServerResponse deleteMemo(User user, Integer id) {
        ProductionPlanningDetail productionPlanningDetail = productionPlanningDetailMapper.selectByPrimaryKey(id);
        if (productionPlanningDetail == null) {
            return ServerResponse.createdByError("该排产进度不存在");
        }
        productionPlanningDetail.setUpdateTime(new Date());
        productionPlanningDetail.setUpdateUserId(user.getId());
        productionPlanningDetail.setMemo("");
        productionPlanningDetailMapper.updateByPrimaryKeySelective(productionPlanningDetail);
        return ServerResponse.createdBySuccess();
    }
}
