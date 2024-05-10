package io.github.daylanbueno.authapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MensagensDTO {

    private Long usuarioId;
    private String texto;
}
