package com.daily.export.util;


import com.daily.common.utils.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExportExcelUtil {
    /**
     * remark
     *
     * @param fileName 文件名称   Excel标题
     * @param thead    表头
     * @param data     数据 List<LinkedHashMap<String,Object>>
     * @date 2020/6/29
     */
    public static void exportExcel(String fileName, HttpServletResponse response, List<String> thead, List<?> data) {
        HSSFWorkbook workbook = new HSSFWorkbook(); // 申明一个工作簿
        HSSFSheet sheet = workbook.createSheet(); // 生成一个表格
        sheet.setHorizontallyCenter(false);

        HSSFFont fonts = workbook.createFont();
        fonts.setFontHeightInPoints((short) 12); // 字体高度
        fonts.setFontName("宋体"); // 字体

        HSSFCellStyle cellStyle = cellStyle(workbook, fonts);
        HSSFCellStyle StyleTitle = titleStyle(workbook);
        HSSFCellStyle StyleSearch = workbook.createCellStyle();
        StyleSearch.setFont(fonts);

        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(55);// ==30*20设置高度

        ArrayList<String> headers = new ArrayList<String>();
        for (int i = 0; i < thead.size(); i++) {
            headers.add(thead.get(i));
            sheet.setColumnWidth(i, 15 * 200);
        }
        sheet.setMargin(HSSFSheet.LeftMargin, 0.544);
        sheet.setMargin(HSSFSheet.RightMargin, 0.394);

        HSSFCell cell;
        int j = 0;
        cell = row.createCell(j++);
        cell.setCellStyle(StyleTitle);
        cell.setCellValue(fileName);

        row = sheet.createRow(j++);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.size() - 1));
        cell = row.createCell(0);
        cell.setCellStyle(StyleSearch);
        cell.setCellValue("查询时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 表头
        row = sheet.createRow(j++);
        for (int i = 0; i < headers.size(); i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers.get(i).split("#")[0]);
            cell.setCellValue(text); // 格式化单元格内容
        }

        int count = 0;
        if (data.size() < 60000) {
            workbook.setSheetName(count, "第0~" + data.size() + "条");
        } else {
            workbook.setSheetName(count, "第0~60000条");
        }
        Integer start = 0;
        Integer end = 60000;
        if (data.size() > 0) {
            for (int x = 0; x < data.size(); x++) {
                Map<String, Object> map = (Map<String, Object>) data.get(x);
                if (x != 0 && x % 60000 == 0) {
                    start = end + 1;
                    if ((end + 60000) > data.size()) {
                        end = data.size();
                    } else {
                        end = end + 60000;
                    }
                    sheet = creatSheet(thead, workbook, fonts);
                    count++;
                    workbook.setSheetName(count, "第" + start + "~" + end + "条");
                    j = 1;
                }
                row = sheet.createRow(j++);
                int i = 0;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    cell = row.createCell(i++);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(new HSSFRichTextString(StringUtil.getString(entry.getValue())));
                }
            }
        }
        OutputStream out;
        try {
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("gbk"), StandardCharsets.ISO_8859_1)
                    + ".xls");

            out = response.getOutputStream();
            workbook.write(out);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("导出结束");
        }
    }

    public static HSSFSheet creatSheet(List<String> thead, HSSFWorkbook workbook, HSSFFont font) {
        HSSFSheet sheet = workbook.createSheet();
        sheet.setHorizontallyCenter(false);
        ArrayList<String> headers = new ArrayList<String>();
        for (int i = 0; i < thead.size(); i++) {
            headers.add(thead.get(i));
            sheet.setColumnWidth(i, 15 * 200);
        }
        sheet.setMargin(HSSFSheet.LeftMargin, 0.544);
        sheet.setMargin(HSSFSheet.RightMargin, 0.394);
        HSSFCell cell;
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle cellStyle = cellStyle(workbook, font);
        for (int i = 0; i < headers.size(); i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers.get(i).split("#")[0]);
            cell.setCellValue(text); // 格式化单元格内容
        }
        return sheet;
    }

    public static HSSFCellStyle titleStyle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 18); // 字体高度
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体增粗
        font.setFontName("宋体"); // 字体
        HSSFCellStyle StyleTitle = workbook.createCellStyle();
        StyleTitle.setFont(font);
        StyleTitle.setWrapText(true);
        StyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
        StyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        return StyleTitle;
    }

    public static HSSFCellStyle cellStyle(HSSFWorkbook workbook, HSSFFont font) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中 // 设置单元格类型(标题)
        return cellStyle;
    }

    public static HSSFCellStyle StyleCenter(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体"); // 字体
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setWrapText(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        return style;
    }

    public static HSSFCellStyle StyleCenterBorder(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体"); // 字体
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setWrapText(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        return style;
    }

    public static void setBorder(HSSFSheet sheet, HSSFWorkbook workbook, int border, int firstRow, int lastRow, int firstCol, int lastCol) {
        // 起始行, 终止行, 起始列, 终止列
        CellRangeAddress cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        // 下边框
        RegionUtil.setBorderBottom(border, cra, sheet, workbook);
        // 左边框
        RegionUtil.setBorderLeft(border, cra, sheet, workbook);
        // 有边框
        RegionUtil.setBorderRight(border, cra, sheet, workbook);
        // 上边框
        RegionUtil.setBorderTop(border, cra, sheet, workbook);
    }

    public static void exportExcelBigData(String fileName, HttpServletResponse response, List<String> thead, List<?> data) throws Exception {
        String filePath = "D:\\111.xlsx";
        SXSSFWorkbook workbook = new SXSSFWorkbook(); // 申明一个工作簿
        Sheet sheet = workbook.createSheet(); // 生成一个表格
        sheet.setHorizontallyCenter(false);

        Font fonts = workbook.createFont();
        fonts.setFontHeightInPoints((short) 12); // 字体高度
        fonts.setFontName("宋体"); // 字体

        CellStyle cellStyle = cellStyleSXSSF(workbook, fonts);
        CellStyle StyleTitle = titleStyleSXSSF(workbook);
        CellStyle StyleSearch = workbook.createCellStyle();
        StyleSearch.setFont(fonts);

        Row row = sheet.createRow(0);
        row.setHeightInPoints(55);// ==30*20设置高度

        ArrayList<String> headers = new ArrayList<String>();
        for (int i = 0; i < thead.size(); i++) {
            headers.add(thead.get(i));
            sheet.setColumnWidth(i, 15 * 200);
        }
        sheet.setMargin(HSSFSheet.LeftMargin, 0.544);
        sheet.setMargin(HSSFSheet.RightMargin, 0.394);

        Cell cell;
        int j = 0;
        cell = row.createCell(j++);
        cell.setCellStyle(StyleTitle);
        cell.setCellValue(fileName);

        row = sheet.createRow(j++);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.size() - 1));
        cell = row.createCell(0);
        cell.setCellStyle(StyleSearch);
        cell.setCellValue("查询时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 表头
        row = sheet.createRow(j++);
        for (int i = 0; i < headers.size(); i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers.get(i).split("#")[0]);
            cell.setCellValue(text); // 格式化单元格内容
        }

        int count = 0;
        if (data.size() < 60000) {
            workbook.setSheetName(count, "第0~" + data.size() + "条");
        } else {
            workbook.setSheetName(count, "第0~60000条");
        }
        Integer start = 0;
        Integer end = 60000;
        if (data.size() > 0) {
            for (int x = 0; x < data.size(); x++) {
                Map<String, Object> map = (Map<String, Object>) data.get(x);
                if (x != 0 && x % 60000 == 0) {
                    start = end + 1;
                    if ((end + 60000) > data.size()) {
                        end = data.size();
                    } else {
                        end = end + 60000;
                    }
                    sheet = creatSheetSXSSF(thead, workbook, fonts);
                    count++;
                    workbook.setSheetName(count, "第" + start + "~" + end + "条");
                    j = 1;
                }
                row = sheet.createRow(j++);
                int i = 0;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    cell = row.createCell(i++);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(new HSSFRichTextString(StringUtil.getString(entry.getValue())));
                }
            }
        }
        OutputStream out;
        try {
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("gbk"), StandardCharsets.ISO_8859_1)
                    + ".xls");

            out = response.getOutputStream();
            workbook.write(out);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("导出结束");
        }
    }

    public static CellStyle titleStyleSXSSF(SXSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 18); // 字体高度
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字体增粗
        font.setFontName("宋体"); // 字体
        CellStyle StyleTitle = workbook.createCellStyle();
        StyleTitle.setFont(font);
        StyleTitle.setWrapText(true);
        StyleTitle.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
        StyleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
        return StyleTitle;
    }

    public static CellStyle cellStyleSXSSF(SXSSFWorkbook workbook, Font font) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);// 居中 // 设置单元格类型(标题)
        return cellStyle;
    }

    public static Sheet creatSheetSXSSF(List<String> thead, SXSSFWorkbook workbook, Font font) {
        Sheet sheet = workbook.createSheet();
        sheet.setHorizontallyCenter(false);
        ArrayList<String> headers = new ArrayList<String>();
        for (int i = 0; i < thead.size(); i++) {
            headers.add(thead.get(i));
            sheet.setColumnWidth(i, 15 * 200);
        }
        sheet.setMargin(Sheet.LeftMargin, 0.544);
        sheet.setMargin(Sheet.RightMargin, 0.394);
        Cell cell;
        Row row = sheet.createRow(0);
        CellStyle cellStyle = cellStyleSXSSF(workbook, font);
        for (int i = 0; i < headers.size(); i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers.get(i).split("#")[0]);
            cell.setCellValue(text); // 格式化单元格内容
        }
        return sheet;
    }

    public static XSSFWorkbook getXSSFWorkbook(String filePath) {
        XSSFWorkbook workbook = null;
        BufferedOutputStream outputStream = null;
        try {
            File fileXlsxPath = new File(filePath);
            outputStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
            workbook = new XSSFWorkbook();
            workbook.createSheet("测试Sheet");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }
}
