package ru.whitegray;

import ru.whitegray.dao.StudentDao;
import ru.whitegray.session.LearnSessionFactory;
import ru.whitegray.toDo.ToDo;

public class LearnMain {

    public static void main(String[] args) {
//        Session session = LearnSessionFactory.getSessionFactory().openSession();
//
//        session.beginTransaction();
        LearnSessionFactory session = new LearnSessionFactory();
        ToDo todo = new ToDo();
//        Transaction transaction = session.beginTransaction();

        /*
        * добавить 1000 записей*/
        session.openCurrentSessionwithTransaction();
        for (int q = 1; q <= 1000; q++) {
            StudentDao studentDao = new StudentDao("Студент", "Student_" + q, (int) Math.random() * 42);
            todo.save(studentDao);
        }
        session.closeCurrentSessionwithTransaction();
        System.out.println("Now table is\n" + todo.findAll());


        session.openCurrentSessionwithTransaction();


        System.out.println(todo.findAll());
        todo.deleteById("12");
        todo.deleteAll();
        System.out.println("After clear table\n" + todo.findAll());

        session.closeCurrentSessionwithTransaction();
    }

}
