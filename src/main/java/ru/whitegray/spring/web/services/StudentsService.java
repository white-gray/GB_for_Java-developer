package ru.whitegray.spring.web.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.whitegray.spring.web.entities.Student;
import ru.whitegray.spring.web.repositories.StudentsRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentsService {
    private final StudentsRepository studentsRepository;

    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentsRepository.findById(id);
    }

    public void deleteById(Long id) {
        studentsRepository.deleteById(id);
    }

    public Student save(Student student) {
        return studentsRepository.save(student);
    }

    @Transactional
    public Student update(Student student) {
        Student entity = null;
        if (student.getId() != null) {
            entity = studentsRepository.findById(student.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Student with id " + student.getId() + " doesn't exist!"));
        } else {
            entity = studentsRepository.save(new Student(student.getName(), student.getAge()));
        }
        return entity;
    }
}
