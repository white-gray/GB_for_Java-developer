package ru.whitegray.ToDo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.whitegray.Dao.StudentDao;
import ru.whitegray.Entity.Student;
import ru.whitegray.Session.LearnSessionFactory;

import java.util.List;

public class ToDo {
    private LearnSessionFactory learnSessionFactory;
    private Session currentSession;
    private Transaction currentTransaction;


    public void save(StudentDao studentDao) {
        learnSessionFactory.getCurrentSession().save(dtoToEntity(studentDao));
    }
    public void update(Student student) {
        learnSessionFactory.getCurrentSession().update(student);
    }
    public Student findById(String id) {
        Student student = (Student) learnSessionFactory.getCurrentSession().get(Student.class, id);
        return student;
    }
    public void delete(Student student) {
        learnSessionFactory.getCurrentSession().delete(student);
    }
    public List<Student> findAll() {
        List<Student> students = (List<Student>) learnSessionFactory.getCurrentSession().createQuery("from Student").list();
        return students;
    }
    public void deleteAll() {
        List<Student> entityList = findAll();
        entityList.clear();
//        for (Student entity : entityList) {
//            delete(entity);
//        }
    }

    public Student dtoToEntity(StudentDao studentDao) {
        return new Student(studentDao.getName(), studentDao.getSurname(), studentDao.getAge());
    }

    public StudentDao entityToDto(Student student) {
        return new StudentDao(student.getId(), student.getName(), student.getSurname(), student.getAge());
    }
}
