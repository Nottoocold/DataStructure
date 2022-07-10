#include <iostream>
#include <cstring>
using namespace std;

void rotate(int *nums, int numsSize, int k)
{
    if (k < 0 || numsSize == 0 || numsSize == 1)
        return;
    int *ret = (int *)calloc(numsSize, sizeof(nums[0]));
    for (int i = 0; i < numsSize; ++i)
    {
        ret[(i + k) % numsSize] = nums[i];
    }
    memcpy(nums, ret, sizeof(nums[0]) * numsSize);
}

int main()
{
    int a[] = {-1, -100, 3, 99};
    rotate(a, 4, 2);
    for (int i : a)
    {
        cout << i << " ";
    }
    return 0;
}