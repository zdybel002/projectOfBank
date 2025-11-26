package com.zdybel.course.dto.transfer.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zdybel.course.entity.Account;

import java.math.BigDecimal;

public class BillRequestDTO {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("isDefault")
    private Boolean isDefault;

    @JsonProperty("id")
    private Long AccountId;


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
}
