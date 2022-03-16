package ru.geraskin.nikita.bankforhaulmont.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geraskin.nikita.bankforhaulmont.model.Credit;

import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {

}
