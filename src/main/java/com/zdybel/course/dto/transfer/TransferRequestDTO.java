package com.zdybel.course.dto.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransferRequestDTO {

    @JsonProperty("iban_id_from")
    private String ibanIdFrom;

    @JsonProperty("iban_id_to")
    private String ibanIdTo;

    @JsonProperty("amount")
    private BigDecimal amount;


    public String getIbanIdFrom() {
        return ibanIdFrom;
    }

    public void setIbanIdFrom(String ibanIdFrom) {
        this.ibanIdFrom = ibanIdFrom;
    }

    public String getIbanIdTo() {
        return ibanIdTo;
    }

    public void setIbanIdTo(String ibanIdTo) {
        this.ibanIdTo = ibanIdTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
