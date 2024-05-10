package io.github.daylanbueno.authapi.services;

import io.github.daylanbueno.authapi.dtos.UsuarioDto;
import io.github.daylanbueno.authapi.dtos.UsuarioResponseDTO;
import io.github.daylanbueno.authapi.models.Mensagens;
import io.github.daylanbueno.authapi.models.Produto;
import io.github.daylanbueno.authapi.models.Usuario;

import java.util.List;

public interface UsuarioService {

    public UsuarioDto salvar(UsuarioDto usuarioDto);

    public List<UsuarioResponseDTO> findAllUsers();

    Produto criarProduto(String nome, String descricao, Long usuarioId);

    Mensagens criarMensagem(Long produtoId, Long usuarioId, String texto);
}
