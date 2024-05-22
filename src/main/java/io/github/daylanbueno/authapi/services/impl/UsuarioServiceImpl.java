package io.github.daylanbueno.authapi.services.impl;

import io.github.daylanbueno.authapi.dtos.UsuarioDto;
import io.github.daylanbueno.authapi.dtos.UsuarioResponseDTO;
import io.github.daylanbueno.authapi.infra.exceptions.BusinessException;
import io.github.daylanbueno.authapi.mapper.ResponseUser;
import io.github.daylanbueno.authapi.models.Mensagens;
import io.github.daylanbueno.authapi.models.Produto;
import io.github.daylanbueno.authapi.models.Usuario;
import io.github.daylanbueno.authapi.respositories.MensagemRepository;
import io.github.daylanbueno.authapi.respositories.ProdutoRepository;
import io.github.daylanbueno.authapi.respositories.UsuarioRepository;
import io.github.daylanbueno.authapi.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {

        Usuario usuarioJaExiste = usuarioRepository.findByLogin(usuarioDto.login());

        if (usuarioJaExiste != null) {
            throw new BusinessException("Usuário já existe!");
        }

        var passwordHash = passwordEncoder.encode(usuarioDto.senha());

        Usuario entity = new Usuario(usuarioDto.nome(), usuarioDto.login(), passwordHash, usuarioDto.role());

        Usuario novoUsuario = usuarioRepository.save(entity);

        return new UsuarioDto(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getSenha(), novoUsuario.getRole());
    }


    @Override
    public Produto criarProduto(String nome, String descricao, Long usuarioId) {
        // Busca o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // Cria um novo produto
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setUsuario(usuario);

        // Salva o produto no banco de dados
        return produtoRepository.save(produto);
    }


    @Override
    public Mensagens criarMensagem(Long produtoId, Long usuarioId, String texto) {
        // Busca o produto pelo ID
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        // Busca o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // Cria uma nova mensagem
        Mensagens mensagem = new Mensagens();
        mensagem.setTexto(texto);
        mensagem.setProduto(produto);
        mensagem.setUsuario(usuario);
        mensagem.setDataCriacao(LocalDate.now());

        // Salva a mensagem no banco de dados
        return mensagemRepository.save(mensagem);
    }

    @Override
    public List<UsuarioResponseDTO> findAllUsers(){
        List<Usuario> users = this.usuarioRepository.findAll();
        return users
                .stream()
                .map(user -> mapper.map(user, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseUser> findAllUsers2(){
        List<Usuario> users = this.usuarioRepository.findAll();
        return users
                .stream()
                .map(user -> mapper.map(user, ResponseUser.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Produto> findProducts(Long id) {
        return produtoRepository.findProductByUsuarioId(id);
    }

    @Override
    public List<Produto> findAllProducts() {
        return produtoRepository.findAll();
    }

    @Override
    public Page<Produto> findAllProductsPageable(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }

}
