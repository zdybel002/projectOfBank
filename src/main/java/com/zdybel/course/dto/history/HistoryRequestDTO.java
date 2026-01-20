package com.zdybel.course.dto.history;

public class HistoryRequestDTO {
    private String iban;
    private int page;
    private int size;

    // Domyślny konstruktor
    public HistoryRequestDTO() {
    }

    // Gettery i Settery
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
