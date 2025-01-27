package com.douglas.transacao_api.controllers;

import com.douglas.transacao_api.controllers.dtos.TransacaoRequestDTO;
import com.douglas.transacao_api.services.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto) {
        transacaoService.adicionarTransacao(dto);

        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacao() {
        transacaoService.limparTransacoes();

        return  ResponseEntity.ok().build();
    }
}
