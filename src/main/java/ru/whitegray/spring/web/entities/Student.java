package ru.whitegray.spring.web.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private BigDecimal age;

    public Student(Long id, String name, BigDecimal age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(String name, BigDecimal age) {
        this.name = name;
        this.age = age;
    }
}
