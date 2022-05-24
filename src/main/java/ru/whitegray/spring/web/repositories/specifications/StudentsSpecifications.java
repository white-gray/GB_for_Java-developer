package ru.whitegray.spring.web.repositories.specifications;

import ru.whitegray.spring.web.entities.Student;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class StudentsSpecifications {
    public static Specification<Student> ageGreaterOrEqualsThan(BigDecimal age) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("age"), age);
    }

    public static Specification<Student> ageLessThanOrEqualsThan(BigDecimal age) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("age"), age);
    }

    public static Specification<Student> nameLike(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name);
    }
}
