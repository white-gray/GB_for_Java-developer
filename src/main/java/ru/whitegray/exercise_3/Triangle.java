package ru.whitegray.exercise_3;

public class Triangle implements Shape{
    private int side1;
    private int side2;
    private int side3;

    public Triangle(int side1, int side2, int side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public void draw() {
        System.out.println("Треугольник со сторонами " + side1 + ", " +  side2 + ", " + side3);
    }
}
