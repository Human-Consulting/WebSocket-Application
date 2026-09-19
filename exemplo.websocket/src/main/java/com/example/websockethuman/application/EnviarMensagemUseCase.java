package com.example.websockethuman.application;

import com.example.websockethuman.application.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnviarMensagemUseCase {

  private static final Logger logger = LoggerFactory.getLogger(EnviarMensagemUseCase.class);

  private final SimpMessagingTemplate template;

  public void execute(MessageDto message) {
    if (message.getIdSala() == null) {
      throw new IllegalArgumentException("idSala não pode ser nulo");
    }
    logger.info("""
            Chegou mensagem:
            idSala: %d
            Conteúdo: %s
            Usuário: %s
            """.formatted(message.getIdSala(), message.getConteudo(), message.getNome()));
    template.convertAndSend("/topic/message/" + message.getIdSala(), message);
  }
}
