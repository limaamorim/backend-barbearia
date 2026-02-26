package br.com.barbearia.api.service;

import br.com.barbearia.api.dto.AgendamentoRequestDTO;
import br.com.barbearia.api.dto.AgendamentoResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoService {

    AgendamentoResponseDTO criar(AgendamentoRequestDTO dto);

    AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO dto);

    List<AgendamentoResponseDTO> listar();

    List<AgendamentoResponseDTO> listarPorData(LocalDate data);

    void cancelar(Long id);

    void confirmar(Long id);
}