package io.github.daylanbueno.authapi.mapper;

import io.github.daylanbueno.authapi.models.Usuario;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ResponseUserMapper {
    ResponseUser toResponse(Usuario usuario);
    Usuario toEntity(ResponseUser responseUser);
}
