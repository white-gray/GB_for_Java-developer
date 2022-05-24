package ru.whitegray.spring.web.controllers;

import ru.whitegray.spring.web.exceptions.ResourceNotFoundException;
import ru.whitegray.spring.web.converters.StudentConverter;
import ru.whitegray.spring.web.dto.StudentDto;
import ru.whitegray.spring.web.entities.Student;
import ru.whitegray.spring.web.services.StudentsService;
import ru.whitegray.spring.web.validators.StudentValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Студенты", description = "Список студентов")
public class StudentsController {
    private final StudentsService studentsService;
    private final StudentConverter studentConverter;
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
    public Page<StudentDto> getAllStudents(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "name", required = false) String name
    ) {
        if (page < 1) {
            page = 1;
        }
        return studentsService.findAll(name, page).map(
                p -> studentConverter.entityToDto(p)
        );
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Запрос на показ студента по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StudentDto.class))
                    )
            }
    )
    public StudentDto getStudentById(
            @PathVariable @Parameter(description = "Идентификатор студента", required = true) Long id
    ) {
        Student student = studentsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found, id: " + id));
        return studentConverter.entityToDto(student);
    }

    @PostMapping
    public StudentDto saveNewStudent(@RequestBody StudentDto studentDto) {
        studentValidator.validate(studentDto);
        Student student = studentConverter.dtoToEntity(studentDto);
        student = studentsService.save(student);
        return studentConverter.entityToDto(student);
    }

    @PutMapping
    public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
        studentValidator.validate(studentDto);
        Student student = studentsService.update(studentDto);
        return studentConverter.entityToDto(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentsService.deleteById(id);
    }
}
