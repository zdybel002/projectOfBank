package com.zdybel.course.repository;

import com.zdybel.course.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    // Zmieniamy List na Page oraz dodajemy parametr Pageable
    @Query("SELECT t FROM TransactionHistory t " +
            "WHERE t.fromBill.id = :billId OR t.toBill.id = :billId")
    Page<TransactionHistory> findAllByBillId(@Param("billId") Long billId, Pageable pageable);
}


