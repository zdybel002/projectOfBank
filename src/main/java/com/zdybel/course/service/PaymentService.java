package com.zdybel.course.service;

import com.zdybel.course.entity.Account;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private AccountService accountService;

    @Autowired
    public PaymentService(AccountService accountService){
        this.accountService = accountService;
    }

    public Object pay(Long accountId, BigDecimal paymentAmount){
        Account account = accountService.getById(accountId);
        Bill defaultBill = AccountUtils.findDefaultBill(account);
        defaultBill.setAmount(defaultBill.getAmount().subtract(paymentAmount));
        accountService.update(account);
        return "Success";
    }

    public Object deposit(Long accountId, BigDecimal paymentAmount){
        Account account = accountService.getById(accountId);
        Bill defaultBill = AccountUtils.findDefaultBill(account);
        defaultBill.setAmount(defaultBill.getAmount().add(paymentAmount));
        accountService.update(account);
        return "Success";
    }
}
