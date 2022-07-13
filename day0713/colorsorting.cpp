#include <iostream>

using namespace std;

/*75.颜色分类，0 1 2 分别代表三种颜色，O(1)的空间复杂度,最好一趟扫描算法*/

void Swap(int *x, int *y)
{
    int tmp = *x;
    *x = *y;
    *y = tmp;
}

void sortColors(int nums[], int numsSize)
{
    for (int i = 0; i < numsSize - 1; ++i)
    {
        // bool flag = false;
        for (int j = numsSize - 1; j > i; --j)
        {
            if (nums[j - 1] > nums[j])
            {
                Swap(&nums[j - 1], &nums[j]);
                // flag = true;
            }
            // if (flag == false)
            //     return;
        }
    }
}

//双指针法
void sortColors_p(int nums[], int numsSize)
{
    int p0 = 0, p1 = 0;//p0指向0的位置，p1指向1的位置
    for (int i = 0; i < numsSize; ++i)
    {
        if (nums[i] == 1)
        {
            swap(nums[i], nums[p1]);
            ++p1;
        }
        else if (nums[i] == 0)
        {
            swap(nums[i], nums[p0]);
            if (p0 < p1)
                swap(nums[i], nums[p1]);
            ++p0;
            ++p1;
        }
    }
}

int main()
{
    int nums[] = {2, 0, 2, 1, 1, 0};
    sortColors_p(nums, 6);
    for (int a : nums)
    {
        cout << a << " ";
    }
    return 0;
}