package com.zdybel.course.service;

import com.zdybel.course.dto.history.TransactionDTO;
import com.zdybel.course.entity.Bill;
import com.zdybel.course.entity.TransactionHistory;
import com.zdybel.course.repository.BillRepository;
import com.zdybel.course.repository.TransactionHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final TransactionHistoryRepository historyRepository;
    private final BillRepository billRepository;

    public HistoryService(TransactionHistoryRepository historyRepository, BillRepository billRepository) {
        this.historyRepository = historyRepository;
        this.billRepository = billRepository;
    }

    public Page<TransactionDTO> getHistoryForIban(String iban, int pageNumber, int pageSize) {
        Bill myBill = billRepository.findByIban(iban)
                .orElseThrow(() -> new IllegalArgumentException("Konto nie istnieje"));

        // Tworzymy obiekt strony:
        // pageNumber -> numer strony (zaczynamy od 0)
        // pageSize -> 15 (ile wyników na stronę)
        // Sort.by... -> sortowanie od najnowszych
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("timestamp").descending());

        // Pobieramy stronę encji z bazy
        Page<TransactionHistory> transactionPage = historyRepository.findAllByBillId(myBill.getBillId(), pageable);

        // Mapujemy stronę encji na stronę DTO (zachowując metadane o stronach!)
        return transactionPage.map(t -> mapToDto(t, myBill));
    }

    private TransactionDTO mapToDto(TransactionHistory t, Bill myBill) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(t.getId());
        dto.setDate(t.getTimestamp());
        dto.setTitle(t.getTitle());

        // 1. Bezpieczne sprawdzenie: Czy ja jestem nadawcą?
        // Musimy sprawdzić, czy fromBill NIE JEST NULLem, zanim sprawdzimy ID
        boolean amISender = t.getFromBill() != null && t.getFromBill().getBillId().equals(myBill.getBillId());

        if (amISender) {
            // --- Pieniądze wychodzą ode mnie ---

            // Sprawdzamy, czy jest odbiorca (czy to przelew, czy płatność w sklepie/wypłata)
            if (t.getToBill() != null) {
                dto.setOtherPartyIban(t.getToBill().getIban());
            } else {
                dto.setOtherPartyIban("PŁATNOŚĆ / WYPŁATA"); // Brak odbiorcy w systemie
            }

            dto.setAmount(t.getAmount().negate()); // Na minusie
            dto.setType("WYCHODZĄCY");

        } else {
            // --- Pieniądze przychodzą do mnie ---

            // Sprawdzamy, czy jest nadawca (czy to przelew, czy wpłatomat)
            if (t.getFromBill() != null) {
                dto.setOtherPartyIban(t.getFromBill().getIban());
            } else {
                dto.setOtherPartyIban("WPŁATOMAT / PRZELEW ZEWNĘTRZNY"); // Brak nadawcy w systemie
            }

            dto.setAmount(t.getAmount()); // Na plusie
            dto.setType("PRZYCHODZĄCY");
        }

        return dto;
    }
}