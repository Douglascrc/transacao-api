package com.douglas.transacao_api.controllers;

import com.douglas.transacao_api.controllers.dtos.EstatisticaResponseDTO;
import com.douglas.transacao_api.services.EstatisticasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    @Operation(description = " Endpoint responsável por buscar transações em um ntervalo de tempo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transações retornadas com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos"),
            @ApiResponse(responseCode= "400", description = " Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<EstatisticaResponseDTO> listarEstatisticas(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer iintervaloBusca) {

        return ResponseEntity.ok(estatisticasService.trazerEstatisticas(iintervaloBusca));
    }
}
