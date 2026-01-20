package com.zdybel.course.controller;
import com.zdybel.course.dto.history.HistoryRequestDTO;
import com.zdybel.course.dto.history.TransactionDTO;
import com.zdybel.course.service.HistoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    // Zmieniamy na POST, bo przesyłamy dane w BODY
    @PostMapping("/fetch")
    public ResponseEntity<Page<TransactionDTO>> getHistory(@RequestBody HistoryRequestDTO request) {

        // Wyciągamy dane z DTO i przekazujemy do serwisu
        // Możesz tutaj dodać zabezpieczenie, np. jeśli size == 0, to ustaw 15
        int size = request.getSize() > 0 ? request.getSize() : 15;

        Page<TransactionDTO> historyPage = historyService.getHistoryForIban(
                request.getIban(),
                request.getPage(),
                size
        );

        return ResponseEntity.ok(historyPage);
    }
}
