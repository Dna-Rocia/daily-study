package com.daily.common.utils;

public final class ArraysUtils {

    public ArraysUtils() {
        throw new RuntimeException("ArraysUtils stub!");
    }

    public static <T> boolean isEmpty(final T[] array) {
        return array == null || array.length == 0;
    }
}