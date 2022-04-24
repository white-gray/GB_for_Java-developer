package ru.whitegray.exercise_3;

public class Square implements Shape{
    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println("Квадрат c длиной стороны " + side);

    }
}
