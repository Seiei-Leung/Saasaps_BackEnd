package top.seiei.saasaps.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 操作 Excel 工具类
 */
public class ExcelUtil {

    public static final String OFFICE_EXCEL_XLS = "xls";
    public static final String OFFICE_EXCEL_XLSX = "xlsx";

    /**
     * 读取时间类型的 cell
     * @param row row 对象
     * @param cellIndex 列索引
     * @return
     * @throws ParseException
     */
    public static Date getDateCell(Row row, Integer cellIndex) throws ParseException {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return null;
        }
        Date date = null;
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            date = DateUtil.zeroSetting(new SimpleDateFormat("yyyy-MM-dd").parse(cell.toString()));
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            date = cell.getDateCellValue();
        }
        return date;
    }

    /**
     * 获取字符串类型的 cell 值
     * @param row row 对象
     * @param cellIndex 列索引
     * @return
     */
    public static String getStringCell(Row row, Integer cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return Double.toString(cell.getNumericCellValue());
        }
        return cell.toString();
    }

    /**
     * 获取字符串类型的 cell 值
     * @param row row 对象
     * @param cellIndex 列索引
     * @return
     */
    public static String getPrueStringCell(Row row, Integer cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return null;
        }
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.toString();
    }

    public static String getBigDecimal(Row row, Integer cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return "0";
        }
        return cell.toString();
    }


    /**
     * 获取数字类型的 Cell 值
     * @param row row 对象
     * @param cellIndex 列索引
     * @return
     */
    public static Integer getIntegerCell(Row row, Integer cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return null;
        }
        if (StringUtils.isBlank(cell.toString())) {
            return null;
        }
        return Integer.parseInt(cell.toString().split("\\.")[0]);
    }

    /**
     * 获取表格中的某个单元格
     * @param sheet 表格 sheet 对象
     * @param cellIndex 列索引
     * @param rowIndex 行索引
     * @return
     */
    public static Cell readExcelByIndex(Sheet sheet, Integer cellIndex, Integer rowIndex) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(cellIndex);
        return cell;
    }

    /**
     * 获取 Sheet 对象
     * @param filePath 文件路径
     * @param sheetIndex sheet 索引
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static Sheet getSheet(String filePath, Integer sheetIndex) throws IOException, InvalidFormatException {
        Workbook workbook = getWorkbook(filePath);
        Sheet sheet;
        if (sheetIndex == null) {
            sheet = workbook.getSheetAt(0);
        } else {
            sheet = workbook.getSheetAt(sheetIndex);
        }
        return sheet;
    }

    /**
     * 根据文件路径获取Workbook对象
     * @param filepath 文件全路径
     */
    public static Workbook getWorkbook(String filepath)
            throws EncryptedDocumentException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        InputStream is = null;
        Workbook wb = null;
        if (StringUtils.isBlank(filepath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        } else {
            String suffiex = getSuffiex(filepath);
            if (StringUtils.isBlank(suffiex)) {
                throw new IllegalArgumentException("文件后缀不能为空");
            }
            if (OFFICE_EXCEL_XLS.equals(suffiex) || OFFICE_EXCEL_XLSX.equals(suffiex)) {
                try {
                    is = new FileInputStream(filepath);
                    wb = WorkbookFactory.create(is);
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            } else {
                throw new IllegalArgumentException("该文件非Excel文件");
            }
        }
        return wb;
    }

    /**
     * 读取指定Sheet页的表头
     */
    public static Row readTitle(Sheet sheet) throws IOException {
        Row returnRow = null;
        int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
        for (int i = 0; i < totalRow; i++) {// 遍历行
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            returnRow = sheet.getRow(0);
            break;
        }
        return returnRow;
    }

    /**
     * 获取后缀
     * @param filepath filepath 文件全路径
     */
    private static String getSuffiex(String filepath) {
        if (StringUtils.isBlank(filepath)) {
            return "";
        }
        int index = filepath.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return filepath.substring(index + 1, filepath.length());
    }

    public static void main(String[] args) throws IOException, InvalidFormatException, ParseException {
        //readExcel("D:\\Documents\\Tencent Files\\786883603\\FileRecv\\20Q1斐乐成人米兰海外（9-25入TXT数 ）.xls", null);
/*        Workbook workbook = getWorkbook("F:\\123.xls");
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = readExcelByIndex(sheet, 9, 3);
        System.out.println(cell.getCellType());
        System.out.println(cell.getNumericCellValue());*/
        String str = "false";
        System.out.println(Boolean.getBoolean(str));
    }


}
