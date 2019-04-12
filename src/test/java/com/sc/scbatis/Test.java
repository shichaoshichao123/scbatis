package com.sc.scbatis;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: shichao
 * @date: 2019/4/12
 * @description:
 */
public class Test {


    private static class Provider implements Runnable {
        public Provider(Queue<Integer> workQueue) {
            this.workQueue = workQueue;
        }

        private Queue<Integer> workQueue;
        public void run() {
            while(true){
                synchronized (workQueue) {
                    while (workQueue.size() == 20) {
                        try {
                            System.out.println("任务队列已满...");
                            workQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        workQueue.offer(1);
                        System.out.println(Thread.currentThread().getName()+"加入任务...对列大小：" + workQueue.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    workQueue.notifyAll();
                }
            }



        }
    }

    private static class Comsummer implements Runnable {
        private Queue<Integer> workQueue;

        public Comsummer(Queue<Integer> workQueue) {
            this.workQueue = workQueue;
        }

        public void run() {
            while(true){
                synchronized (workQueue) {
                    while (workQueue.isEmpty()) {
                        try {
                            System.out.println("任务队列为空...");
                            workQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    workQueue.poll();

                    System.out.println("消费任务...对列大小： " + workQueue.size());
                    workQueue.notifyAll();
                }
            }

        }

    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();

        Provider p = new Provider(queue);

        Thread threadProvider1 = new Thread(p);
        Thread threadProvider2 = new Thread(p);
        Thread threadProvider3 = new Thread(p);
        Thread threadConsummer = new Thread(new Comsummer(queue));

        threadProvider1.start();
        threadProvider2.start();
        threadProvider3.start();
        threadConsummer.start();
    }
}
