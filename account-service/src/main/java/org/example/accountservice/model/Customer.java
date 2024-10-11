package org.example.accountservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Customer {

    public Long id;
    public String firstname;
    public String lastname;
    public String email;

}
