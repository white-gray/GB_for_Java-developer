package ru.whitegray.Dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.whitegray.Entity.Student;
import ru.whitegray.Session.LearnSessionFactory;


public class StudentDao {
    private Long id;
    private String name;
    private String surname;
    private Integer age;


    public StudentDao() {}

    public StudentDao(Long id, String name, String surname, Integer age) {
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.age=age;
    }

    public StudentDao(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

