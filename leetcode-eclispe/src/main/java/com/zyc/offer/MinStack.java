package com.zyc.offer;

/**
 * 剑指offer 30.包含min函数的栈
 */
public class MinStack {
    private int[] minStack;
    private int top ;
    private int cap ;

    public MinStack(int size){
        minStack = new int[size];
        top = -1;
        cap = size;
    }

    public int getTop() {
        return minStack[top - 1];
    }

    public int getMin(){
        return minStack[top];
    }

    public void pop(){
        top -= 2;
    }

    public void push(int e){
        if (top == -1){
            minStack[++top] = e;
            minStack[++top] = e;
            return;
        }
        if (top == cap - 1)
            resize();
        int t = minStack[top];
        minStack[++top] = e;
        minStack[++top] = Math.min(t, e);
    }

    private void resize(){
        int newCap = cap << 1;
        int[] m = new int[newCap];
        System.arraycopy(minStack, 0, m, 0, minStack.length);
        minStack = m;
        cap = newCap;
    }
}
