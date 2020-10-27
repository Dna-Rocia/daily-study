package com.daily.export;


import com.daily.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/26
 */
public class ExportUtil {

    private static final Logger logger = LoggerFactory.getLogger("ExportUtil");

    public static void exportCSV(String columnName[], List dataList, HttpServletRequest request, HttpServletResponse response) {
        String columns = getTableColumnName(columnName);
        String data = buildDataForCSV(columnName, dataList);
        String fileName = (String)request.getAttribute("fileName");
        if (fileName == null || "".equals(fileName))
            fileName = DateUtil.convertDateToStr(new Date(), "yyyy-MM-dd");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                (new StringBuilder()).append("attachment; filename=\"").append(fileName).append(".csv").append("\" ").toString());
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");

        try
        {
            java.io.OutputStream fos = response.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(fos, "gbk");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write((new StringBuilder()).append(columns).append(data).toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
    }

    public static void exportCSV(String headName, String dataStr, HttpServletRequest request, HttpServletResponse response) {
        String fileName = (String)request.getAttribute("fileName");
        if (fileName == null || "".equals(fileName))
            fileName = DateUtil.convertDateToStr(new Date(), "yyyy-MM-dd");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                (new StringBuilder()).append("attachment; filename=\"").append(fileName).append(".csv").append("\" ").toString());
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        try
        {
            java.io.OutputStream fos = response.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(fos, "gbk");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write((new StringBuilder()).append(headName).append(dataStr).toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
    }

    public static void saveDataCSV(String columnName[], List dataList, String csvFileName) {
        String columns = getTableColumnName(columnName);
        String data = buildDataForCSV(columnName, dataList);
        csvFileName = csvFileName.replace('/', File.separatorChar);
        File file = new File(csvFileName);
        try
        {
            FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(stream, "gbk");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write((new StringBuilder()).append(columns).append(data).toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
    }

    public static String getTableColumnName(String columnName[])
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columnName.length; i++)
            if (i != columnName.length - 1) {
                sb.append((new StringBuilder()).append("\"").append(columnName[i]).append("\",").toString());
            } else {
                sb.append((new StringBuilder()).append("\"").append(columnName[i]).append("\"").toString());
            }
        sb.append("\n");
        return sb.toString();
    }


    private static String buildDataForCSV(String[] columnName, List dataList) {
        StringBuilder strb = new StringBuilder();
        for (Iterator i$ = dataList.iterator(); i$.hasNext(); strb.append("\n"))
        {
            Map map = (Map)i$.next();
            for (int i = 0; i < columnName.length; i++)
                if (i != columnName.length - 1) {
                    strb.append((new StringBuilder()).append("\"").append(map.get(columnName[i])).append("\",").toString());
                }
                else {
                    strb.append((new StringBuilder()).append("\"").append(map.get(columnName[i])).append("\"").toString());
                }
        }

        return strb.toString();
    }


}
