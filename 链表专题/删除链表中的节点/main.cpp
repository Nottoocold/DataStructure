/*
 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
*/

#include <stdio.h>
#include <stdlib.h>

 struct ListNode {
     int val;
    struct ListNode *next;
 };

void deleteNode(struct ListNode* node) {
    struct ListNode* pre = node;
    struct ListNode* nextp = pre->next;
    while (nextp->next != NULL)
    {
        pre->val = nextp->val;
        pre = nextp;
        nextp = nextp->next;
    }//循环结束时, pre 和 nextp 指针刚好分别指向 尾节点的前驱 和 尾节点, 只需在循环外再次交换值并把pre所指节点指针域设为null即可
    pre->val = nextp->val;
    pre->next = NULL;
}

int main()
{
    return 0;
}