package com.daily.export;

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
public class CsvServiceImpl implements CsvService{

    private static final Logger logger = LoggerFactory.getLogger(CsvServiceImpl.class);

    @Override
    public void csv(String fileName, String[] TITLE, String[] THEAD,Integer count, HttpServletResponse response) throws Exception {
        BufferedWriter bw = getBufferedWriter(fileName,TITLE,response);
        Integer per = ExportConst.PER_NUM_2W;
        int maxPageNum = count/per +((count % per) == 0?0:2);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < maxPageNum; i++) {
//            ex.setPageNum(i * per);
//            ex.setOffset(per);
            List<LinkedHashMap<String, Object>> dataList =  this.finFreezeAndUnFreezeService.exportFinFreezeAndUnFreezeList(ex,dataEncryptList);
            str.setLength(0);
            for (Iterator c = dataList.iterator(); c.hasNext(); str.append("\n")){
                Map map = (Map)c.next();
                //分批写入
                for (int k = 0; k < THEAD.length; k++){
                    Object obj = StringUtils.isEmpty(map.get(THEAD[k]))?"":map.get(THEAD[k]);
                    if (k != THEAD.length - 1) {
                        str.append((new StringBuilder()).append("\"").append("\t"+obj).append("\",").toString());
                    }else {
                        str.append((new StringBuilder()).append("\"").append("\t"+obj).append("\"").toString());
                    }
                }
            }
            if (str.length() > 0){
                bw.write(str.toString());
            }
        }
        try {
            bw.newLine();

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            bw.flush();
            bw.close();
            System.out.println("导出结束");
        }
    }




    /**
     * 设置csv响应头部
     * @param fileName 文件名
     * @param response 响应
     */
    private void setCsvHeader(String fileName, HttpServletResponse response) {
        try {
            // 设置文件后缀
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = new String(fileName.getBytes("gbk"), "iso8859-1") + sdf.format(new Date()) + ".csv";
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Content-Disposition", "attachment;filename="+filename);
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 初始化 csv文件头
     * @param fileName 文件名称
     * @param columnName 列标题
     * @param response 响应
     */
    private  BufferedWriter getBufferedWriter(String fileName, String[] columnName, HttpServletResponse response) throws IOException {
        setCsvHeader(fileName,response);
        OutputStream fos = response.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(getTableColumnName(columnName,fileName));
        return bw;
    }


    /**
     * 处理表头标题部分
     * @param columnName 列名称
     * @param fileName 文件名称
     */
    private  String getTableColumnName(String[] columnName,String fileName){
        StringBuilder sb = new StringBuilder();
        sb.append(fileName).append("\n");
        for (int i = 0; i < columnName.length; i++)
            if (i != columnName.length - 1) {
                sb.append("\"").append(columnName[i]).append("\",");
            } else {
                sb.append("\"").append(columnName[i]).append("\"");
            }
        sb.append("\n");
        return sb.toString();
    }

}
