package io.github.daylanbueno.authapi.dtos;

import io.github.daylanbueno.authapi.enums.RoleEnum;
import io.github.daylanbueno.authapi.models.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private RoleEnum role;
    private List<Produto> produtos;
}
