package com.douglas.transacao_api.services;

import com.douglas.transacao_api.controllers.dtos.EstatisticaResponseDTO;
import com.douglas.transacao_api.controllers.dtos.TransacaoRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {

    @InjectMocks
    EstatisticasService estatisticasService;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstatisticaResponseDTO estatisticas;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDTO(10.0, OffsetDateTime.now());
        estatisticas = new EstatisticaResponseDTO(1L,10.0,10.0,10.0,10.0);
    }

    @Test
    void trazerEstatisticas() {
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.singletonList(transacao));

        EstatisticaResponseDTO resultado = estatisticasService.trazerEstatisticas(60);

        verify(transacaoService,times(1)).buscarTransacoes(60);
        Assertions.assertThat(resultado).isEqualTo(estatisticas);
    }

    @Test
    void trazerEstatisticaQuandoListaVazia() {

        EstatisticaResponseDTO estatisticaEsperado = new EstatisticaResponseDTO(0L,0.0,0.0,0.0, 0.0);

        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.emptyList());
        EstatisticaResponseDTO resultado = estatisticasService.trazerEstatisticas((60));

        verify(transacaoService, times(1)).buscarTransacoes(60);
        Assertions.assertThat(resultado).isEqualTo(estatisticaEsperado);
    }
}
