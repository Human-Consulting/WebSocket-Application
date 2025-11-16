package com.example.websockethuman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MessageDto {
  private Integer idMensagem;
  private Integer idSala;
  private Integer idUsuario;
  private String nome;
  private String conteudo;
  private LocalDateTime horario;
  private boolean informativo;
}
