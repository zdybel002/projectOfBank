package com.zdybel.course.service;

import com.zdybel.course.Kantor.Errors.InsufficientFundsException;
import com.zdybel.course.dto.history.TransactionDTO;
import com.zdybel.course.entity.Account;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.entity.TransactionHistory;
import com.zdybel.course.repository.BillRepository;
import com.zdybel.course.repository.TransactionHistoryRepository;
import com.zdybel.course.utils.AccountUtils;
import com.zdybel.course.utils.TransactionType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class PaymentService {

    private final BillRepository billRepository;
    private final TransactionHistoryRepository historyRepository;
    private final AccountService accountService;

    @Autowired
    public PaymentService(AccountService accountService,
                          BillRepository billRepository,
                          TransactionHistoryRepository historyRepository) {
        this.accountService = accountService;
        this.billRepository = billRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public TransactionDTO pay(String billIban, BigDecimal paymentAmount) {
        // ... (logika pobierania i sprawdzania salda bez zmian) ...
        Bill bill = billRepository.findByIban(billIban)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono rachunku: " + billIban));

        if (bill.getAmount().compareTo(paymentAmount) < 0) {
            throw new InsufficientFundsException("Brak środków");
        }

        bill.setAmount(bill.getAmount().subtract(paymentAmount));
        billRepository.save(bill);

        // --- POPRAWKA: Użycie konstruktora ---
        TransactionHistory history = new TransactionHistory(
                bill,                                   // fromBill (pieniądze wychodzą z tego konta)
                null,                                   // toBill (brak odbiorcy w systemie)
                paymentAmount,                          // amount
                bill.getCurrency(),                     // currency
                "Płatność zewnętrzna / Wypłata",        // title
                TransactionType.TRANSFER_OUTCOMING       // type
        );

        TransactionHistory savedHistory = historyRepository.save(history);

        return new TransactionDTO(
                savedHistory.getId(),
                "Płatność zewnętrzna",
                paymentAmount.negate(),
                "PŁATNOŚĆ",
                LocalDateTime.now(),
                savedHistory.getTitle()
        );
    }

    @Transactional
    public TransactionDTO deposit(String billIban, BigDecimal paymentAmount) {
        // ... (logika pobierania rachunku bez zmian) ...
        Bill bill = billRepository.findByIban(billIban)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono rachunku: " + billIban));

        if (bill.getAmount() == null) bill.setAmount(BigDecimal.ZERO);
        bill.setAmount(bill.getAmount().add(paymentAmount));
        billRepository.save(bill);

        // --- POPRAWKA: Użycie konstruktora ---
        TransactionHistory history = new TransactionHistory(
                null,                                   // fromBill (brak nadawcy w systemie)
                bill,                                   // toBill (pieniądze wchodzą na to konto)
                paymentAmount,                          // amount
                bill.getCurrency(),                     // currency
                "Wpłata środków (Wpłatomat/Oddział)",   // title
                TransactionType.TRANSFER_INCOMING       // type
        );

        TransactionHistory savedHistory = historyRepository.save(history);

        return new TransactionDTO(
                savedHistory.getId(),
                "Wpłatomat",
                paymentAmount,
                "WPŁATA",
                LocalDateTime.now(),
                savedHistory.getTitle()
        );
    }
}
