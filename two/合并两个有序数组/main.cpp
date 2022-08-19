#include <stdio.h>
#include <stdlib.h>
//Ĭ������1���ȴ�������2
//m��ʾ����1����ռ���ȣ�nums1size��ʾ����1Ԫ�ظ���,n��ʾ����2�ĳ���
void merge(int* nums1,int m,int nums1size, int* nums2,int n) {
    int len1 = m - 1;
    int len2 = n - 1;
    int len = nums1size - 1;
    while (len1 >= 0 && len2 >= 0) {
        nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
    }
    while (len>=0)
    {
        if (len2 >= 0) {
            nums1[len] = nums2[len2];
            len--;
            len2--;
        }
        else
        {
            nums1[len] = nums1[len1];
            len--;
            len1--;
        }
    }
}

int main()
{
    int num1[7] = { 1,2,3,6,0,0,0 };
    int num2[3] = { 1,4,5 };
    merge(num1, 4,7, num2, 3);
    return 0;
}