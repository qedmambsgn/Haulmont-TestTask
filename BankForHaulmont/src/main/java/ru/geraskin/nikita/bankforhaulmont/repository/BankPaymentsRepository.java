package ru.geraskin.nikita.bankforhaulmont.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.geraskin.nikita.bankforhaulmont.model.BankPayments;

import java.util.List;
import java.util.UUID;

public interface BankPaymentsRepository extends JpaRepository<BankPayments, UUID> {

    @Query("SELECT bankPayments FROM BankPayments bankPayments WHERE bankPayments.payments.id=:id ORDER BY bankPayments.date")
    @Transactional(readOnly = true)
    List<BankPayments> findByPaymentId(@Param("id") UUID id);
}
