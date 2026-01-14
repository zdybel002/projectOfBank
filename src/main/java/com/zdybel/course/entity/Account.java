package com.zdybel.course.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    private String password;


    @OneToMany(
            mappedBy = "accountId", // Wskazuje na pole 'account' w klasie Bill
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST // Opcjonalnie: Użyj tylko PERSIST
    )
    private List<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private  Role role;

    public Account(String name, String email, List<Bill> bills) {
        this.name = name;
        this.email = email;
        this.bills = bills;
    }
    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Account() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bills=" + bills +
                '}';
    }
}
