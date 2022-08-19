#include <stdio.h>
#include <stdlib.h>
//哈希查找，每遍历一个元素，就比较哈希表中是否有target - x，若有则返回下标，否则将当前元素和下标插入哈希表
 
int* twoSum(int* nums, int numsSize, int target) {
	int ret[2] = { -1,-1 };
	for (int i = 0; i < numsSize-1; i++)
	{
		for (int j = i + 1; j < numsSize; j++)
		{
			if (nums[j]==target-nums[i])
			{
				ret[0] = i;
				ret[1] = j;
			}
		}
	}
	return ret;

}


int main() {
	int nums[] = { 2, 7, 11, 15 };
	int numsize = sizeof(nums) / sizeof(int);
	int target = 9;
	twoSum(nums, numsize, target);
	return 0;
}