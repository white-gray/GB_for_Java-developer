package ru.whitegray.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Описание студента")
public class StudentDto {
    @Schema(description = "ID студента", required = true, example = "1")
    private Long id;

    @Schema(description = "Имя студента", required = true, maxLength = 255, minLength = 1, example = "Серый")
    private String name;

    @Schema(description = "Возраст студента", required = true, example = "130")
    private BigDecimal age;

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

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public StudentDto() {
    }

    public StudentDto(Long id, String name, BigDecimal age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
