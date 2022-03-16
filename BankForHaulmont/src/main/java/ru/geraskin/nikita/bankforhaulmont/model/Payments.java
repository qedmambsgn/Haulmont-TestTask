package ru.geraskin.nikita.bankforhaulmont.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "date_payment")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "summa")
    private float summa;

    @Column(name = "period")
    private Integer period;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bank bank;

    @OneToMany(mappedBy = "payments", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BankPayments> bankPayments = new HashSet<>();

    public Set<BankPayments> getBankPayments() {
        return bankPayments;
    }

    public void setBankPayments(Set<BankPayments> bankPayments) {
        this.bankPayments = bankPayments;
    }

    public Payments(){
        this.date = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public float getSumma() {
        return summa;
    }

    public LocalDate getDate() {
        return date;
    }

    public Bank getBank() {
        return bank;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSumma(float summa) {
        this.summa = summa;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
