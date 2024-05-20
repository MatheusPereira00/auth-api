package io.github.daylanbueno.authapi.controllers;

import io.github.daylanbueno.authapi.dtos.UsuarioDto;
import io.github.daylanbueno.authapi.dtos.UsuarioResponseDTO;
import io.github.daylanbueno.authapi.mapper.ResponseUser;
import io.github.daylanbueno.authapi.models.Produto;
import io.github.daylanbueno.authapi.models.Usuario;
import io.github.daylanbueno.authapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto) {
        return  usuarioService.salvar(usuarioDto) ;
    }

    @GetMapping
    private ResponseEntity<List<UsuarioResponseDTO>> findAllUsers() {
        List<UsuarioResponseDTO> usuario = usuarioService.findAllUsers();
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/2")
    private ResponseEntity<List<ResponseUser>> findAllUsers2() {
        List<ResponseUser> usuario = usuarioService.findAllUsers2();
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/admin")
    private String getAdmin() {
        return "permissão de administrador";
    }

    @GetMapping("/user")
    private String getUser() {
        return "permissão de usuário";
    }
}
