package com.zdybel.course.dto.history;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private Long id;
    private String otherPartyIban; // IBAN drugiej strony (dla nadawcy - odbiorca, dla odbiorcy - nadawca)
    private BigDecimal amount;
    private String type; // Np. "PRZYCHODZĄCY" / "WYCHODZĄCY"
    private LocalDateTime date;
    private String title;

    public TransactionDTO(Long id, String otherPartyIban, BigDecimal amount, String type, LocalDateTime date, String title) {
        this.id = id;
        this.otherPartyIban = otherPartyIban;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.title = title;
    }

    // 2. DODAJ TO: Pusty konstruktor
    public TransactionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtherPartyIban() {
        return otherPartyIban;
    }

    public void setOtherPartyIban(String otherPartyIban) {
        this.otherPartyIban = otherPartyIban;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}