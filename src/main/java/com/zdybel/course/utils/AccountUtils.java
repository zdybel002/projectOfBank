package com.zdybel.course.utils;

import com.zdybel.course.entity.Account;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.exceptions.NotDefaultBillException;

public class AccountUtils {

    public static Bill findDefaultBill(Account accountFrom) {
        return accountFrom.getBills().stream()
                .filter(Bill::getDefault)
                .findAny().orElseThrow(() ->
                        new NotDefaultBillException
                                ("Unable to find default bill for account with id: "
                                        + accountFrom.getAccountId()));
    }
}
