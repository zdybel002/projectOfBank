package com.zdybel.course.service;

import com.zdybel.course.dto.Request.AccountRequestDTO;
import com.zdybel.course.dto.response.AllAccountsResponse;
import com.zdybel.course.entity.Account;
import com.zdybel.course.exceptions.AccountNotFoundException;
import com.zdybel.course.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {



    private final AccountRepository accountRepository;


    @Autowired
    public AccountService (AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }



    public Long save(AccountRequestDTO requestDTO) {

        String name = requestDTO.getName();
        String email = requestDTO.getEmail();

        Account account = new Account( name, email);

        return accountRepository.save(account).getAccountId();
    }

    public Account getById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new AccountNotFoundException("Unable to find account with id:" + accountId));
    }

    public AllAccountsResponse getAllAccounts() {


        List<Account> accounts = accountRepository.findAll();

        return new AllAccountsResponse(accounts);
    }


    public Account update(Account account){

        return accountRepository.save(account);
    }




}
