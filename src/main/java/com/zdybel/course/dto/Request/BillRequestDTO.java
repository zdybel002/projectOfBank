package com.zdybel.course.dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zdybel.course.utils.Currency;

import java.math.BigDecimal;

public class BillRequestDTO {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("isDefault")
    private Boolean isDefault;

    @JsonProperty("accountId")
    private Long AccountId;

    @JsonProperty("currency")
    private Currency currency;


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

    public Long getAccountId() {
        return AccountId;
    }

    public void setAccountId(Long accountId) {
        AccountId = accountId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
