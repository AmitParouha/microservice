package com.eazybank.account.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{

    @Id
    private long accountNumber;
    private int customerId;
    private String accountType;
    private String  branchAddress;

}
