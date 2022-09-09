package com.zyc.offer.order;

public class MyPriorityQueueMax {
    private int[] arr;
    private int size ;

    private MyPriorityQueueMax(){

    }

    /**
     * 构造一个带初始容量的大根堆,默认容量是10
     * @param cap 容量
     */
    public MyPriorityQueueMax(int cap) {
        if (cap <= 0 || cap > 100) {
            arr = new int[10];
        }else {
            arr = new int[cap];
        }
        size = 0;
    }

    public void add(int val) {
        int p = size;
        while (p > 0) {
            int parent = (p - 1) >> 1;
            int e = arr[parent];
            if (val <= e) {
                break;
            }
            arr[p] = e;
            p = parent;
        }
        arr[p] = val;
        size++;
    }

    public int poll() {
        int result = arr[0];
        int half = (--size) >> 1;
        int k = 0, down = arr[size];
        // 从根节点开始向下
        while (k < half) {
            // 左孩子
            int child = (k << 1) + 1;
            int left = arr[child];
            // 右孩子
            int right = child + 1;
            // 左孩子和有孩子哪一个优先级更大
            if (right < size && left < arr[right]) {
                // 右孩子优先级大
                left = arr[child = right];
            }
            if (left < down)
                break;
            arr[k] = left;
            k = child;
        }
        arr[k] = down;
        return result;
    }

    public int peek() {
        return arr[0];
    }
}
