package com.zdybel.course.dto.response;

import com.zdybel.course.entity.Account;

import java.util.List;

// W pakiecie DTO
public class AllAccountsResponse {


    private List<Account> accounts;

    // Konstruktor, Gettery i Settery
    public AllAccountsResponse(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
    // ...
}


