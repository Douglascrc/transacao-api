package com.douglas.transacao_api.controllers;

import com.douglas.transacao_api.controllers.dtos.EstatisticaResponseDTO;
import com.douglas.transacao_api.services.EstatisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    public ResponseEntity<EstatisticaResponseDTO> listarEstatisticas(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer iintervaloBusca) {

        return ResponseEntity.ok(estatisticasService.trazerEstatisticas(iintervaloBusca));
    }
}
