package com.buffet.buffet.controller.assignment.assignmentdto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class AssignmentDTO {
    @NotEmpty(message = "El usuario no puede estar vacio")
    private String userEmail;
    @NotEmpty(message = "El numero de orden no puede estar vacio")
    private String numOrder;
}
