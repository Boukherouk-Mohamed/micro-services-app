package org.example.accountservice;

import org.example.accountservice.clients.CustomerRestClient;
import org.example.accountservice.entities.BankAccount;
import org.example.accountservice.enums.AccountType;
import org.example.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){
		return args -> {
			customerRestClient.allCustomers().forEach(customer -> {
				BankAccount bankAccount1 = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.balance(Math.random()*1552)
						.createAt(LocalDate.now())
						.currency("MAD")
						.type(AccountType.CURRENT_ACCOUNT)
						.customerId(customer.getId())
						.build();
				BankAccount bankAccount2 = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.balance(Math.random()*1542)
						.createAt(LocalDate.now())
						.currency("MAD")
						.type(AccountType.SAVING_ACCOUNT)
						.customerId(customer.getId())
						.build();

				bankAccountRepository.save(bankAccount1);
				bankAccountRepository.save(bankAccount2);
			});

		};
	}
}
