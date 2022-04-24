package ru.whitegray.exercise_3;

public class Сircle implements Shape{
    private int radius;

    public Сircle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Круг радиуса " + radius);
    }
}
