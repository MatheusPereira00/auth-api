package io.github.daylanbueno.authapi.services.impl;

import io.github.daylanbueno.authapi.dtos.UsuarioDto;
import io.github.daylanbueno.authapi.dtos.UsuarioResponseDTO;
import io.github.daylanbueno.authapi.enums.RoleEnum;
import io.github.daylanbueno.authapi.mapper.ResponseUser;
import io.github.daylanbueno.authapi.models.Produto;
import io.github.daylanbueno.authapi.models.Usuario;
import io.github.daylanbueno.authapi.respositories.ProdutoRepository;
import io.github.daylanbueno.authapi.respositories.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {


    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private final ModelMapper mapper = new ModelMapper();

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    UsuarioResponseDTO usuarioResponseDTO;
    ResponseUser responseUser;
    Produto produto;
    Usuario usuario;

    @BeforeEach
    void setUp(){
        produto =  new Produto(1L, "Produto 1", "Description 1");
        usuario =  new Usuario("Usuario 2", "Login 2", "12345", RoleEnum.ADMIN);
    }

    @Test
    void criarProduto() {

        Produto produto = new Produto();
        produto.setNome("produto");
        produto.setDescricao("descricao");
        produto.setUsuario(usuario);

        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.ofNullable(usuario));
        when(produtoRepository.save(any())).thenReturn(produto);

        Produto produtoReal = usuarioService.criarProduto(produto.getNome(), produto.getDescricao(), produto.getUsuario().getId());

        assertEquals(produto, produtoReal);

    }

    @Test
    void criarMensagem() {
    }

    @Test
    void findAllUsers() {
    }

    @Test
    void findAllUsers2() {
        List<Usuario> expectedUser = Arrays.asList(
                new Usuario("Usuario 1", "Login 1", "12345", RoleEnum.ADMIN),
                new Usuario("Usuario 2", "Login 2", "12345", RoleEnum.ADMIN)
        );

        List<ResponseUser> expectedResponseUser = Arrays.asList(
                new ResponseUser("Usuario 1", "Login 1", "12345", RoleEnum.ADMIN),
                new ResponseUser("Usuario 2", "Login 2", "12345", RoleEnum.ADMIN)
        );


        when(usuarioRepository.findAll()).thenReturn(expectedUser);

        List<ResponseUser> actualUser = usuarioService.findAllUsers2();

        assertEquals(expectedResponseUser, actualUser);
        assertNotNull(actualUser);
    }

    @Test
    void findProducts() {
        when(produtoRepository.findProductByUsuarioId(produto.getId())).thenReturn(Collections.singletonList(produto));
        List<Produto> produtoList = usuarioService.findProducts(produto.getId());
        assertEquals(Collections.singletonList(produto), produtoList);
    }

    @Test
    void findAllProducts() {

        List<Produto> expectedProdutos = Arrays.asList(
                new Produto(1L, "Produto 1", "Description 1"),
                new Produto(2L, "Produto 2", "Description 2")
        );

        when(produtoRepository.findAll()).thenReturn(expectedProdutos);

        List<Produto> actualProdutos = usuarioService.findAllProducts();

        assertEquals(expectedProdutos, actualProdutos, "TEST PASSOU");

        verify(produtoRepository).findAll();

    }
}