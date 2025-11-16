package com.example.websockethuman.web;

import com.example.websockethuman.application.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.websockethuman.application.EnviarMensagemUseCase;

@RestController
@RequestMapping("/notification")
public class WebSocketController {

  @Autowired
  private EnviarMensagemUseCase enviarMensagemUseCase;
  
  @PostMapping
  @CrossOrigin("*")
  public void sendMessage(@RequestBody MessageDto messageDto){
    enviarMensagemUseCase.execute(messageDto);
  }
}
