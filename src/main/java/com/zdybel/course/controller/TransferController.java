package com.zdybel.course.controller;


import com.zdybel.course.dto.transfer.TransferRequestDTO;
import com.zdybel.course.service.TransferService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfers")
    public ResponseEntity<String> makeTransfer(@RequestBody TransferRequestDTO requestDTO){
        try{
            transferService.transwerMoney(requestDTO);
            return ResponseEntity.ok("Przelew wykonany pomyslnie");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
