package com.zdybel.course.controller;

import com.zdybel.course.dto.Request.AccountRequestDTO;
import com.zdybel.course.dto.Request.BillRequestDTO;
import com.zdybel.course.entity.Account;
import com.zdybel.course.repository.AccountRepository;
import com.zdybel.course.service.BillService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BillController {


    private BillService billService;


    public BillController(BillService billService){
        this.billService = billService;
    }

    @PostMapping("/bill")
    public Long createBill(
            @RequestBody BillRequestDTO billRequestDTO){
        return billService.createBill(billRequestDTO);
    }

}
