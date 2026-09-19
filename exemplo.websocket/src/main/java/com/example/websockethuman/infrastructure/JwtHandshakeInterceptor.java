package com.example.websockethuman.infrastructure;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtHandshakeInterceptor implements ChannelInterceptor {

  private final String secret;

  public JwtHandshakeInterceptor(@Value("${jwt.secret}") String secret) {
    this.secret = secret;
  }

  @Override
  public Message<?> preSend(Message<?> message, org.springframework.messaging.MessageChannel channel) {
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

    if (accessor.getCommand() == StompCommand.CONNECT) {
      String authorizationHeader = accessor.getFirstNativeHeader("Authorization");

      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        throw new MessagingException("Cabeçalho Authorization ausente ou inválido");
      }

      String token = authorizationHeader.substring("Bearer ".length());

      try {
        Key signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token);
      } catch (JwtException | IllegalArgumentException e) {
        throw new MessagingException("Token JWT inválido", e);
      }
    }

    return message;
  }
}
