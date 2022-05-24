package ru.whitegray.spring.web.repositories;

import ru.whitegray.spring.web.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
}
