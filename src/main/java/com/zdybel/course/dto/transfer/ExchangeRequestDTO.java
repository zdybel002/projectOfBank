package com.zdybel.course.dto.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zdybel.course.utils.Currency;

import java.math.BigDecimal;

public class ExchangeRequestDTO {

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("sourceCurrency")
    private Currency sourceCurrency;

    @JsonProperty("targetCurrency")
    private Currency targetCurrency;

    @JsonProperty("amountToExchange")
    private BigDecimal amountToExchange;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getAmountToExchange() {
        return amountToExchange;
    }

    public void setAmountToExchange(BigDecimal amountToExchange) {
        this.amountToExchange = amountToExchange;
    }
}
