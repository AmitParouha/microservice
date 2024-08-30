package com.eazybank.account.reposiotry;

import com.eazybank.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Optional<Customer> findByMobileNumber(String mobileNumber);
    public boolean existsByMobileNumber(String mobileNumber);

}
