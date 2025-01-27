package com.douglas.transacao_api.controllers.dtos;

public record EstatisticaResponseDTO(Long count, double sum, double avg, double min, double max) {
}
