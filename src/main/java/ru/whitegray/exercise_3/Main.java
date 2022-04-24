package ru.whitegray.exercise_3;

public class Main {
    public static void main(String[] args) {
        Shape[] figures = new Shape[] {new Triangle(3,2,5), new Square(3), new Сircle(7), new Square(7), new Square(33)} ;

        for(Shape figure : figures) {
            figure.draw();
        }
    }
}
