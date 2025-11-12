package com.zdybel.course.controller;

import com.zdybel.course.dto.AccountRequestDTO;
import com.zdybel.course.dto.AccountResponseDTO;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.service.AccountService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
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



    @PostMapping("/accounts")
    public Long create(@RequestBody AccountRequestDTO accountRequestDTO){
        return accountService.save(accountRequestDTO.getName(),
                accountRequestDTO.getEmail(), accountRequestDTO.getBills().stream()
                        .map(billRequestDTO -> new Bill(billRequestDTO.getAmount(),
                                billRequestDTO.getDefault())).collect(Collectors.toList()));
    }

}
