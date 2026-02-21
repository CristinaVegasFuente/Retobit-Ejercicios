package com.cristinavegas.holaMundo.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * Una vez tenemos el DTO vamos al controlador de @PatchMapping y podemos decirle que en vez de llegar un
 * actor nos llegara un ActorUpDateNameDTO y podemos introducir la anotacion @Valid.
 */
public class ActorUpDateNameDTO {

    @NotNull //no es nulo
    @NotEmpty //ni esta vacio
    private String name;

}

