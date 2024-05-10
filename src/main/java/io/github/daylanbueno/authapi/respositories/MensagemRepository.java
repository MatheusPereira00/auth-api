package io.github.daylanbueno.authapi.respositories;

import io.github.daylanbueno.authapi.models.Mensagens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagens, Long> {
}
