package com.eazybank.account.service.impl;

import com.eazybank.account.dto.AccountDto;
import com.eazybank.account.dto.CardsDto;
import com.eazybank.account.dto.CustomerDetailsDto;
import com.eazybank.account.dto.LoansDto;
import com.eazybank.account.entity.Account;
import com.eazybank.account.entity.Customer;
import com.eazybank.account.exception.ResourceNotFoundException;
import com.eazybank.account.mapper.AccountMapper;
import com.eazybank.account.mapper.CustomerMapper;
import com.eazybank.account.reposiotry.AccountRepository;
import com.eazybank.account.reposiotry.CustomerRepository;
import com.eazybank.account.service.ICustomerDetailsService;
import com.eazybank.account.service.client.CardsFeignClient;
import com.eazybank.account.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsServiceImpl implements ICustomerDetailsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", ""+customer.getCustomerId())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountsDto(account, new AccountDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}
