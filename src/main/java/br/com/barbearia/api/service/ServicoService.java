package br.com.barbearia.api.service;

import br.com.barbearia.api.dto.ServicoRequestDTO;
import br.com.barbearia.api.dto.ServicoResponseDTO;

import java.util.List;

public interface ServicoService {

    ServicoResponseDTO criar(ServicoRequestDTO dto);

    List<ServicoResponseDTO> listar();

    ServicoResponseDTO buscarPorId(Long id);

    ServicoResponseDTO atualizar(Long id, ServicoRequestDTO dto);

    void deletar(Long id);
}