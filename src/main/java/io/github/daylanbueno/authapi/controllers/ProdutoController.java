package io.github.daylanbueno.authapi.controllers;

import io.github.daylanbueno.authapi.models.Produto;
import io.github.daylanbueno.authapi.models.Usuario;
import io.github.daylanbueno.authapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        Long usuarioId = produto.getUsuario().getId();
        String nome = produto.getNome();
        String descricao = produto.getDescricao();

        Produto produtos = usuarioService.criarProduto(nome, descricao, usuarioId);
        return produto;
    }

    @GetMapping(path = "/usuario/{userId}")
    public List<Produto> retrievePosts(@PathVariable("userId") Long id ) {
        return usuarioService.findProducts(id);
    }

    @GetMapping
    public List<Produto> findAllPosts() {
        return usuarioService.findAllProducts();
    }

    @GetMapping(path = "/pageable")
    public List<Produto> findAllProductsPageable(Pageable pageable) {
        return usuarioService.findAllProductsPageable(pageable).getContent();
    }
}
