package com.zdybel.course.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zdybel.course.dto.Request.BillRequestDTO;
import com.zdybel.course.utils.Currency;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.Random;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;

    @Column(name = "account_number", unique = true, nullable = false)
    private String iban;

    private BigDecimal amount;

    private Boolean isDefault;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_fk") // Nazwa kolumny klucza obcego w tabeli 'bill'
    private Account accountId; // Encja User

    public Bill(Account accountId, BillRequestDTO billRequestDTO) {
        this.iban = generateNumericIban();
        this.accountId = accountId;
        this.billId = billRequestDTO.getAccountId();
        this.amount = billRequestDTO.getAmount();
        this.isDefault = billRequestDTO.getDefault();
        this.currency = billRequestDTO.getCurrency();
    }

    public Bill() {
    }

    //    public Bill(BillRequestDTO billRequestDTO){
//        amount = billRequestDTO.getAmount();
//        isDefault = billRequestDTO.getDefault();
//        currency = billRequestDTO.getCurrency();
//        accountId = billRequestDTO.getAccountId();
//    }


    private String generateNumericIban(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder("PL");
        for (int i = 0; i < 26; i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", amount=" + amount +
                ", isDefault=" + isDefault +
                ", currency=" + currency +
                ", accountId=" + accountId.getAccountId() +
                '}';
    }
}
