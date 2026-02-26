package br.com.barbearia.api.repository;

import br.com.barbearia.api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByData(LocalDate data);

    boolean existsByDataAndHoraInicio(LocalDate data, LocalTime horaInicio);

    @Query("""
    SELECT COUNT(a)
    FROM Agendamento a
    WHERE a.status = 'CONFIRMADO'
      AND a.data BETWEEN :inicio AND :fim
""")
    Long contarConfirmadosPorPeriodo(
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );

    @Query("""
    SELECT COALESCE(SUM(s.preco), 0)
    FROM Agendamento a
    JOIN a.servico s
    WHERE a.status = 'CONFIRMADO'
      AND a.data BETWEEN :inicio AND :fim
""")
    BigDecimal somarFaturamentoPorPeriodo(
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );
}