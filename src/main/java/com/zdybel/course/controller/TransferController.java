package com.zdybel.course.controller;


import com.zdybel.course.dto.transfer.TransferRequestDTO;
import com.zdybel.course.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfers")
    public Object transfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        return transferService.trasfer(transferRequestDTO.getAccountIdFrom(),
                transferRequestDTO.getAccountIdTo(), transferRequestDTO.getAmount());
    }
}
