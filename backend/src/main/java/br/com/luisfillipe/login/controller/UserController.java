package br.com.luisfillipe.login.controller;

import br.com.luisfillipe.login.model.UserEntity;
import br.com.luisfillipe.login.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository users;

    public UserController(UserRepository users) {
        this.users = users;
    }

    @GetMapping
    public List<Map<String, Object>> list() {
        return users.findAll().stream().map(this::toMap).toList();
    }

    @GetMapping("/me")
    public Map<String, Object> me(Authentication auth) {
        UserEntity u = users.findByEmail(auth.getName()).orElseThrow();
        return toMap(u);
    }

    private Map<String, Object> toMap(UserEntity u) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", u.getId());
        result.put("name", u.getName());
        result.put("email", u.getEmail());
        result.put("role", u.getRole());
        return result;
    }
}
