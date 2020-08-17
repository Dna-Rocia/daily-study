package com.daily.common.utils;

import java.util.zip.CRC32;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/15
 */
public class HashUtil {

    public static long crc32Code(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        return crc32.getValue();
    }
}
