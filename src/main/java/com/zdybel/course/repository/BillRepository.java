package com.zdybel.course.repository;

import com.zdybel.course.entity.Account;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.utils.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findByAccountId_AccountIdAndCurrency(Long accoundId, Currency currency);
    boolean existsByAccountIdAndCurrency(Account account, Currency currency);

    Optional<Bill> findByIban(String accountNumber);
}

