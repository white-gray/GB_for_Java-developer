package ru.whitegray.spring.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.whitegray.spring.web.entities.Student;
import ru.whitegray.spring.web.exceptions.ResourceNotFoundException;
import ru.whitegray.spring.web.services.StudentsService;
import ru.whitegray.spring.web.validators.StudentValidator;

import java.util.List;

@RestController
@RequestMapping("/rest_api/v1")
@RequiredArgsConstructor
@Tag(name = "Студенты", description = "Список студентов")
public class Rest_Controller {
    private final StudentsService studentsService;
    private final StudentValidator studentValidator;

    @Operation(
            summary = "Запрос на получение страницы списка студентов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping
    public List<Student> getAllStudents() {
        return studentsService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Запрос на показ студента по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Student.class))
                    )
            }
    )
    public Student getStudentById(
            @PathVariable @Parameter(description = "Идентификатор студента", required = true) Long id
    ) {
        return studentsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found, id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveNewStudent(@RequestBody Student student) {
        student.setId(null);
        return studentsService.save(student);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentsService.update(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentsService.deleteById(id);
    }
}
