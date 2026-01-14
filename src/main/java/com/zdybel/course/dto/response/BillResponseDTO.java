package com.zdybel.course.dto.response;

import com.zdybel.course.entity.Bill;
import com.zdybel.course.utils.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class BillResponseDTO {

    private Long billId;
    private String iban;        // Dodałem, bo masz to w encji
    private BigDecimal amount;
    private Boolean isDefault;
    private Currency currency;

    // --- KLUCZOWY KONSTRUKTOR ---
    public BillResponseDTO(Bill bill) {
        this.billId = bill.getBillId();
        this.iban = bill.getIban();
        this.amount = bill.getAmount();

        // Uwaga: W Twojej encji getter nazywa się getDefault(), więc tak go wołamy:
        this.isDefault = bill.getDefault();

        this.currency = bill.getCurrency();
    }
}