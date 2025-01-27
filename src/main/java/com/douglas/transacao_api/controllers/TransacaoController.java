package com.douglas.transacao_api.controllers;

import com.douglas.transacao_api.controllers.dtos.TransacaoRequestDTO;
import com.douglas.transacao_api.services.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService trasacaoService;

    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto) {
        trasacaoService.adicionarTransacao(dto);

        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
