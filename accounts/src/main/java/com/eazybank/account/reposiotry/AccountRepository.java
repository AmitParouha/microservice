package com.eazybank.account.reposiotry;

import com.eazybank.account.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findByCustomerId(int customerId);

    // Here we are modify the data in DB that's why we have to mention these two annotation for delete
    @Transactional
    @Modifying
    public void deleteByCustomerId(int customerId);

}
