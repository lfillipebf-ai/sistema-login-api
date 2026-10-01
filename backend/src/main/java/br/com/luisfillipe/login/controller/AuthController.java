package br.com.luisfillipe.login.controller;
import br.com.luisfillipe.login.model.*;
import br.com.luisfillipe.login.repository.UserRepository;
import br.com.luisfillipe.login.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthController(UserRepository u,PasswordEncoder e,JwtService j){users=u;encoder=e;jwt=j;}

 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED)
 public Map<String,String> register(@RequestBody UserEntity input){
  if(users.existsByEmail(input.getEmail())) throw new IllegalArgumentException("E-mail já cadastrado");
  input.setPassword(encoder.encode(input.getPassword())); input.setRole(UserRole.USER);
  UserEntity saved=users.save(input);
  return Map.of("token",jwt.generate(saved));
 }

 @PostMapping("/login")
 public Map<String,String> login(@RequestBody UserEntity input){
  UserEntity user=users.findByEmail(input.getEmail()).orElseThrow(()->new IllegalArgumentException("Credenciais inválidas"));
  if(!encoder.matches(input.getPassword(),user.getPassword())) throw new IllegalArgumentException("Credenciais inválidas");
  return Map.of("token",jwt.generate(user));
 }
}
