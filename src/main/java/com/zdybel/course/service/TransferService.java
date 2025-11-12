package com.zdybel.course.service;

import com.zdybel.course.entity.Account;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.exceptions.NotDefaultBillException;
import com.zdybel.course.repository.AccountRepository;
import com.zdybel.course.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final AccountService accountService;


    @Autowired
    public TransferService(AccountService accountService) {
        this.accountService = accountService;
    }



    public Object trasfer(Long accountIdFrom, Long accountIdTo, BigDecimal amount) {
        Account accountFrom = accountService.getById(accountIdFrom);
        Account accountTo = accountService.getById(accountIdTo);

        Bill billFrom = AccountUtils.findDefaultBill(accountFrom);
        Bill billTo = AccountUtils.findDefaultBill(accountTo);

        billFrom.setAmount(billFrom.getAmount().subtract(amount));
        billTo.setAmount(billTo.getAmount().add(amount));

        accountService.update(accountFrom);
        accountService.update(accountTo);
        return "Success";

    }



}
