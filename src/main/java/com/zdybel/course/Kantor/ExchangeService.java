package com.zdybel.course.Kantor;

import com.zdybel.course.Kantor.Errors.InsufficientFundsException;
import com.zdybel.course.Kantor.RateProvider.ExchangeRateProvider;
import com.zdybel.course.dto.transfer.ExchangeRequestDTO;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.repository.BillRepository;
import com.zdybel.course.utils.Currency;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeService {

    private final BillRepository billRepository;
    private final ExchangeRateProvider exchangeRateProvider;

    @Autowired
    public ExchangeService(BillRepository billRepository, ExchangeRateProvider exchangeRateProvider){
        this.billRepository = billRepository;
        this.exchangeRateProvider = exchangeRateProvider;
    }


    @Transactional
    public Object exchangeFunds(
            ExchangeRequestDTO exchangeRequestDTO
            ){


        // Walidacja kwoty

        if(exchangeRequestDTO.getAmountToExchange() == null ||
                exchangeRequestDTO.getAmountToExchange().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Kwota wymiany powina być dodatnia");
        }

        // walidacja roznych walut

        if(exchangeRequestDTO.getSourceCurrency().equals(exchangeRequestDTO.getTargetCurrency())){
            throw new IllegalArgumentException("Waluta zrodlowa i docelowa powinni być róźne");
        }

        // Pobranie rachunku docelowego
        Bill sourceBill = billRepository
                .findByAccountId_AccountIdAndCurrency(exchangeRequestDTO.getAccountId(),exchangeRequestDTO.getSourceCurrency())
                .orElseThrow(() -> new RuntimeException
                        ("Bląd: nie znaleziono rachunku zrodlowego w walucie" + exchangeRequestDTO.getSourceCurrency()) );


//      Sprawdzanie czy wystarcza srodków na koncie
        if(sourceBill.getAmount().compareTo(exchangeRequestDTO.getAmountToExchange()) < 0){
            throw new InsufficientFundsException("Niewystarczające srodki na rachunku"
                    + exchangeRequestDTO.getSourceCurrency());
        }


//        Pobranie docelowego konta
        Bill targetBill = billRepository
                .findByAccountId_AccountIdAndCurrency(exchangeRequestDTO.getAccountId(),exchangeRequestDTO.getTargetCurrency())
                .orElseThrow(() -> new RuntimeException
                        ("Bląd: nie znaleziono rachunku docelowego w walucie" + exchangeRequestDTO.getTargetCurrency()) );


//        pobranie kursu wymiany
        BigDecimal rate = exchangeRateProvider.getRate(
                exchangeRequestDTO.getSourceCurrency(),
                exchangeRequestDTO.getTargetCurrency());


//        Oblicanie kwoty docelowej
        BigDecimal targetAmount = exchangeRequestDTO.getAmountToExchange().multiply(rate).setScale(2,
                RoundingMode.HALF_UP);

//        Zmniejszanie rachunku zrodlowego
        BigDecimal newSourceAmount = sourceBill.getAmount().subtract(exchangeRequestDTO.getAmountToExchange());
        sourceBill.setAmount(newSourceAmount);

//        Zwiekszenie rachunku docelowego
        BigDecimal newTargetAmount = targetBill.getAmount().add(targetAmount);
        targetBill.setAmount(newTargetAmount);

        billRepository.save(sourceBill);
        billRepository.save(targetBill);

        return null;

    }


}
