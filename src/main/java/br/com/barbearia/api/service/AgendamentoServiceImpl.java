package br.com.barbearia.api.service;

import br.com.barbearia.api.dto.AgendamentoRequestDTO;
import br.com.barbearia.api.dto.AgendamentoResponseDTO;
import br.com.barbearia.api.mapper.AgendamentoMapper;
import br.com.barbearia.api.model.Agendamento;
import br.com.barbearia.api.model.Servico;
import br.com.barbearia.api.repository.AgendamentoRepository;
import br.com.barbearia.api.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository repository;
    private final ServicoRepository servicoRepository;

    public AgendamentoServiceImpl(
            AgendamentoRepository repository,
            ServicoRepository servicoRepository
    ) {
        this.repository = repository;
        this.servicoRepository = servicoRepository;
    }

    @Override
    public AgendamentoResponseDTO criar(AgendamentoRequestDTO dto) {

        if (repository.existsByDataAndHoraInicio(dto.getData(), dto.getHoraInicio())) {
            throw new RuntimeException("Horário já ocupado");
        }

        Servico servico = servicoRepository.findById(dto.getServicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Agendamento agendamento = new Agendamento();
        agendamento.setNomeCliente(dto.getNomeCliente());
        agendamento.setTelefoneCliente(dto.getTelefoneCliente());
        agendamento.setData(dto.getData());
        agendamento.setHoraInicio(dto.getHoraInicio());

        LocalTime horaFim = dto.getHoraInicio()
                .plusMinutes(servico.getDuracaoMinutos());

        agendamento.setHoraFim(horaFim);
        agendamento.setServico(servico);
        agendamento.setStatus("AGENDADO");

        return AgendamentoMapper.toResponseDTO(
                repository.save(agendamento)
        );
    }

    @Override
    public List<AgendamentoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(AgendamentoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<AgendamentoResponseDTO> listarPorData(java.time.LocalDate data) {
        return repository.findByData(data)
                .stream()
                .map(AgendamentoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void cancelar(Long id) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamento.setStatus("CANCELADO");
        repository.save(agendamento);
    }

    @Override
    public void confirmar(Long id) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamento.setStatus("CONFIRMADO");
        repository.save(agendamento);
    }

    @Override
    public AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO dto) {

        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        Servico servico = servicoRepository.findById(dto.getServicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        if (!agendamento.getHoraInicio().equals(dto.getHoraInicio())
                && repository.existsByDataAndHoraInicio(dto.getData(), dto.getHoraInicio())) {
            throw new RuntimeException("Horário já ocupado");
        }

        agendamento.setNomeCliente(dto.getNomeCliente());
        agendamento.setTelefoneCliente(dto.getTelefoneCliente());
        agendamento.setData(dto.getData());
        agendamento.setHoraInicio(dto.getHoraInicio());
        agendamento.setHoraFim(
                dto.getHoraInicio().plusMinutes(servico.getDuracaoMinutos())
        );
        agendamento.setServico(servico);

        return AgendamentoMapper.toResponseDTO(
                repository.save(agendamento)
        );
    }

}