package org.example.accountservice.web;


import org.example.accountservice.clients.CustomerRestClient;
import org.example.accountservice.entities.BankAccount;
import org.example.accountservice.model.Customer;
import org.example.accountservice.repository.BankAccountRepository;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository bankAccountRepository,
                                 CustomerRestClient customerRestClient){
        this.bankAccountRepository=bankAccountRepository;
        this.customerRestClient=customerRestClient;
    }

    @GetMapping("/accounts")
    List<BankAccount> bankAccountList(){
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        bankAccounts.forEach( bankAccount -> {
            bankAccount.setCustomer(customerRestClient.findCustomerById(bankAccount.getCustomerId()));
        });
        return bankAccounts;
    }

    @GetMapping("/accounts/{id}")
    BankAccount bankAccountById(@PathVariable String id){
        BankAccount bankAccount= bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;

    }

}
