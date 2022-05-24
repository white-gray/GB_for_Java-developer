package ru.whitegray.spring.web.converters;

import ru.whitegray.spring.web.dto.StudentDto;
import ru.whitegray.spring.web.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    public Student dtoToEntity(StudentDto studentDto) {
        return new Student(studentDto.getId(), studentDto.getName(), studentDto.getAge());
    }

    public StudentDto entityToDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getAge());
    }
}
