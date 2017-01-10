package at.hennerbichler.reactiveprogramming.examples;

import java.util.ArrayDeque;
import java.util.Queue;

public class WorkerQueue<T> {
    private int size;
    private Queue<T> queue;

    public WorkerQueue(int size) {
        this.size = size;
        queue = new ArrayDeque<>();
    }

    public synchronized void enqueue(T value) throws InterruptedException {
        while (queue.size() == size) {
            this.wait();
        }
        queue.add(value);
        if (queue.size() == 1)
            this.notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.size() == 0) {
            this.wait();
        }
        T head = queue.remove();
        if (queue.size() == size -1)
            this.notifyAll();
        return head;
    }
}
