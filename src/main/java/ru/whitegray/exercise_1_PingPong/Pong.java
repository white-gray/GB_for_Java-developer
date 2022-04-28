package ru.whitegray.exercise_1_PingPong;

public class Pong implements Runnable{
    SyncQueue sq;
    public Pong(SyncQueue sq){
        this.sq = sq;
        new Thread(this).start();
    }
    @Override
    public void run() {
        while (true){
            sq.pong();
        }
    }
}
