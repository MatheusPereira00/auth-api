package io.github.daylanbueno.authapi.services;

import io.github.daylanbueno.authapi.dtos.UsuarioDto;
import io.github.daylanbueno.authapi.dtos.UsuarioResponseDTO;
import io.github.daylanbueno.authapi.mapper.ResponseUser;
import io.github.daylanbueno.authapi.models.Mensagens;
import io.github.daylanbueno.authapi.models.Produto;
import io.github.daylanbueno.authapi.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {

    public UsuarioDto salvar(UsuarioDto usuarioDto);

    public List<UsuarioResponseDTO> findAllUsers();

    public List<ResponseUser> findAllUsers2();

    public List<Produto> findProducts(Long id);

    public List<Produto> findAllProducts();

    public Page<Produto> findAllProductsPageable(Pageable pageable);

    Produto criarProduto(String nome, String descricao, Long usuarioId);

    Mensagens criarMensagem(Long produtoId, Long usuarioId, String texto);
}
