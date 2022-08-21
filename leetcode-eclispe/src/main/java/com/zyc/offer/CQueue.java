package com.zyc.offer;

/**
 * 剑指offer 09.使用两个栈实现队列
 */
public class CQueue {
    private final Stack stack1;//入队栈
    private final Stack stack2;//出队栈

    public CQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }

    public void appendTail(int element){
        stack1.push(element);
    }

    public int deleteHead() {
        if (stack2.isEmpty()){
            if (stack1.isEmpty())
                return -1;//队空
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

     private static class Stack {
        private static final int INIT_SIZE = 16;
        private  int[] stk;
        private  int top = -1;
        private  int cap;

        public Stack() {
            stk = new int[INIT_SIZE];
            cap = INIT_SIZE;
        }

        private void resize() {
            int newCap = cap << 1;
            int[] ret = new int[newCap];
            System.arraycopy(stk, 0, ret, 0, stk.length);
            stk = ret;
            cap = newCap;
        }

        public void push(int e) {
            if (top == cap - 1) {
                resize();
            }
            stk[++top] = e;
        }

        public int pop() {
            if (isEmpty())
                return -1;
            return stk[top--];
        }

        public boolean isEmpty() {
            return top == -1;
        }

    }
}
