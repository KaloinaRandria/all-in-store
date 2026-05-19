package mg.working.allinstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")  // Pas de /api ici car il est déjà dans context-path
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<Map<String, Object>> hello() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello World depuis Spring Boot!");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "success");
        response.put("info", "Spring Security est configuré et l'accès est autorisé !");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Cet endpoint est public - Tout le monde peut y accéder");
    }
}