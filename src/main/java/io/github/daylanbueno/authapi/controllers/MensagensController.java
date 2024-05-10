package io.github.daylanbueno.authapi.controllers;

import io.github.daylanbueno.authapi.dtos.MensagensDTO;
import io.github.daylanbueno.authapi.models.Mensagens;
import io.github.daylanbueno.authapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/produtos/{produtoId}/mensagens")
public class MensagensController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public String criarMensagem(@PathVariable Long produtoId, @RequestBody Mensagens mensagens) {
        Long usuarioId = mensagens.getUsuario().getId();
        String texto = mensagens.getTexto();

        Mensagens mensagem = usuarioService.criarMensagem(produtoId, usuarioId, texto);
        return "Comentario feito";
    }
}
