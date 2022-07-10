package com.zyc.day0626;

import java.util.HashMap;
import java.util.Map;

/**
 * 求数组中两个和为target的索引
 * @author modua
 *
 */
public class Solution {
	public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])) {
				return new int[]{i,map.get(target - nums[i])};
			}
            map.put(nums[i], i);
        }
		return new int[0];
    }
	
	public static void main(String[] args) {
		int[] nums = {2,7,15,11,12,2};
		int[] re = Solution.twoSum(nums, 4);//0,5
		System.out.print("[");
		for(int i : re){
			if (i != re[re.length-1]) {
				System.out.print(i+",");				
			}else {
				System.out.print(i+"]");
			}
		}
	}
}
