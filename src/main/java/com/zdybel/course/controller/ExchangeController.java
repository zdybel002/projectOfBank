package com.zdybel.course.controller;


import com.zdybel.course.Kantor.ExchangeService;
import com.zdybel.course.dto.transfer.ExchangeRequestDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping("/exchange")
    public Object exchange(@RequestBody ExchangeRequestDTO exchangeRequestDTO){
        return exchangeService.exchangeFunds(exchangeRequestDTO);

    }


//    public Object pay(@RequestBody PaymentRequestDTO paymentRequestDTO){
//        return paymentService.pay(paymentRequestDTO.getAccountId(), paymentRequestDTO.getAmount());
//    }


//    @PostMapping("/transfers")
//    public Object transfer(@RequestBody TransferRequestDTO transferRequestDTO) {
//        return transferService.trasfer(transferRequestDTO.getAccountIdFrom(),
//                transferRequestDTO.getAccountIdTo(), transferRequestDTO.getAmount());
//    }
}
