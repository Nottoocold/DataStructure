/*
由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，就可以删除重复的元素。

使用两个指针分别指示当前元素和当前元素的下个元素，只要两个元素相同就删除后一个，然后后一个指针向后移动
否则两个指针都向后移动一位，直到有一个指针为空为止
*/

#include <stdio.h>
#include <stdlib.h>

 struct ListNode {
      int val;
      struct ListNode *next;
  };

struct ListNode* deleteDuplicates(struct ListNode* head) {
    if (!head || !head->next)
    {
        return head;
    }
    struct ListNode* p1, * p2;
    p1 = head;
    p2 = head->next;
    while (p1 && p2)
    {
        if (p1->val == p2->val)
        {
            p1->next = p2->next;
            p2 = p2->next;
        }
        else
        {
            p1 = p1->next;
            p2 = p2->next;
        }
    }
    return head;
}

int main()
{
    return 0;
}