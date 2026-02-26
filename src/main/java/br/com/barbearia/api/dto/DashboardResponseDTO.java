package br.com.barbearia.api.dto;

import java.math.BigDecimal;

public class DashboardResponseDTO {

    private Long cortesConfirmados;
    private BigDecimal faturamento;

    public DashboardResponseDTO(Long cortesConfirmados, BigDecimal faturamento) {
        this.cortesConfirmados = cortesConfirmados;
        this.faturamento = faturamento;
    }

    public Long getCortesConfirmados() {
        return cortesConfirmados;
    }

    public BigDecimal getFaturamento() {
        return faturamento;
    }
}