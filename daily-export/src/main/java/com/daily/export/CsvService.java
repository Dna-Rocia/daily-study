package com.daily.export;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/29
 */
public interface CsvService {

    void csv(String fileName, String[] TITLE, String[] THEAD,Integer count, HttpServletResponse response) throws Exception;


}
