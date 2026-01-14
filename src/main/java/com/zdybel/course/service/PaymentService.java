package com.zdybel.course.service;

import com.zdybel.course.Kantor.Errors.InsufficientFundsException;
import com.zdybel.course.entity.Account;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.repository.BillRepository;
import com.zdybel.course.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final BillRepository billRepository;
    private AccountService accountService;

    @Autowired
    public PaymentService(AccountService accountService, BillRepository billRepository){
        this.accountService = accountService;
        this.billRepository = billRepository;
    }

    public Object pay(String billIban, BigDecimal paymentAmount) {

        // 1️⃣ Pobranie rachunku po IBAN
        Bill bill = billRepository.findByIban(billIban)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono rachunku o IBAN: " + billIban));

        // Sprawdzenie, czy środki wystarczają
        if (bill.getAmount().compareTo(paymentAmount) < 0) {
            throw new InsufficientFundsException(
                    "Brak wystarczających środków na rachunku: " + bill.getIban());
        }

        // 3️⃣ Odjęcie kwoty
        bill.setAmount(bill.getAmount().subtract(paymentAmount));

        // 4️⃣ Zapis zmian w bazie
        billRepository.saveAndFlush(bill);

        return "Success";
    }





    public Object deposit(String billIban, BigDecimal paymentAmount) {

        // 1️⃣ Pobranie rachunku po IBAN
        Bill bill = billRepository.findByIban(billIban)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono rachunku o IBAN: " + billIban));

        // 3️⃣ Dodanie kwoty do salda
        if (bill.getAmount() == null) {
            bill.setAmount(BigDecimal.ZERO);
        }
        bill.setAmount(bill.getAmount().add(paymentAmount));

        // 4️⃣ Zapis zmian w bazie
        billRepository.saveAndFlush(bill);

        return "Success";
    }

}
