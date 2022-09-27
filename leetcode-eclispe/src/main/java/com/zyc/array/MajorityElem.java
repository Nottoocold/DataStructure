package com.zyc.array;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/*
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 */
public class MajorityElem {

    public int majorityElement(int[] nums) {
        int ret = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i])) {
                int p = map.get(nums[i]);
                p++;
                map.put(nums[i], p);
            } else {
                int d = map.getOrDefault(nums[i], 0);
                map.put(nums[i], d + 1);
            }
        }
        Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            int value = entry.getValue();
            if (value > nums.length / 2) {
                ret = entry.getKey();
                break;
            }
        }

        return ret;
    }

}
