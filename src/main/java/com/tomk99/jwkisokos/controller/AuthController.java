package com.tomk99.jwkisokos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/status")
    public ResponseEntity<Map<String, Boolean>> getAuthStatus(Authentication authentication) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("authenticated", authentication != null && authentication.isAuthenticated());
        return ResponseEntity.ok(response);
    }
}
