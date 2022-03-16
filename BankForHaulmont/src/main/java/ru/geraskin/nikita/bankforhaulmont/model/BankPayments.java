package ru.geraskin.nikita.bankforhaulmont.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bank_payments")
public class BankPayments {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "bank_payments_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "fee")
    private float fee;

    @ManyToOne
    @JoinColumn(name = "payments_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Payments payments;

    @Column(name = "percent_fee")
    private float percentFee;

    @Column(name = "body_fee")
    private float bodyFee;

    @Column(name = "over_price")
    private float overPrice;


    public void setOverPrice(float overPrice) {
        this.overPrice = overPrice;
    }

    public float getOverPrice() {
        return overPrice;
    }

    public void setBodyFee(float bodyFee) {
        this.bodyFee = bodyFee;
    }

    public void setPercentFee(float percentFee) {
        this.percentFee = percentFee;
    }

    public float getBodyFee() {
        return bodyFee;
    }

    public float getPercentFee() {
        return percentFee;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }

    public Payments getPayments() {
        return payments;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getFee() {
        return fee;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }
}
