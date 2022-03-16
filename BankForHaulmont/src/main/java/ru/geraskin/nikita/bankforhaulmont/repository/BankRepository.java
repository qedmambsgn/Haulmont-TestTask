package ru.geraskin.nikita.bankforhaulmont.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.geraskin.nikita.bankforhaulmont.model.Bank;
import ru.geraskin.nikita.bankforhaulmont.model.BankPayments;
import ru.geraskin.nikita.bankforhaulmont.model.Credit;

import java.util.List;
import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, UUID> {

}
