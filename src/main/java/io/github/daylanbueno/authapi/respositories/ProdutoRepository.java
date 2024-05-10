package io.github.daylanbueno.authapi.respositories;

import io.github.daylanbueno.authapi.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
