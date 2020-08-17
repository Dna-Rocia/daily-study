package com.daily.javabsc.sort;

/**
 * @author Rocia
 * @description :排序算法
 * @CreateTime 2018-08-20-9:20
 */
public class SortSum {

    //选择排序
    public void selectSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int swap = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = swap;
        }
    }


    //插入排序
    public void insertSort1(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) { //i直接从1开始，第0个元素本身就是有序
            //寻找元素arr[i]合适插入的位置，
            // j > 0：最多查到j=1的情况，要是比0小就交换位置，要是比0大就放在本身的位置上
            // j = i: 考察的是当前元素所在的位置
            // j--:从后往前进行相邻比较
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                int swap = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = swap;
            }
        }
    }


    //插入排序（优化）
    public void insertSort2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) { //i直接从1开始，第0个元素本身就是有序
            int e = arr[i];
            int j; //用于保存元素e应该插入的位置
            for (j = i; j > 0 && arr[j - 1] > e; j--) arr[j] = arr[j - 1];
            arr[j] = e;
        }
    }


    //对arr[left...right]范围的数组进行插入排序  注意：都是闭区间
    public void insertSort3(int[] arr, int left, int right) {
        for (int i = left + 1; i < right; i++) { //i直接从1开始，第0个元素本身就是有序
            int e = arr[i];
            int j; //用于保存元素e应该插入的位置
            for (j = i; j > 0 && arr[j - 1] > e; j--) arr[j] = arr[j - 1];
            arr[j] = e;
        }
    }


    //冒泡排序 一(向后)
    public void bubbleSort1(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) { //外层循环控制排序趟数
            for (int j = 0; j < n - i - 1; j++) { //内层循环控制每一趟排序多少次
                if (arr[j] > arr[j + 1]) {
                    int swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                }
            }
        }
    }


    //冒泡排序 二 （向前）
    public void bubbleSort2(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) { //外层循环控制排序趟数
            for (int j = n - 2; j >= i; j--) {
                if (arr[j] > arr[j + 1]) {
                    int swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                }
            }
        }
    }


    //插入排序延伸出了shell sort 希尔排序
    public void shellSort(int[] arr) {
        if (arr == null) return;
        int n = arr.length;
        if (n <= 1) return;

        //增量
        int incrementNum = n / 2;
        while (incrementNum >= 1) {
            for (int i = 0; i < n; i++) {
                //进行插入排序
                for (int j = i; j < n - incrementNum; j = j + incrementNum) {
                    if (arr[j] > arr[j + incrementNum]) {
                        int temple = arr[j];
                        arr[j] = arr[j + incrementNum];
                        arr[j + incrementNum] = temple;
                    }
                }
            }
            //设置新的增量
            incrementNum = incrementNum / 2;
        }

    }


    public void mergeSort(int[] arr) {
        int n = arr.length;
        recursionImplMergeSort1(arr, 0, n - 1);
    }

    //递归使用归并排序，对arr[left...right]的范围进行排序 注意：闭区间(right= arr.length-1),如若右边是开区间：（right = arr.length）
    private void recursionImplMergeSort(int[] arr, int left, int right) {
        //当递归到底，left大于right 的时候，表示数组中只有一个元素，甚至是空
        if (left >= right) return;
        //找出中间点
        int mid = left + (right - left) / 2;//(left + right) / 2;

        recursionImplMergeSort(arr, left, mid);//对左侧部分归并排序
        recursionImplMergeSort(arr, mid + 1, right);//对右侧部分进行归并排序

        //将上边的两个归并好的数组进行合并处理
        mergeArr(arr, left, mid, right);


    }


    //将arr[left ... mid] 和 arr[mid+1 ... right]两部分进行归并  注意：两个都是闭区间
    private void mergeArr(int[] arr, int left, int middle, int right) {
        int[] aux = new int[right - left + 1];

        for (int i = left; i <= right; i++) {
            aux[i - left] = arr[i];
        }

        // 初始化，
        // i：指向左半部分的起始索引位置l；
        // j：指向右半部分起始索引位置mid+1；
        // k：（最终归并）
        //左右侧进行比较之后，把小的值放到最终归并数组中
        int i = left, j = middle + 1;
        for (int k = left; k <= right; k++) {
            //还要考虑下标（索引）是否越界的问题
            // 左边当前下标超过中间值，标明k值还没有遍历完全，那么，K值应从右侧取值arr[mid+1 ... right]
            if (i > middle) {
                arr[k] = aux[j - left];
                j++;
            } else
                //右边当前下标超过最右边的值，标明k值还没有遍历完全，那么，K值应从左侧取值arr[left ... mid]
                if (j > right) {
                    arr[k] = aux[i - left];
                    i++;
                } else
                    // 左半部分所指元素 < 右半部分所指元素
                    if (aux[i - left] < aux[j - left]) {
                        arr[k] = aux[i - left];
                        i++;
                    }
                    // 左半部分所指元素 >= 右半部分所指元素
                    else {
                        arr[k] = aux[j - left];
                        j++;
                    }
        }
    }

    //优化, 有问题
    //递归使用归并排序，对arr[left...right]的范围进行排序 注意：闭区间(right= arr.length-1),如若右边是开区间：（right = arr.length）
    private void recursionImplMergeSort1(int[] arr, int left, int right) {

        //当递归到一定程度的时候，调用插入排序
        if (right - left <= 5) {
            insertSort3(arr, left, right);
            return;
        }

        //找出中间点
        int mid = left + (right - left) / 2;//(left + right) / 2;

        recursionImplMergeSort1(arr, left, mid);//对左侧部分归并排序
        recursionImplMergeSort1(arr, mid + 1, right);//对右侧部分进行归并排序

        //若近乎为有序的数组
        if (arr[mid] > arr[mid + 1]) {
            //将上边的两个归并好的数组进行合并处理
            mergeArr(arr, left, mid, right);
        }

    }


    public static void main(String[] str) {
        int[] arr = {4, 1, 7, 6, 5, 3, 9, 8, 2, 10};
        SortSum selectionSort = new SortSum();

        selectionSort.mergeSort(arr);
//		selectionSort.shellSort(arr);
//		selectionSort.bubbleSort2(arr);
//		selectionSort.bubbleSort1(arr);
//		selectionSort.insertSort2(arr);
//		selectionSort.insertSort1(arr);
//		selectionSort.selectSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }


}
