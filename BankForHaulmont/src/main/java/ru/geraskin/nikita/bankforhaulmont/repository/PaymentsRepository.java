package ru.geraskin.nikita.bankforhaulmont.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geraskin.nikita.bankforhaulmont.model.Payments;

import java.util.UUID;

public interface PaymentsRepository extends JpaRepository<Payments, UUID> {
}
