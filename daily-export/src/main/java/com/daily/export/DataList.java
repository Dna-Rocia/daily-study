package com.daily.export;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/29
 */
public interface DataList{

    List<LinkedHashMap<String, Object>> getDataList(int per, int i );

}
