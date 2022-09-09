package com.zyc.offer.order;

public class StrMinNumber {

    /**
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     * @param nums 非负整数数组
     * @return 所有拼接可能中最小的一个数, 以字符串形式返回
     */
    public String minNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        sortStr(strings, 0, strings.length - 1);
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
        }
        return builder.toString();
    }

    // 快排 排序规则: s1 + s2 > s2 + s1 -> 说明s1拼接在前面更大
    private void sortStr(String[] ss, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left, j = right;
        String w = "";
        while (i < j) {
            // 第一个ss[j]放在前 小的
            while ((ss[j] + ss[left]).compareTo(ss[left] + ss[j]) >= 0 && i < j) j--;
            // 第一个ss[i]放在前 大的
            while ((ss[i] + ss[left]).compareTo(ss[left] + ss[i]) <= 0 && i < j) i++;
            // 交换 ss[i] ss[j]
            w = ss[i];
            ss[i] = ss[j];
            ss[j] = w;
        }
        // 枢轴元素ss[left] 放入最终位置
        ss[i] = ss[left];
        ss[left] = w;
        sortStr(ss, left, i - 1);
        sortStr(ss, i + 1, right);
    }
}
