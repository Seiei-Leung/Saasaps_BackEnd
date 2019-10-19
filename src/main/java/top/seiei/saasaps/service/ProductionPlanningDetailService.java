package top.seiei.saasaps.service;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.seiei.saasaps.bean.ProductionPlanningDetail;
import top.seiei.saasaps.bean.SummaryOfProductionPlanningDetail;
import top.seiei.saasaps.bean.User;
import top.seiei.saasaps.common.ServerResponse;
import top.seiei.saasaps.dao.ProductionPlanningDetailMapper;
import top.seiei.saasaps.dao.SummaryOfProductionPlanningDetailMapper;
import top.seiei.saasaps.util.DateUtil;
import top.seiei.saasaps.util.ExcelUtil;
import top.seiei.saasaps.util.PropertiesUtil;
import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.*;
import top.seiei.saasaps.util.StringUtil;


@Service("productionPlanningDetailService")
public class ProductionPlanningDetailService {

    @Resource
    private ProductionPlanningDetailMapper productionPlanningDetailMapper;

    @Resource
    private SummaryOfProductionPlanningDetailMapper summaryOfProductionPlanningDetailMapper;

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
                int rowCount = sheet.getLastRowNum(); // 表格的总行数
                SummaryOfProductionPlanningDetail summaryOfProductionPlanningDetail = new SummaryOfProductionPlanningDetail();
                summaryOfProductionPlanningDetail.setBillno(billNo);
                summaryOfProductionPlanningDetail.setUpdateUserId(user.getId());
                summaryOfProductionPlanningDetail.setUpdateTime(new Date());
                summaryOfProductionPlanningDetail.setCreateTime(new Date());
                summaryOfProductionPlanningDetailMapper.insertSelective(summaryOfProductionPlanningDetail);
                for (int rowIndex=1; rowIndex<rowCount; rowIndex++) {
                    Row rowItem = sheet.getRow(rowIndex);
                    ProductionPlanningDetail productionPlanningDetail = new ProductionPlanningDetail();
                    productionPlanningDetail.setSummaryid(summaryOfProductionPlanningDetail.getId());
                    productionPlanningDetail.setSeason(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.season")));
                    productionPlanningDetail.setClientname(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.clientName")));
                    productionPlanningDetail.setClientstyleno(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.clientStyleNo")));
                    productionPlanningDetail.setOrderno(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.orderNo")));
                    productionPlanningDetail.setOrdernum(ExcelUtil.getIntegerCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.orderNum")));
                    productionPlanningDetail.setOrderkind(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.orderKind")));
                    productionPlanningDetail.setStyleno(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.styleNo")));
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
                    if (ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.sam")) == null) {
                        productionPlanningDetail.setSam(null);
                    } else {
                        productionPlanningDetail.setSam(new BigDecimal(ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.sam"))));
                    }
                    if (ExcelUtil.getStringCell(rowItem, PropertiesUtil.getIntegerProperty("excel.productionPlanningDetail.samOfLocal")) == null) {
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
}
