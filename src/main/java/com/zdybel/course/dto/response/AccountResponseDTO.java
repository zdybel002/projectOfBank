package com.zdybel.course.dto.response;

import com.zdybel.course.entity.Account;
import lombok.Data;

import java.util.List;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
public class AccountResponseDTO {

    private Long accountId;
    private String name;
    private String email;
    private List<BillResponseDTO> bills;
    private String role;

    public AccountResponseDTO(Account account) {
        this.accountId = account.getAccountId();
        this.name = account.getName();
        this.email = account.getEmail();

        // Pobieranie nazwy roli (zabezpieczenie przed null)
        if (account.getRole() != null) {
            this.role = account.getRole().getName();
        }

        // --- MAPOWANIE RACHUNKÓW ---
        // To zadziała, bo w BillResponseDTO mamy już konstruktor przyjmujący 'Bill'
        if (account.getBills() != null) {
            this.bills = account.getBills().stream()
                    .map(BillResponseDTO::new)
                    .collect(Collectors.toList());
        } else {
            this.bills = new ArrayList<>();
        }
    }
}