package com.daily.javabsc.interview.loop;

/**
 * @author Rocia
 * @description : 二分查找
 * @CreateTime 2018-08-18-13:51
 */
public class BinarySearch {


    /**
     * 要求数组是有序的，随机访问
     *
     * @param arr
     * @param key
     * @return
     */
    public int binarySearch(int[] arr, int key) {

        int a = 0, b = arr.length; //[a,b) a <= b， 保证k可能在[a,b)
        while (a < b) {
            int m = (a + b) / 2;// a+(b -a)/2
            // a,b  半开半闭区间
            if (key < arr[m]) {
                b = m;
            } else if (key > arr[m]) {
                a = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }


    public static void main(String[] a) {
        BinarySearch binarySearch = new BinarySearch();

        System.out.println(binarySearch.binarySearch(new int[]{1, 2, 10, 15, 100}, 15));
        System.out.println(binarySearch.binarySearch(new int[]{1, 2, 10, 15, 100}, -1));
        System.out.println(binarySearch.binarySearch(new int[]{1, 2, 10, 15, 100}, 3));
        System.out.println(binarySearch.binarySearch(new int[]{1, 2, 10, 15, 100}, 101));
    }


}
