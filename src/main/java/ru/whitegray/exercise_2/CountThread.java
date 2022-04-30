package ru.whitegray.exercise_2;

import java.util.concurrent.locks.ReentrantLock;

public class CountThread implements Runnable{

    CommonResource res;
    ReentrantLock locker;
    CountThread(CommonResource res, ReentrantLock lock){
        this.res=res;
        locker = lock;
    }
    public void run(){

        locker.lock(); // устанавливаем блокировку
        try{
            res.x=1;    // запускаем действие
            for (int i = 1; i <= 5; i++){
                System.out.printf("%s - операция %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock(); // снимаем блокировку
        }
    }
}