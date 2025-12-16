package com.zdybel.course.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PaymentRequestDTO {

//    String billIban, BigDecimal paymentAmount

    @JsonProperty("bill_iban")
    private String billIban;

    @JsonProperty("amount")
    private BigDecimal paymentAmount;

    public String getBillIban() {
        return billIban;
    }

    public void setBillIban(String billIban) {
        this.billIban = billIban;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
