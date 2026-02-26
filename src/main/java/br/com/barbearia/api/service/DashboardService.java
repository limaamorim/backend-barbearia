package br.com.barbearia.api.service;

import br.com.barbearia.api.dto.DashboardResponseDTO;
import br.com.barbearia.api.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    private final AgendamentoRepository repository;

    public DashboardService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public DashboardResponseDTO buscarResumo(LocalDate inicio, LocalDate fim) {

        Long confirmados =
                repository.contarConfirmadosPorPeriodo(inicio, fim);

        var faturamento =
                repository.somarFaturamentoPorPeriodo(inicio, fim);

        return new DashboardResponseDTO(confirmados, faturamento);
    }
}