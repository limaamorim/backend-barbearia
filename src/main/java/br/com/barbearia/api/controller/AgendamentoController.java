package br.com.barbearia.api.controller;

import br.com.barbearia.api.dto.AgendamentoRequestDTO;
import br.com.barbearia.api.dto.AgendamentoResponseDTO;
import br.com.barbearia.api.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criar(
            @RequestBody AgendamentoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPorData(
            @PathVariable LocalDate data) {

        return ResponseEntity.ok(service.listarPorData(data));
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmar(@PathVariable Long id) {
        service.confirmar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody AgendamentoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}