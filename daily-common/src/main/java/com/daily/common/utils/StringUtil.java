package com.daily.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/26
 */
public class StringUtil {

    public static String getString(Object o) {
        if (o == null || StringUtils.isEmpty(o.toString()) || StringUtils.isBlank(o.toString())) {
            return null;
        } else {
            return o.toString();
        }
    }
}
