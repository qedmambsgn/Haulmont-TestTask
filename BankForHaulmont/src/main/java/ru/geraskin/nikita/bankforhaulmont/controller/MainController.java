package ru.geraskin.nikita.bankforhaulmont.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geraskin.nikita.bankforhaulmont.model.Bank;
import ru.geraskin.nikita.bankforhaulmont.model.Client;
import ru.geraskin.nikita.bankforhaulmont.model.Credit;
import ru.geraskin.nikita.bankforhaulmont.repository.ClientRepository;
import ru.geraskin.nikita.bankforhaulmont.repository.CreditRepository;

import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    private final ClientRepository clientRepository;

    private final CreditRepository creditRepository;

    @Autowired
    public MainController(ClientRepository clientRepository,
                          CreditRepository creditRepository) {
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
    }

    @GetMapping("/clients")
    public String listClients(Model model){
        List<Client> listClients = clientRepository.findAll();
        model.addAttribute("listClients", listClients);
        return "clients";
    }

    @GetMapping("/clients/new")
    public String showClientNewForm(Model model){
        model.addAttribute("client", new Client());

        return "client-form";
    }

    @PostMapping("/clients/save")
    public String saveClient(Client client){
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("clients/edit/{id}")
    public String showEditClientForm(@PathVariable("id")UUID id, Model model){
        Client client = clientRepository.findById(id).get();
        model.addAttribute("client", client);
        return "client-form";
    }

    @GetMapping("clients/delete/{id}")
    public String deleteClient(@PathVariable("id")UUID id, Model model){
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }

    @GetMapping("/credits")
    public String listCredits(Model model){
        List<Credit> listCredits = creditRepository.findAll();
        model.addAttribute("listCredits", listCredits);
        return "credits";
    }

    @GetMapping("/credits/new")
    public String showCreditNewForm(Model model){
        model.addAttribute("credit", new Credit());
        return "credit-form";
    }

    @PostMapping("/credits/save")
    public String saveCredit(Credit credit, BindingResult result, Model model){
        if(credit.getPercent()<0||credit.getPercent()>100){
            result.rejectValue("percent", "error.percent", "Невозможный процент");
        }
        if(result.hasErrors()){
            model.addAttribute("credit", credit);
            return "credit-form";
        }
        else {
            creditRepository.save(credit);
            return "redirect:/credits";
        }
    }

}
