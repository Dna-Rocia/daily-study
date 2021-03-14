package com.daily.export.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/29
 */
public class ExportCSVUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExportCSVUtil.class);

    //CSV文件分隔符
    private final static String NEW_LINE_SEPARATOR = "\n";
    /**
     * CSV文件列分隔符
     */
    private static final String CSV_COLUMN_SEPARATOR = ",";
    /**
     * CSV文件列分隔符
     */
    private static final String CSV_RN = "\r\n";
    /**
     * CSV文件列值的前边附加的符号
     **/
    private static final String CSV_T = "\t";
    /**
     * CSV文件列值的符号
     **/
    private static final String CSV_COLUMN = "\"";

    /**
     * 一次性查询后再一次性写入
     *
     * @param fileName    文件名称： 无需加时间戳
     * @param columnName  列标题
     * @param theadColumn 列标题属性
     * @param dataList    数据 LinkedHashMap<String,Object> 小于20w
     * @param response    响应
     */
    public void exportCSV(String fileName, String[] columnName, String[] theadColumn, List<LinkedHashMap<String, Object>> dataList, HttpServletResponse response) {
        setCsvHeader(fileName, response);
        try {
            java.io.OutputStream fos = response.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(writer);
            StringBuilder str = new StringBuilder();
            bw.write(getTableColumnName(columnName, fileName));
            String data = buildDataForCSV(theadColumn, dataList);
            bw.write(str.append(data).toString());
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            System.out.println("导出结束");
        }
    }

    /**
     * 初始化 csv文件头
     *
     * @param fileName   文件名称
     * @param columnName 列标题
     * @param response   响应
     */
    public BufferedWriter getBufferedWriter(String fileName, String[] columnName, HttpServletResponse response) throws IOException {
        setCsvHeader(fileName, response);
        OutputStream fos = response.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(getTableColumnName(columnName, fileName));
        return bw;
    }

    /**
     * 对文件内容批量追加
     *
     * @param dataList 数据
     * @param str      追加的StringBuilder
     * @param THEAD    表头
     * @param bw       写入
     * @throws IOException
     */
    public void appendStr(List<LinkedHashMap<String, Object>> dataList, StringBuilder str, String[] THEAD, BufferedWriter bw) throws IOException {
        str.setLength(0);
        for (Iterator c = dataList.iterator(); c.hasNext(); str.append(NEW_LINE_SEPARATOR)) {
            Map map = (Map) c.next();
            //分批写入
            for (int k = 0; k < THEAD.length; k++) {
                Object obj = StringUtils.isEmpty(map.get(THEAD[k])) ? "" : map.get(THEAD[k]);
                if (k != THEAD.length - 1) {
                    str.append(CSV_COLUMN).append(CSV_T).append(obj).append(CSV_COLUMN).append(CSV_COLUMN_SEPARATOR);
                } else {
                    str.append(CSV_COLUMN).append(CSV_T).append(obj).append(CSV_COLUMN);
                }
            }
        }
        if (str.length() > 0) {
            bw.write(str.toString());
        }
    }

    /**
     * 设置csv响应头部
     *
     * @param fileName 文件名
     * @param response 响应
     */
    public void setCsvHeader(String fileName, HttpServletResponse response) {
        try {
            // 设置文件后缀
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = new String(fileName.getBytes("gbk"), "iso8859-1") + sdf.format(new Date()) + ".csv";
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理表头标题部分
     *
     * @param columnName 列名称
     * @param fileName   文件名称
     */
    public String getTableColumnName(String[] columnName, String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append(fileName).append(NEW_LINE_SEPARATOR);
        for (int i = 0; i < columnName.length; i++)
            if (i != columnName.length - 1) {
                sb.append(CSV_COLUMN).append(columnName[i]).append(CSV_COLUMN).append(CSV_COLUMN_SEPARATOR);
            } else {
                sb.append(CSV_COLUMN).append(columnName[i]).append(CSV_COLUMN);
            }
        sb.append(NEW_LINE_SEPARATOR);
        return sb.toString();
    }

    /**
     * 数据一次性转csv格式
     *
     * @param theadColumn 列数据
     * @param dataList    查询出的数据
     */
    private String buildDataForCSV(String[] theadColumn, List dataList) {
        StringBuilder strb = new StringBuilder();
        int j = 0;
        for (Iterator i$ = dataList.iterator(); i$.hasNext(); strb.append(NEW_LINE_SEPARATOR), j++) {
            Map map = (Map) i$.next();
            for (int i = 0; i < theadColumn.length; i++) {
                Object obj = StringUtils.isEmpty(map.get(theadColumn[i])) ? "" : map.get(theadColumn[i]);
                if (i != theadColumn.length - 1) {
                    strb.append(CSV_COLUMN).append(CSV_T).append(obj).append(CSV_COLUMN).append(CSV_COLUMN_SEPARATOR);
                } else {
                    strb.append(CSV_COLUMN).append(CSV_T).append(obj).append(CSV_COLUMN);
                }
            }
        }
        return strb.toString();
    }


//    调用例子
//    public SwaggerResponsModel<Void> exportBalances(SwaggerInputModel<FinBalanceInput> inputModel, HttpServletResponse response) throws Exception {
//        SwaggerResponsModel<Void> responseModel = new SwaggerResponsModel<>();
//        FinBalanceInput input = inputModel.getData();
//        FinBalanceVo balanceVo = this.finBalanceService.getFinBalanceExtListCount(input);
//        Integer count = balanceVo.getCount();
//        FinBalanceEx ex = balanceVo.getFinBalanceEx();
//        List<DataEncrypt> dataEncryptList = balanceVo.getDataEncryptList();
//        if (count > 0) {
//            Integer per = ExportConst.PER_NUM_2W;
//            //写入
//            BufferedWriter bw = getBufferedWriter("会员卡余额",ExportConst.TITLE_BALANCE,response);
//            try{
//                int maxPageNum = count/per +((count % per) == 0?0:2);
//                StringBuilder str = new StringBuilder();
//                for (int i = 0; i < maxPageNum; i++) {
//                    ex.setPageNum(i * per);
//                    ex.setOffset(per);
//                    List<LinkedHashMap<String, Object>> dataList = this.finBalanceService.exportFinBalanceExtList(ex,dataEncryptList);
//                    //分批次追加数据
//                    appendStr(dataList,str,ExportConst.THEAD_BALANCE,bw);
//                }
//            } catch (Exception e) {
//                responseModel.setMessage("导出余额EXCEL成功");
//                responseModel.setErrorCode(0);
//                return responseModel;
//            }finally {
//                bw.flush();
//                bw.close();
//                System.out.println("导出结束");
//            }
//            //CsvUtil.exportBigCSV("会员卡余额",ExportConst.TITLE_BALANCE,ExportConst.THEAD_BALANCE,dataList,response);
//        }else {
//            responseModel.setMessage("没有可以导出的数据");
//            responseModel.setErrorCode(0);
//            return responseModel;
//        }
//        responseModel.setMessage("导出余额EXCEL成功");
//        return responseModel;
//    }


}
