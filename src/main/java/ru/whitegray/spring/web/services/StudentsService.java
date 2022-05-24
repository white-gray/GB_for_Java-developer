package ru.whitegray.spring.web.services;

import ru.whitegray.spring.web.exceptions.ResourceNotFoundException;
import ru.whitegray.spring.web.dto.StudentDto;
import ru.whitegray.spring.web.entities.Student;
import ru.whitegray.spring.web.repositories.StudentsRepository;
//import ru.whitegray.spring.web.repositories.specifications.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentsService {
    private final StudentsRepository studentsRepository;

    public Page<Student> findAll(String name, Integer page) {
        Specification<Student> spec = Specification.where(null);
        return studentsRepository.findAll(spec, PageRequest.of(page - 1, 8));
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
    public Student update(StudentDto studentDto) {
        Student student = studentsRepository.findById(studentDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить студента, - не надйен в базе, id: " + studentDto.getId()));
        student.setAge(studentDto.getAge());
        student.setName(studentDto.getName());
        return student;
    }
}
