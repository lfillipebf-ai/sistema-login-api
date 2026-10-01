package br.com.luisfillipe.login.service;
import br.com.luisfillipe.login.model.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
 private final SecretKey key;
 public JwtService(@Value("\${jwt.secret}") String secret){key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));}
 public String generate(UserEntity user){
  Date now=new Date();
  return Jwts.builder().subject(user.getEmail()).claim("role",user.getRole().name())
   .issuedAt(now).expiration(new Date(now.getTime()+86400000)).signWith(key).compact();
 }
 public String extractEmail(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();}
}
