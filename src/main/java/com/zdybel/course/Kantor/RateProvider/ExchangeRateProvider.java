package com.zdybel.course.Kantor.RateProvider;

import com.zdybel.course.utils.Currency;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ExchangeRateProvider {

    BigDecimal getRate(Currency source, Currency target);
}
