package io.github.daylanbueno.authapi.mapper;

import io.github.daylanbueno.authapi.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUser {
    String nome;
    String login;
    String senha;
    RoleEnum role;
}
