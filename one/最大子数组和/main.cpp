#include <stdio.h>

int max(int* nums, int numsSize)
{
    int i, max;
    max = nums[0];
    for (i = 1; i < numsSize; i++)
    {
        if (max < nums[i])
        {
            max = nums[i];
        }
    }
    return max;
}

//
int maxSubArray(int* nums, int numsSize) {//��̬�滮������˼����ǰһ�����ǷǸ���ʱ����͵�ǰ�����
    int i;
    for (i = 1; i < numsSize; i++)//��ʼΪnum[0]�����Ե�0�����ֽ�β��������ֻ��a[0]��
    {
        if (nums[i - 1] > 0)
        {
            nums[i] = nums[i - 1] + nums[i];
        }
    }
    return max(nums, numsSize);
}

int main()
{
    int arr[] = { -2, 1, -3, 2, 4, -5, 7, -5, 4 };
    int size = sizeof(arr) / sizeof(int);
    int ret = maxSubArray(arr, size);
}