package io.github.daylanbueno.authapi.respositories;

import io.github.daylanbueno.authapi.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findProductByUsuarioId(Long id);
}
