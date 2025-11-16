package com.example.websockethuman.application;

import com.example.websockethuman.application.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnviarMensagemUseCase {

  private final SimpMessagingTemplate template;
  
  public void execute(MessageDto message) {
    System.out.println("""
            Chegou mensagem:
            idSala: %d
            Conteúdo: %s
            Usuário: %s
            """.formatted(message.getIdSala(), message.getConteudo(), message.getNome()));
    template.convertAndSend("/topic/message/" + message.getIdSala(), message);
  }
}
