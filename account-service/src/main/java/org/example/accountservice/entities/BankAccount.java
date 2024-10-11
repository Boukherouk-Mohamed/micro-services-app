package org.example.accountservice.entities;


import jakarta.persistence.*;
import lombok.*;
import org.example.accountservice.enums.AccountType;
import org.example.accountservice.model.Customer;

import java.time.LocalDate;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class BankAccount {

    @Id
    private String id;
    private double balance;
    private LocalDate createAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerId;

}
