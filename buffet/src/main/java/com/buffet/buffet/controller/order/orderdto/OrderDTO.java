package com.buffet.buffet.controller.order.orderdto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class OrderDTO {
    private Double orderPrice;
    @NotEmpty(message = "La calle no puede estar en blanco")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "La calle no debe contener caracteres especiales")
    private String street;
    @NotEmpty(message = "La colonia no puede estar en blanco")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "La colonia no debe contener caracteres especiales")
    private String disctric;
    @NotEmpty(message = "El codigo postal no puede estar en blanco")
    @Pattern(regexp = "^[0-9]+$", message = "El codigo postal no debe contener caracteres especiales")
    private String postalCode;
    @NotEmpty(message = "El estado no puede estar en blanco")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El estado no debe contener caracteres especiales")
    private String city;
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "La referencia no debe contener caracteres especiales")
    private String comments;
    private String userEmail;
    private String packageName;

}
