package com.daily.common.utils;

import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/2
 */
public class EmptyUtil {

    private EmptyUtil() {
        throw new UnsupportedOperationException("you can't instantiate me ...");
    }

    /**
     * 判断 Object 为空
     */
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     * 判断Object 不为空
     */
    public static boolean isNotEmpty(Object obj) {
        return obj != null;
    }

    /**
     * 判断String 为空
     */
    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str.trim()) || str.trim().length() == 0);
    }

    /**
     * 判断String 不为空
     */
    public static boolean isNotEmpty(String str) {
        return (str != null && !"".equals(str) && str.trim().length() > 0);
    }

    /**
     * 判断Integer 数字类型为空
     */
    public static boolean isEmpty(Integer num) {
        return num == null;
    }

    /**
     * 判断 Integer 数字类型不为空
     */
    public static boolean isNotEmpty(Integer num) {
        return num != null;
    }

    /**
     * 判断 list集合为空：list == null 或 list.size ==0  返回 true
     */
    @SuppressWarnings("unchecked")
    public static boolean isEmpty(List list) {
        return (list == null || list.size() == 0);
    }

    /**
     * 判断  list集合不为空：list != null 或 list.size >0  返回 true
     */
    @SuppressWarnings("unchecked")
    public static boolean isNotEmpty(List list) {
        return (list != null && list.size() > 0);
    }

    /**
     * 对象数组为空
     */
    public static boolean isEmpty(Object[] obj) {
        return ((obj == null) || (obj.length == 0));
    }

    /**
     * 对象数组不为空
     */
    public static boolean isNotEmpty(Object[] obj) {
        return ((obj != null) && (obj.length > 0));
    }

    /**
     * collection集合为空
     */
    public static boolean isEmpty(Collection<?> conn) {
        return ((conn == null) || (conn.size() <= 0));
    }

    /**
     * collection集合不为空
     */
    public static boolean isNotEmpty(Collection<?> conn) {
        return ((conn != null) && (conn.size() > 0));
    }

    /**
     * byte数组为空
     */
    public static boolean isEmpty(byte[] bys) {
        return ((bys == null) || (bys.length == 0));
    }

    /**
     * byte数组不为空
     */
    public static boolean isNotEmpty(byte[] bys) {
        return ((bys != null) && (bys.length > 0));
    }

    /**
     * long类型为空
     */
    public static boolean isEmpty(Long l) {
        return ((l == null) || (l == 0L));
    }

    /**
     * long类型不为空
     */
    public static boolean isNotEmpty(Long l) {
        return ((l != null) && (l > 0L));
    }

}
