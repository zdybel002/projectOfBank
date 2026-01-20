    package com.zdybel.course.service;

    import com.zdybel.course.dto.transfer.TransferRequestDTO;
    import com.zdybel.course.entity.Bill;
    import com.zdybel.course.entity.TransactionHistory;
    import com.zdybel.course.repository.BillRepository;
    import com.zdybel.course.repository.TransactionHistoryRepository;
    import com.zdybel.course.utils.TransactionType;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.math.BigDecimal;

    @Service
    public class TransferService {

        private BillRepository billRepository;
        private final TransactionHistoryRepository historyRepository;

        public TransferService(BillRepository billRepository, TransactionHistoryRepository transactionHistoryRepository, TransactionHistoryRepository historyRepository){
            this.billRepository = billRepository;
            this.historyRepository = historyRepository;
        }


        @Transactional
        public void transwerMoney(TransferRequestDTO requestDTO) {
            // 1. Walidacja podstawowa
            if (requestDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Kwota przelewu powinna być dodatnia");
            }
            if (requestDTO.getIbanIdFrom().equals(requestDTO.getIbanIdTo())) {
                throw new IllegalArgumentException("Nie można wykonać przelewu na to samo konto");
            }

            // 2. Pobranie kont
            Bill senderBill = billRepository.findByIban(requestDTO.getIbanIdFrom())
                    .orElseThrow(() -> new IllegalArgumentException("Konto nadawcy nie istnieje"));

            Bill receiverBill = billRepository.findByIban(requestDTO.getIbanIdTo())
                    .orElseThrow(() -> new IllegalArgumentException("Konto odbiorcy nie istnieje"));

            // 3. Sprawdzenie waluty
            if (senderBill.getCurrency() != receiverBill.getCurrency()) {
                throw new IllegalArgumentException("Przewalutowanie nie jest obslugiwane: " +
                        "Konta muszą być w tej samej walucie");
            }

            // --- POPRAWKA: Sprawdzenie salda PRZED wykonaniem operacji ---
            // Sprawdzamy, czy saldo nadawcy jest mniejsze niż kwota przelewu z DTO
            if (senderBill.getAmount().compareTo(requestDTO.getAmount()) < 0) {
                throw new IllegalStateException("Niewystarczające środki na koncie nadawcy");
            }

            // --- POPRAWKA: Wykonanie operacji na kwocie z DTO ---
            BigDecimal transferAmount = requestDTO.getAmount();

            // Odejmujemy kwotę przelewu od nadawcy
            senderBill.setAmount(senderBill.getAmount().subtract(transferAmount));

            // Dodajemy kwotę przelewu do odbiorcy
            receiverBill.setAmount(receiverBill.getAmount().add(transferAmount));

                                                                                                TransactionHistory historyEntry = new TransactionHistory(
                    senderBill,
                    receiverBill,
                    requestDTO.getAmount(),
                    senderBill.getCurrency(),
                    "Przelew srodków",
                    TransactionType.TRANSFER_OUTCOMING
            );

            historyRepository.save(historyEntry);

            // Zapis zmian
            billRepository.save(senderBill);
            billRepository.save(receiverBill);
        }





    }
