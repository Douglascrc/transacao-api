package com.douglas.transacao_api.services;

import com.douglas.transacao_api.controllers.dtos.EstatisticaResponseDTO;
import com.douglas.transacao_api.controllers.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {
    private final TransacaoService transacaoService;

    public EstatisticaResponseDTO  trazerEstatisticas(Integer intervaloBusca) {
        log.info("Buscando transações pelo intervalo de tempo de " + intervaloBusca);
    List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

    if(transacoes.isEmpty()) {
        return  new EstatisticaResponseDTO(0L, 0.0,  0.0 , 0.0, 0.0);
    }

    DoubleSummaryStatistics estatisticasTransacoes  = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Transações retornadas com sucesso!");
        return new EstatisticaResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }

}