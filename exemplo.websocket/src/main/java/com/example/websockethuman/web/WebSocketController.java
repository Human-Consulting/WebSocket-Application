package com.example.websockethuman.web;

import com.example.websockethuman.application.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.websockethuman.application.EnviarMensagemUseCase;

@RestController
@RequestMapping("/notification")
public class WebSocketController {

  @Autowired
  private EnviarMensagemUseCase enviarMensagemUseCase;

  @Value("${internal.api.key}")
  private String internalApiKey;

  @PostMapping
  public ResponseEntity<Void> sendMessage(
      @RequestHeader(value = "X-Internal-Api-Key", required = false) String apiKey,
      @RequestBody MessageDto messageDto){
    if (apiKey == null || !apiKey.equals(internalApiKey)) {
      return ResponseEntity.status(401).build();
    }
    enviarMensagemUseCase.execute(messageDto);
    return ResponseEntity.ok().build();
  }
}
