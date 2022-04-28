package ru.whitegray.exercise_1_PingPong;

public class Ping implements Runnable{
    SyncQueue sq;
    public Ping(SyncQueue sq){
        this.sq = sq;
        new Thread(this).start();
    }
    @Override
    public void run() {
        while (true){
            sq.ping();
        }
    }
}
