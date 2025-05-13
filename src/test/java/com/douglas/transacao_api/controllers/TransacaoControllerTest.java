package com.douglas.transacao_api.controllers;

import com.douglas.transacao_api.controllers.dtos.TransacaoRequestDTO;
import com.douglas.transacao_api.services.TransacaoService;
import com.douglas.transacao_api.infra.exceptions.UnprocessableEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {

    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private TransacaoRequestDTO transacao;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController)
                .setControllerAdvice(new UnprocessableEntity("Erro"))
                .build();

        transacao = new TransacaoRequestDTO(20.0,
                OffsetDateTime.of(2024, 2, 18, 14, 30, 0, 0, ZoneOffset.UTC));
    }

    @Test
    void deveAdicionarTransacao() throws Exception {
        doNothing().when(transacaoService).adicionarTransacao(any(TransacaoRequestDTO.class));

        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(transacaoService).adicionarTransacao(any(TransacaoRequestDTO.class));
    }

    @Test
    void deveGerarExcecaoAoAdicionarTransacao() throws Exception {
        doThrow(new UnprocessableEntity("Erro de requisição"))
                .when(transacaoService).adicionarTransacao(any(TransacaoRequestDTO.class));

        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deveDeletarTransacoesComSucesso() throws Exception {
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());

        verify(transacaoService).limparTransacoes();
    }
}