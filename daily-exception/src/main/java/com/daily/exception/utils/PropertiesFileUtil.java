package com.daily.exception.utils;



import com.daily.exception.constant.CustomExceptionConst;
import com.daily.exception.handle.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;

/**
 * 读取配置信息
 */
public class PropertiesFileUtil {
    @Autowired
    private ApplicationContext ctx;
    // 当打开多个资源文件时，缓存资源文件
    private static HashMap<String, PropertiesFileUtil> configMap = new HashMap<String, PropertiesFileUtil>();
    // 打开文件时间，判断超时使用
    private Date loadTime = null;
    // 资源文件
    private ResourceBundle resourceBundle = null;
    // 缓存时间
    private static final Integer TIME_OUT = 60 * 1000;
    //定义的配置文件名
    private static String excFilename = "";


    static {
        try {
            readApplicationProperties();
        } catch (IOException e) {
            throw new CustomException(CustomExceptionConst.ERROR_IO);
        }
    }

    // 私有构造方法，创建单例
    private PropertiesFileUtil() {
        this.loadTime = new Date();
        try {
            this.resourceBundle = ResourceBundle.getBundle(excFilename);
        } catch (MissingResourceException mre) {
            String msg = CustomExceptionConst.ERROR_605.message;
            msg = MessageFormat.format(msg, excFilename);
            CustomExceptionConst.ERROR_605.setMessage(msg);
            throw new CustomException(CustomExceptionConst.ERROR_605);
        }
    }

//    public static synchronized PropertiesFileUtil getInstance() {
//        return getInstance(NAME);
//    }

    /**
     * 读取 application文件
     *
     * @throws IOException
     */
    private static void readApplicationProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = null;
        inputStream = Object.class.getResourceAsStream(SpringContextUtil.getApplicationConfigurationPath());
        try {
            properties.load(inputStream);
            excFilename = properties.get("custom.filename").toString().trim();
        } catch (IOException e) {
            throw new CustomException(CustomExceptionConst.ERROR_IO);
        } finally {
            inputStream.close();
        }
    }


    public static synchronized PropertiesFileUtil getInstance() {
        PropertiesFileUtil conf = configMap.get(excFilename);
        if (null == conf) {
            conf = new PropertiesFileUtil();
            configMap.put(excFilename, conf);
        }
        // 判断是否打开的资源文件是否超时1分钟
        if ((System.currentTimeMillis() - conf.getLoadTime().getTime()) > TIME_OUT) {
            conf = new PropertiesFileUtil();
            configMap.put(excFilename, conf);
        }

        return conf;
    }

    // 根据key读取value
    public String get(String key, String charsetName) {
        try {
            String val = resourceBundle.getString(key).trim();
            if (val.length() == 0) throw new CustomException(CustomExceptionConst.ERROR_600);
            if (charsetName != null && charsetName != "") {
                return resetEncoding(val, charsetName);
            }
            return val;
        } catch (MissingResourceException e) {
            return "";
        }
    }


    // 根据key读取value(整形)
    public Integer getInt(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Integer.parseInt(value);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    // 根据key读取value(布尔)
    public boolean getBool(String key) {
        try {
            String value = resourceBundle.getString(key);
            if ("true".equals(value)) {
                return true;
            }
            return false;
        } catch (MissingResourceException e) {
            return false;
        }
    }

    public Date getLoadTime() {
        return loadTime;
    }


    /**
     * 获取自定义properties文件
     */
    public static String readPropertiesFile(String key, String charsetName) throws IOException {
        Properties properties = new Properties();
        String path = "/" + excFilename + ".properties";
        InputStream inputStream = null;
        inputStream = Object.class.getResourceAsStream(path);

        if (inputStream == null) {
            String msg = CustomExceptionConst.ERROR_605.message;
            msg = MessageFormat.format(msg, excFilename);
            CustomExceptionConst.ERROR_605.setMessage(msg);
            throw new CustomException(CustomExceptionConst.ERROR_605);
        }

        try {
            properties.load(inputStream);
            Object obj = properties.get(key);
            if (obj == null) throw new CustomException(CustomExceptionConst.ERROR_600);
            String val = obj.toString().trim();
            if (charsetName != null && charsetName != "") {
                return resetEncoding(val, charsetName);
            }
            return val;
        } catch (IOException e) {
            throw new CustomException(CustomExceptionConst.ERROR_IO);
        } finally {
            inputStream.close();
        }
    }

    private static String resetEncoding(String valueStr, String charsetName) throws CustomException {
        Charset charset = Charset.forName(charsetName);
        System.out.println("直接获取到的:" + valueStr);
        byte[] value8859 = valueStr.getBytes();
        valueStr = new String(value8859, charset);
        System.out.println(charset + ": " + valueStr);
        return valueStr;
    }


    /**
     * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
     *
     * @param key   键名
     * @param value 键值
     */
    public static void writePropertiesFile(String filePath, String key, String value) throws IOException {
        Properties properties = new Properties();
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        properties.load(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        // 必须先用map将所有的内容先保存,不然一更新,原来的内容都没了
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object object : keySet) {
            String keytmp = (String) object;
            String valuetmp = (String) properties.getProperty(keytmp);
            System.out.println(keytmp + "=" + valuetmp);
            map.put(keytmp, valuetmp);
        }
        map.put(key, value);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            properties.setProperty(entry.getKey(), entry.getValue());
        }
        FileOutputStream fos = new FileOutputStream(file);
        properties.store(new OutputStreamWriter(fos), "update");
        fos.close();
        fis.close();
    }

}
