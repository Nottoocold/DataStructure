package com.zyc.sort;

import com.zyc.utils.MyArrays;

public class Sort {

    //给你一个整数数组 nums，请你将该数组升序排列。
    public static void sortArray(int[] nums) {
        MyArrays.QuickSort(nums);
        MyArrays.SelectSort(nums);
        MyArrays.InsertSort(nums);
        MyArrays.MergeSort(nums);
    }

}
