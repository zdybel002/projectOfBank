package com.zdybel.course.service;

import com.zdybel.course.dto.Request.AccoundRegistrationRequest;
import com.zdybel.course.dto.Request.LoginRequest;
import com.zdybel.course.dto.response.AccountResponseDTO;
import com.zdybel.course.dto.response.BillResponseDTO;
import com.zdybel.course.entity.Account;
import com.zdybel.course.entity.Role;
import com.zdybel.course.repository.AccountRepository;
import com.zdybel.course.repository.RoleReposetory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final AccountRepository accountRepository;
    private final RoleReposetory roleRepository;

    // Konstruktor (Spring sam wstrzyknie zależności)
    public UserService(AccountRepository accountRepository, RoleReposetory roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Rejestracja nowego użytkownika
     */
    public AccountResponseDTO registerUser(AccoundRegistrationRequest request) {
        // 1. Ustalanie roli (Domyślnie USER, chyba że podano inną i istnieje w bazie)
        String roleName = (request.getRoleName() != null && !request.getRoleName().isEmpty())
                ? request.getRoleName()
                : "USER";

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rola nie znaleziona: " + roleName));

        // 2. Tworzenie encji Account
        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setName(request.getName());
        account.setPassword(request.getPassword()); // Pamiętaj: w przyszłości warto to haszować (BCrypt)
        account.setRole(role);

        // Uwaga: Lista bills jest domyślnie pusta lub null w nowym obiekcie, co jest OK.

        // 3. Zapis do bazy
        Account savedAccount = accountRepository.save(account);

        // 4. Zwracanie DTO (Konstruktor w DTO sam obsłuży pustą listę bills)
        return new AccountResponseDTO(savedAccount);
    }

    /**
     * Logowanie użytkownika
     * Adnotacja @Transactional jest tutaj KLUCZOWA, jeśli Bills są ładowane leniwie (Lazy),
     * aby uniknąć błędu LazyInitializationException przy mapowaniu wewnątrz DTO.
     */
    @Transactional
    public AccountResponseDTO login(LoginRequest request) {
        // 1. Szukamy konta po emailu
        Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Nieprawidłowy login lub hasło"));

        // 2. Sprawdzamy hasło
        if (!account.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Nieprawidłowy login lub hasło");
        }

        // 3. Zwracamy DTO
        // Konstruktor AccountResponseDTO automatycznie przemapuje List<Bill> na List<BillResponseDTO>
        return new AccountResponseDTO(account);
    }

    /**
     * Pobranie wszystkich użytkowników
     */
    @Transactional(readOnly = true) // Opcjonalnie: optymalizacja dla odczytu
    public List<AccountResponseDTO> getAllUsers() {
        return accountRepository.findAll().stream()
                .map(AccountResponseDTO::new) // Używamy referencji do konstruktora
                .collect(Collectors.toList());
    }
}