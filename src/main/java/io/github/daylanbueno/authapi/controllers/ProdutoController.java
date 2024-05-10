package io.github.daylanbueno.authapi.controllers;

import io.github.daylanbueno.authapi.models.Produto;
import io.github.daylanbueno.authapi.models.Usuario;
import io.github.daylanbueno.authapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
