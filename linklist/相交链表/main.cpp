/*
使用双指针的方法，可以将空间复杂度降至 O(1)

只有当链表 headA 和 headB 都不为空时，两个链表才可能相交。
因此首先判断链表 headA 和 headB 是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，返回null。

当链表 headA 和 headB 都不为空时，创建两个指针 pA 和 pB，初始时分别指向两个链表的头节点 headA 和 headB，
然后将两个指针依次遍历两个链表的每个节点。具体做法如下：

每步操作需要同时更新指针 pA 和 pB。

如果指针 pA 不为空，则将指针 pA 移到下一个节点；如果指针pB 不为空，则将指针 pB 移到下一个节点。

如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点；如果指针 pB 为空，则将指针 pB 移到链表 headA 的头节点。

当指针 pA 和 pB 指向同一个节点或者都为空时，返回它们指向的节点或者 null。
*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
      int val;
      struct ListNode *next;
 };

struct ListNode* getIntersectionNode(struct ListNode* headA, struct ListNode* headB) {
    if (!headA || !headB)
    {
        return NULL;
    }
    struct ListNode* pa = headA;
    struct ListNode* pb = headB;
    while (pa != pb)
    {
        if (!pa)
        {
            pa = headB;
        }
        else
        {
            pa = pa->next;
        }
        if (!pb)
        {
            pb = headA;
        }
        else
        {
            pb = pb->next;
        }
    }
    return pa;
}

int main()
{
    return 0;
}