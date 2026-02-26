package br.com.barbearia.api.mapper;

import br.com.barbearia.api.model.Servico;
import br.com.barbearia.api.dto.ServicoRequestDTO;
import br.com.barbearia.api.dto.ServicoResponseDTO;

public class ServicoMapper {

    // DTO → Entity
    public static Servico toEntity(ServicoRequestDTO dto) {
        Servico servico = new Servico();
        servico.setNome(dto.getNome());
        servico.setPreco(dto.getPreco());
        servico.setDuracaoMinutos(dto.getDuracaoMinutos());
        return servico;
    }

    // Entity → DTO
    public static ServicoResponseDTO toResponseDTO(Servico entity) {
        ServicoResponseDTO dto = new ServicoResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setPreco(entity.getPreco());
        dto.setDuracaoMinutos(entity.getDuracaoMinutos());
        return dto;
    }
}