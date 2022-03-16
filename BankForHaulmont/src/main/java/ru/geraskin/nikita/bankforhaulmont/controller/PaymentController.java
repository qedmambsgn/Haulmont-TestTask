package ru.geraskin.nikita.bankforhaulmont.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geraskin.nikita.bankforhaulmont.model.Bank;
import org.springframework.validation.BindingResult;
import ru.geraskin.nikita.bankforhaulmont.model.BankPayments;
import ru.geraskin.nikita.bankforhaulmont.model.Payments;
import ru.geraskin.nikita.bankforhaulmont.repository.BankPaymentsRepository;
import ru.geraskin.nikita.bankforhaulmont.repository.BankRepository;
import ru.geraskin.nikita.bankforhaulmont.repository.PaymentsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
public class PaymentController {

    private final PaymentsRepository paymentsRepository;

    private final BankRepository bankRepository;

    private final BankPaymentsRepository bankPaymentsRepository;

    @Autowired
    public PaymentController(PaymentsRepository paymentsRepository,
                             BankRepository bankRepository,
                             BankPaymentsRepository bankPaymentsRepository) {
        this.paymentsRepository = paymentsRepository;
        this.bankRepository = bankRepository;
        this.bankPaymentsRepository = bankPaymentsRepository;
    }

    @GetMapping("/payments")
    public String listPayments(Model model){
        List<Payments> listPayments = paymentsRepository.findAll();
        model.addAttribute("listPayments", listPayments);
        return "payments";
    }

    @GetMapping("/payments/new")
    public String showNewBankForm(Model model){
        List<Bank> listBanks = bankRepository.findAll();
        model.addAttribute("listBanks", listBanks);
        model.addAttribute("payments", new Payments());
        return "payments-form";
    }

    @PostMapping("/payments/save")
    public String saveBank(Payments payments, BindingResult result, Model model){
        if(payments.getBank().getCredit().getLimit()<payments.getSumma()){
            result.rejectValue("summa", "error.summa", "Сумма больше лимита");
        }
        if(payments.getSumma()<0){
            result.rejectValue("summa", "error.summa", "Отрицательное число");
        }
        if(payments.getPeriod() < 0){
            result.rejectValue("period", "error.period", "Отрицательное число");
        }
        if(result.hasErrors()){
            List<Bank> listBanks = bankRepository.findAll();
            model.addAttribute("listBanks", listBanks);
            model.addAttribute("payments", payments);
            return "payments-form";
        }
        else {
            paymentsRepository.save(payments);
            LocalDate date = payments.getDate();
            float monthPercent = ((payments.getBank().getCredit().getPercent()/12)/100);
            float periodHelper = payments.getPeriod();
            float ratio = (float) (monthPercent*Math.pow((1+monthPercent), periodHelper)/((Math.pow((1+monthPercent), periodHelper))-1));
            float remains = payments.getSumma();
            for(Integer i = 0; i < payments.getPeriod(); i++){
                BankPayments bankPayments = new BankPayments();
                float percentFeeHelper = remains*monthPercent;
                //Автоматический расчет суммы ежемесячного платежа с учетом процентной
                //ставки.
                bankPayments.setFee(ratio*payments.getSumma());
                //Автоматический расчет итоговой суммы процентов по кредиту;
                bankPayments.setOverPrice(-1*(payments.getSumma()-(ratio*payments.getSumma()*payments.getPeriod())));
                //сумма гашения тела кредита и сумма гашения процентов
                if(percentFeeHelper<0){
                    bankPayments.setBodyFee(ratio*payments.getSumma());
                    bankPayments.setPercentFee(0);
                }
                else {
                    bankPayments.setPercentFee(percentFeeHelper);
                    bankPayments.setBodyFee((ratio * payments.getSumma()) - percentFeeHelper);
                }
                //дата платежа
                bankPayments.setDate(date.plusMonths(1));
                date = date.plusMonths(1);
                //payments_id
                bankPayments.setPayments(payments);
                //остаток по кредиту
                remains -= (ratio*payments.getSumma());

                bankPaymentsRepository.save(bankPayments);
            }
            return "redirect:/";
        }
    }

    @GetMapping("payments/fee/{id}")
    public String showFee(@PathVariable("id") UUID id, Model model){
        List<BankPayments> listBankPayments = bankPaymentsRepository.findByPaymentId(id);
        model.addAttribute("listBankPayments", listBankPayments);
        return "bank_payments";
    }
}
