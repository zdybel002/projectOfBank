package com.zdybel.course.service;

import com.zdybel.course.Kantor.Errors.DuplicateCurrencyException;
import com.zdybel.course.dto.Request.BillRequestDTO;
import com.zdybel.course.entity.Account;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.repository.AccountRepository;
import com.zdybel.course.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final AccountRepository accountRepository;

    public BillService(BillRepository billRepository, AccountRepository accountRepository) {
        this.billRepository = billRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Long createBill(BillRequestDTO requestDTO) {
        // Załóżmy, że metoda w Repository to findById(accountId)
        Account account = accountRepository.findById(requestDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono konta o ID: " +
                        requestDTO.getAccountId()));
        // Zastąp RuntimeException swoim niestandardowym wyjątkiem (np. AccountNotFoundException)

        boolean exists = billRepository.existsByAccountIdAndCurrency(account, requestDTO.getCurrency());
        // Spra
        // wdzenie, czy użytkownik ma już konto w tej walucie
        if (exists) {
            throw new DuplicateCurrencyException(
                    "Użytkownik ma już konto w walucie: " + requestDTO.getCurrency());
        }


        Bill bill = new Bill(account, requestDTO);
        return billRepository.save(bill).getBillId();
    }
}
