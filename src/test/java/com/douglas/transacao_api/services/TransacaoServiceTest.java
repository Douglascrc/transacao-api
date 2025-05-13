package com.douglas.transacao_api.services;

import com.douglas.transacao_api.controllers.dtos.TransacaoRequestDTO;
import com.douglas.transacao_api.infra.exceptions.UnprocessableEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    @Mock
    TransacaoRequestDTO transacao;

    @BeforeEach
    void setUp(){
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
    }

    @Test
    void deveAdicionarTransacao() {
        transacaoService.adicionarTransacao(transacao);

        List<TransacaoRequestDTO> trancacoes = transacaoService.buscarTransacoes(5000);
        assertTrue(trancacoes.contains(transacao));
    }

    @Test
    void deveLancarExcecaoCasoValorNegativo() {
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacao(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals("O valor não pode ser menor que 0", exception.getMessage());
    }

    @Test
    void deveLancarExececaoSeValorNegativo() {
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class, () -> transacaoService.adicionarTransacao(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Data e hora maiores que a atual", exception.getMessage());
    }

    @Test
    void deveLimparTrancacao() {
        transacaoService.limparTransacoes();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void  deveBuscarTrancacoesPorIntervalo() {
        TransacaoRequestDTO dto = new TransacaoRequestDTO(10.00, OffsetDateTime.now().minusHours(1));

        transacaoService.adicionarTransacao(transacao);
        transacaoService.adicionarTransacao(dto);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));
    }
}
