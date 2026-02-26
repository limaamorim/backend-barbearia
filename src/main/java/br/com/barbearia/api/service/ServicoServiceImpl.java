package br.com.barbearia.api.service;

import br.com.barbearia.api.dto.ServicoRequestDTO;
import br.com.barbearia.api.dto.ServicoResponseDTO;
import br.com.barbearia.api.mapper.ServicoMapper;
import br.com.barbearia.api.model.Servico;
import br.com.barbearia.api.repository.ServicoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoServiceImpl implements ServicoService {

    private final ServicoRepository repository;

    public ServicoServiceImpl(ServicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServicoResponseDTO criar(ServicoRequestDTO dto) {
        Servico servico = ServicoMapper.toEntity(dto);
        return ServicoMapper.toResponseDTO(repository.save(servico));
    }

    @Override
    public List<ServicoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(ServicoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ServicoResponseDTO buscarPorId(Long id) {
        Servico servico = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Serviço não encontrado"));

        return ServicoMapper.toResponseDTO(servico);
    }

    @Override
    public ServicoResponseDTO atualizar(Long id, ServicoRequestDTO dto) {
        Servico servico = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Serviço não encontrado"));

        servico.setNome(dto.getNome());
        servico.setPreco(dto.getPreco());
        servico.setDuracaoMinutos(dto.getDuracaoMinutos());

        return ServicoMapper.toResponseDTO(repository.save(servico));
    }

    @Override
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado");
        }
        repository.deleteById(id);
    }
}