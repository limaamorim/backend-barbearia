package br.com.barbearia.api.mapper;

import br.com.barbearia.api.dto.AgendamentoResponseDTO;
import br.com.barbearia.api.model.Agendamento;

public class AgendamentoMapper {

    public static AgendamentoResponseDTO toResponseDTO(Agendamento entity) {
        AgendamentoResponseDTO dto = new AgendamentoResponseDTO();
        dto.setId(entity.getId());
        dto.setNomeCliente(entity.getNomeCliente());
        dto.setTelefoneCliente(entity.getTelefoneCliente());
        dto.setData(entity.getData());
        dto.setHoraInicio(entity.getHoraInicio());
        dto.setHoraFim(entity.getHoraFim());
        dto.setServicoId(entity.getServico().getId());
        dto.setServicoNome(entity.getServico().getNome());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}