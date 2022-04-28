package ru.whitegray.exercise_1_PingPong;

public class SyncQueue {
    boolean flag = true;
    synchronized void ping() {
        while (!flag){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("ping ");
        flag = false;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify();
    }
    synchronized void pong() {
        while (flag) {
            try {
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("- pong !");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        notify();
    }

}
