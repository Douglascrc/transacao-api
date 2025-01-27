package com.douglas.transacao_api.services;

import com.douglas.transacao_api.controllers.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {
    private final List<TransacaoRequestDTO>  listaTransacoes = new ArrayList<>();

    public void  adicionarTransacao(TransacaoRequestDTO dto) {
        if(dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a  atual");
            throw new IllegalArgumentException("Data e hora inválidas");
        }
        if(dto.valor() < 0 ) {
            log.error("O valor não pode ser negativo");
            throw  new IllegalArgumentException("O valor não pode ser menor que 0");
        }

        listaTransacoes.add(dto);
    }

    public  void limparTransacoes() {
        listaTransacoes.clear();
    }

}
