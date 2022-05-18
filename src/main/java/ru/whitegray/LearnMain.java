package ru.whitegray;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.whitegray.Dao.StudentDao;
import ru.whitegray.Session.LearnSessionFactory;
import ru.whitegray.ToDo.ToDo;

import javax.xml.namespace.QName;

public class LearnMain {

    public static void main(String[] args) {
//        Session session = LearnSessionFactory.getSessionFactory().openSession();
//
//        session.beginTransaction();
        LearnSessionFactory session = new LearnSessionFactory();
        ToDo todo = new ToDo();
//        Transaction transaction = session.beginTransaction();

        session.openCurrentSessionwithTransaction();

//        for (int q = 1; q < 1001; q++) {
//            String name = "Student_"+q;
//            String surname = name;
//        StudentDao name = new StudentDao("Студент", surname, (int)Math.random()*42);
//        }

        StudentDao student1 = new StudentDao("Студент", "student1", (int)Math.random()*42);


        System.out.println(todo.findAll());
        todo.deleteAll();
        todo.save(student1);
        System.out.println("after clear teble" + todo.findAll());


        todo.save(student1);
//        sessitoon.getTransaction().commit();

//        session.close();
        session.closeCurrentSessionwithTransaction();
    }

}
