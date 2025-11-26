package com.zdybel.course.entity;

import com.zdybel.course.dto.transfer.Request.BillRequestDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;

    private BigDecimal amount;

    private Boolean isDefault;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id") // Nazwa kolumny klucza obcego w tabeli 'bill'
    private Account account; // Encja User

    public Bill(BigDecimal amount, Boolean isDefault, Account account) {
        this.amount = amount;
        this.isDefault = isDefault;
        this.account = account;
    }

    public Bill(BillRequestDTO billRequestDTO){
        amount = billRequestDTO.getAmount();
        isDefault = billRequestDTO.getDefault();
    }

    public Bill(){

    }

    public Long getBillId() {
        return billId;
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

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", amount=" + amount +
                ", isDefault=" + isDefault +
                '}';
    }
}
