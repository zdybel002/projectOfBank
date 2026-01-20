package com.zdybel.course.entity;

import com.zdybel.course.utils.Currency;
import com.zdybel.course.utils.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_bill_id")
    private Bill fromBill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_bill_id")
    private Bill toBill;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType type;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime timestamp;

    public TransactionHistory() {
    }

    public TransactionHistory(Bill fromBill, Bill toBill, BigDecimal amount, Currency currency, String title, TransactionType type) {
        this.fromBill = fromBill;
        this.toBill = toBill;
        this.amount = amount;
        this.currency = currency;
        this.title = title;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Bill getFromBill() {
        return fromBill;
    }

    public Bill getToBill() {
        return toBill;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getTitle() {
        return title;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
