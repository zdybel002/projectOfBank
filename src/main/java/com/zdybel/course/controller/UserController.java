package com.zdybel.course.controller;

import com.zdybel.course.dto.Request.AccoundRegistrationRequest; // Zachowana pisownia z Twojego kodu
import com.zdybel.course.dto.response.AccountResponseDTO;
import com.zdybel.course.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Możesz zmienić na "/api/accounts" jeśli wolisz
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createUser(@RequestBody AccoundRegistrationRequest request) {
        // Zmieniono typy zmiennych na AccountResponseDTO oraz AccoundRegistrationRequest
        AccountResponseDTO newAccount = userService.registerUser(request);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getUsers() {
        // Metoda serwisu zwraca teraz List<AccountResponseDTO>
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
