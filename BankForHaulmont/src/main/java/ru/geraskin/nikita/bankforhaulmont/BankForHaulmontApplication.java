package ru.geraskin.nikita.bankforhaulmont;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.geraskin.nikita.bankforhaulmont.model.Client;

@SpringBootApplication
public class BankForHaulmontApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankForHaulmontApplication.class, args);
    }

}
