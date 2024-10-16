package org.example.customerservice;

import org.example.customerservice.entities.Customer;
import org.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (CustomerRepository customerRepository){
		return args -> {

			List<Customer> customerList = List.of(
					Customer.builder()
							.firstname("Mohamed")
							.lastname("boukherouk")
							.email("moh@gmail.com")
							.build(),
					Customer.builder()
							.firstname("Moh")
							.lastname("bk")
							.email("bk@gmail.com")
							.build()
			);
			customerRepository.saveAll(customerList);
		};
	}

}
