package com.zyc.offer;

/**
 * @author zyc
 * @date 2022/10/2
 */
public class ArrayReversePairs {

    /**
     * 数组中的逆序对
     *
     * @param numbs
     * @return
     */
    public int reversePairs(int[] numbs) {
        origin = numbs;
        work = new int[numbs.length];
        return mergeSort(0, numbs.length - 1);
    }

    private int mergeSort(int l, int r) {
        if (l >= r)
            return 0;
        int mid = (l + r) / 2;
        int res = mergeSort(l, mid) + mergeSort(mid + 1, r);
        System.arraycopy(origin, l, work, l, r - l + 1);
        /* 归并，i ：左区间首元素，j ：右区间首元素 */
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i == mid + 1) // 左区间归并完了
                origin[k] = work[j++];
                /* 右区间归并完了或右区间值更大 */
            else if (j == r + 1 || work[i] <= work[j])
                origin[k] = work[i++];
            else {
                origin[k] = work[j++];
                res += mid - i + 1; // 统计逆序对
            }
        }
        return res;
    }

    private int[] origin;
    private int[] work;
}
