package com.vevor.scp.forecast.util;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
public class ProducerConsumerExample {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>();
        // Create producer and consumer threads
        Thread producerThread = new Thread(new Producer(sharedQueue));
        Thread consumerThread = new Thread(new Consumer(sharedQueue));
        // Start producer and consumer threads
        producerThread.start();
        consumerThread.start();
    }
}
class Producer implements Runnable {
    private final LinkedBlockingQueue<Integer> sharedQueue;
    public Producer(LinkedBlockingQueue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Produced: " + i);
                sharedQueue.put(i);
                Thread.sleep(new Random().nextInt(3) * 1000L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
class Consumer implements Runnable {
    private final LinkedBlockingQueue<Integer> sharedQueue;
    public Consumer(LinkedBlockingQueue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed: " + sharedQueue.take());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
