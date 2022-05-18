package ru.whitegray.ToDo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.whitegray.Dao.StudentDao;
import ru.whitegray.Entity.Student;
import ru.whitegray.Session.LearnSessionFactory;

import java.util.List;

public class ToDo {
    private LearnSessionFactory learnSessionFactory;

    public void save(StudentDao studentDao) {
        learnSessionFactory.getCurrentSession().save(dtoToEntity(studentDao));
    }
    public void update(StudentDao studentDao) {
        learnSessionFactory.getCurrentSession().update(dtoToEntity(studentDao));
    }

    public StudentDao findById(String id) {
        Student student = (Student) learnSessionFactory.getCurrentSession().get(Student.class, id);
        StudentDao studentDao = entityToDto(student);
        return studentDao;
    }
    public void delete(StudentDao studentDao) {
        learnSessionFactory.getCurrentSession().delete(dtoToEntity(studentDao));
    }
    public void deleteById(String id) {
        StudentDao studentDao = findById(id);
        learnSessionFactory.getCurrentSession().delete(dtoToEntity(studentDao));
    }

    public List<StudentDao> findAll() {
        List<StudentDao> students = (List<StudentDao>) learnSessionFactory.getCurrentSession().createQuery("SELECT * FROM Student").list();
        return students;
    }
    public void deleteAll() {
        List<StudentDao> entityList = findAll();
        for (StudentDao entity : entityList) {
            delete(entity);
        }
    }

    public Student dtoToEntity(StudentDao studentDao) {
        return new Student(studentDao.getName(), studentDao.getSurname(), studentDao.getAge());
    }

    public StudentDao entityToDto(Student student) {
        return new StudentDao(student.getId(), student.getName(), student.getSurname(), student.getAge());
    }
}
