package com.daily.export.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/27
 */
public class CvsUtil2 {

    /**
     * io流导出
     * @author kpzc
     * @date 2018年12月29日 下午3:48:34
     * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据，字符串用逗号分隔
     * @return 返回导出是否成功 true成功 false失败
     */
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;

        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out, "GBK");//解决FileOutputStream中文乱码问题  解决MS office乱码问题
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSucess;
    }

    /**
     * 导入
     * @author kpzc
     * @date 2018年12月29日 下午3:48:11
     * @param file  csv文件(路径+文件)
     * @return 返回List<String>列表
     */
    public static List<String> importCsv(File file){
        List<String> dataList=new ArrayList<String>();
        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }

    /**
     * apache commons-csv导出
     * 注意jdk要在1.7及以上使用
     * map的数据个数要与header的个数相等 并且一一对应，可参照main方法
     * @author kpzc
     * @date 2019年1月4日 上午10:12:20
     * @param filePath 文件存储路径
     * @param list 数据列表
     * @param header 表头
     */
    public static void write(String filePath, List<LinkedHashMap<String, String>> list, String... header) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(header);
            CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
            //跟上面两行代码是一样的
            //CSVPrinter csvPrinter = CSVFormat.DEFAULT.withHeader(header).print(osw);
            for (Map<String, String> map : list) {
                csvPrinter.printRecord(map.values());
            }
            csvPrinter.flush();
            csvPrinter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //io流导出
//        File file=new File("E:/aa.csv");
//        List<String> dataList=new ArrayList<String>();
//        dataList.add("1,2,3,'/N',4");
//        dataList.add("1,2,3,'/N',4");
//        exportCsv(file, dataList);
//
//        // apache commons-csv导出
//        String filePath = "E://aaa.csv";
//        String header1 = "姓名";
//        String header2 = "性别";
//        String header3 = "编号";
//        String header4 = "描述";
//
//        List<LinkedHashMap<String, String>> recordList = new ArrayList<LinkedHashMap<String, String>>();
//        for (int i = 0; i < 5; i++) {
//            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
//            map.put("name", "zhangsan");
//            map.put("sex", "男");
//            map.put("code", "001");
//            map.put("aa", "aaa");
//            recordList.add(map);
//        }
//        write(filePath, recordList,header1,header2,header3,header4);
        ////\r表示回车(carriage-return)
        ////\n表示换行 (new-line)
        String a = System.getProperty("line.separator");
        String b = System.getProperty("carriage.return");
        String c = System.getProperty("new.line");
        System.out.println(a);
        System.out.println(b + c);
    }


    public static boolean writeFileContent(String filepath,List<LinkedHashMap> list) throws IOException{
        Boolean bool = false;
        String temp  = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            int i;
            //文件原有内容
            for(i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            for (int q = 0; q < list.size(); q++) {

//                buffer.append(list.get(q).getId()+",");
//                buffer.append(list.get(q).getTime_point()+",");
//                buffer.append(list.get(q).getAqi()+",");
//                buffer.append(list.get(q).getArea()+",");
//                buffer.append(list.get(q).getPosition_name()+",");
//                buffer.append(list.get(q).getStation_code()+",");
//                buffer.append(list.get(q).getSO2()+",");
//                buffer.append(list.get(q).getNO2()+",");
//                buffer.append(list.get(q).getCO()+",");
//                buffer.append(list.get(q).getO3()+",");
//                buffer.append(list.get(q).getPM2_5()+",");
//                buffer.append(list.get(q).getPM10()+",");
//                buffer.append(list.get(q).getPrimary_pollutant().replaceAll(",", "，")+",");  //逗号在csv文件中相当于tab键，每个字段后面加上逗号是分割单元格replaceAll(",", "，")是一个单元格中的数据存在逗号，将英文逗号替换成中文逗号，csv不解析，会直接按字符串处理，这样就不会换单元格了，文本中也会显示逗号，不影响正常的阅读。
//                buffer.append(list.get(q).getQuality()+" \r\n");
            }
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

}
