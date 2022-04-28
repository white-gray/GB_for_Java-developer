package ru.whitegray.exercise_1_PingPong;

public class Main {
    public static void main(String[] args) {
        SyncQueue sq = new SyncQueue();
        new Ping(sq);
        new Pong(sq);
    }
}
