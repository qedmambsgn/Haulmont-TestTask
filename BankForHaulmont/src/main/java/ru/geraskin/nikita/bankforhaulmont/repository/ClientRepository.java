package ru.geraskin.nikita.bankforhaulmont.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geraskin.nikita.bankforhaulmont.model.Client;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

}
