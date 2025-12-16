package com.zdybel.course.dto.response;

import com.zdybel.course.entity.Bill;
import com.zdybel.course.utils.Currency;

import java.math.BigDecimal;

public class BillResponseDTO {

    private Long billId;

    private BigDecimal amount;

    private Boolean isDefault;

    private Currency currency;;


    public BillResponseDTO(Bill bill){
        billId = bill.getBillId();
        amount = bill.getAmount();
        isDefault = bill.getDefault();
        currency = bill.getCurrency();
    }

    public BillResponseDTO(Long billId, BigDecimal amount, Boolean isDefault) {
        this.billId = billId;
        this.amount = amount;
        this.isDefault = isDefault;
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
}
