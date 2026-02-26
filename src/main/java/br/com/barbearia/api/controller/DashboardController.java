package br.com.barbearia.api.controller;

import br.com.barbearia.api.dto.DashboardResponseDTO;
import br.com.barbearia.api.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:8080")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public DashboardResponseDTO resumo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {
        return service.buscarResumo(inicio, fim);
    }
}