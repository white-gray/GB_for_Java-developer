package ru.whitegray.spring.web.validators;

import ru.whitegray.spring.web.entities.Student;
import ru.whitegray.spring.web.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentValidator {
    public void validate(Student student) {
        List<String> errors = new ArrayList<>();
        if (student.getAge().compareTo(BigDecimal.ONE) < 18) {
            errors.add("Возраст студента должен быть от 18 лет");
        }
        if (student.getName().isBlank()) {
            errors.add("Сиудент не может быть безымянным");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
