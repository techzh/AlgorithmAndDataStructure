package org.technicalad.ads.queue;

/**
 * @Description: 数组实现队列
 * @author: zhangyafeng1
 * @Date 2019-08-16 12:54:52
 */
public class ArrayQueue {

    /**
     * 数组容量
     */
    private int maxSize;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 存放数据的数组
     */
    private int[] arr;

    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        // 指向队列头，也就是队列第一个元素前面的位置
        front = -1;
        // 指向队列尾，也就是队列最后一个元素
        rear = -1;
    }

    /**
     * 判断队列满
     * @return
     */
    public boolean isFull() {
        return rear == maxSize -1;
    }

    /**
     * 判断队列空
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加元素
     * @param n
     */
    public void addQueue(int n) {
        if(isFull()) {
            System.out.println("队列已满，不能添加");
            return;
        }
        // 尾指针加1
        rear ++;
        // 赋值
        arr[rear] = n;
    }

    /**
     * 取出元素
     * @return
     */
    public int getQueue() {
        if(isEmpty()) {
            throw new RuntimeException("队列为空，不能出队");
        }
        // 队列头后移
        front ++;
        return arr[front];
    }

    public void showQueue() {
        if(isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    public int headQueue() {
        if(isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];
    }

}

class Main {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        queue.addQueue(8);
        queue.addQueue(5);
        queue.addQueue(6);

        queue.showQueue();
        System.out.println(queue.headQueue());
        System.out.println(queue.getQueue());
        System.out.println(queue.getQueue());
        System.out.println(queue.headQueue());
        queue.addQueue(3);
        queue.addQueue(9);
        queue.addQueue(7);
        queue.showQueue();
        System.out.println(queue.headQueue());
    }
}
