package com.zdybel.course.controller;

import com.zdybel.course.dto.response.AccountResponseDTO;
import com.zdybel.course.dto.Request.AccountRequestDTO;
import com.zdybel.course.dto.response.AllAccountsResponse;
import com.zdybel.course.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }



    @GetMapping("/accounts/{accountId}")
    public AccountResponseDTO getById(@PathVariable Long accountId){
        return new AccountResponseDTO(accountService.getById(accountId));
    }



    @PostMapping("/account")
    public Long createAccount(@RequestBody AccountRequestDTO accountRequestDTO){
        return accountService.save(accountRequestDTO);
    }


    @GetMapping("accounts")
    public ResponseEntity<AllAccountsResponse> getAllAccounts() {

        AllAccountsResponse response = accountService.getAllAccounts();

        return ResponseEntity.ok(response);
    }


}
