package com.zdybel.course.Kantor.RateProvider;

import com.zdybel.course.utils.Currency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class SimpleExchangeRateProvider implements ExchangeRateProvider{

    @Override
    public BigDecimal getRate(Currency source, Currency target) {

        if (source == Currency.USD && target == Currency.EUR){
            return new BigDecimal("0.92");
        }
        if (source == Currency.EUR && target == Currency.PLN){
            return new BigDecimal("4.35");
        }
        if (source == Currency.PLN && target == Currency.EUR){
            return new BigDecimal("0.24");
        }
        if (source == Currency.PLN && target == Currency.USD){
            return new BigDecimal("0.28");
        }
        if (source == Currency.EUR && target == Currency.USD){
            return new BigDecimal("1.17");
        }

        throw new IllegalArgumentException
                ("Nieznany kurs wymiany dla " + source + " na " + target);

    }
}
