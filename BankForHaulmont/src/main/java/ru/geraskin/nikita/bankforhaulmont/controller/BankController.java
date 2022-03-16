package ru.geraskin.nikita.bankforhaulmont.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geraskin.nikita.bankforhaulmont.model.Bank;
import ru.geraskin.nikita.bankforhaulmont.model.Client;
import ru.geraskin.nikita.bankforhaulmont.model.Credit;
import ru.geraskin.nikita.bankforhaulmont.repository.BankRepository;
import ru.geraskin.nikita.bankforhaulmont.repository.ClientRepository;
import ru.geraskin.nikita.bankforhaulmont.repository.CreditRepository;

import java.util.List;

@Controller
public class BankController {

    private final ClientRepository clientRepository;

    private final CreditRepository creditRepository;

    private final BankRepository bankRepository;

    @Autowired
    public BankController(ClientRepository clientRepository,
                          CreditRepository creditRepository,
                          BankRepository bankRepository) {
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.bankRepository = bankRepository;
    }

    @GetMapping("/banks")
    public String listBanks(Model model){
        List<Bank> listBanks = bankRepository.findAll();
        model.addAttribute("listBanks", listBanks);
        return "banks";
    }


    @GetMapping("/bank/new")
    public String showNewBankForm(Model model){
        List<Client> listClients = clientRepository.findAll();
        List<Credit> listCredits = creditRepository.findAll();
        model.addAttribute("listClients", listClients);
        model.addAttribute("listCredits", listCredits);
        model.addAttribute("bank", new Bank());
        return "bank-form";
    }

    @PostMapping("/bank/save")
    public String saveBank(Bank bank){
        bankRepository.save(bank);
        return "redirect:/";
    }
}
