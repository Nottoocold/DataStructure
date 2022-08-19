/*
我们定义两个指针，一快一满。慢指针每次只移动一步，而快指针每次移动两步。
初始时，慢指针在位置 head，而快指针在位置 head.next。这样一来，如果在移动的过程中，快指针反过来追上慢指针，
就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为环形链表。
*/

#include <stdio.h>
#include <stdlib.h>

struct ListNode {
     int val;
     struct ListNode* next;
 };

bool hasCycle(struct ListNode* head) {
    if (head == NULL || head->next == NULL)
    {
        return false;
    }
    struct ListNode* slow = head;
    struct ListNode* fast = head->next;
    while (slow != fast)
    {
        if (fast == NULL || fast->next == NULL)
        {
            return false;
        }
        slow = slow->next;
        fast = fast->next->next;
    }
    return true;
}

int main()
{
    return 0;
}