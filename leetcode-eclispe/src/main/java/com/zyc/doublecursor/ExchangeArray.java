package com.zyc.doublecursor;

public class ExchangeArray {

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
     *
     * @param nums 数组长度0<=len<=50000
     * @return 不要求奇数和偶数的顺序 [1,2,3,4] -> [1,3,2,4] 或 [3,1,4,2] ...
     */
    public int[] exchange(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return null;
        int i, j;
        for (i = 0, j = 0; j < nums.length; ) {
            if ((nums[j] & 1) == 1) {
                if ((nums[i] & 1) == 0) {
                    swap(nums, i, j);
                    ++i;
                }
                ++j;
            } else {
                if (((nums[i]) & 1) == 1) {
                    i = j++;
                } else {
                    j++;
                }
            }
        }
        return nums;
    }

    public int[] exchange0(int[] nums) {
        int i = 0, j = nums.length - 1, tmp;
        while (i < j) {
            while (i < j && (nums[i] & 1) == 1) i++;
            while (i < j && (nums[j] & 1) == 0) j--;
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }

    private void swap(int[] arr, int i, int j) {
        if (arr[i] == arr[j]) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
