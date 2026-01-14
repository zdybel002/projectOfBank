package com.zdybel.course.autorisation;

import com.zdybel.course.dto.Request.LoginRequest; // Upewnij się, że masz tę klasę w tym pakiecie
import com.zdybel.course.dto.response.AccountResponseDTO;
import com.zdybel.course.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // ZMIANA: Oczekujemy teraz AccountResponseDTO zamiast UserResponse
            AccountResponseDTO loggedUser = userService.login(request);
            return ResponseEntity.ok(loggedUser);
        } catch (RuntimeException e) {
            // Jeśli hasło złe lub user nie istnieje - zwracamy 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}