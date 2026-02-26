package br.com.barbearia.api.repository;

import br.com.barbearia.api.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
