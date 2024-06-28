package com.eazybank.account.service.impl;

import com.eazybank.account.constant.AccountConstants;
import com.eazybank.account.dto.AccountDto;
import com.eazybank.account.dto.CustomerDto;
import com.eazybank.account.entity.Account;
import com.eazybank.account.entity.Customer;
import com.eazybank.account.exception.CustomerAlreadyExistedException;
import com.eazybank.account.exception.ResourceNotFoundException;
import com.eazybank.account.mapper.AccountMapper;
import com.eazybank.account.mapper.CustomerMapper;
import com.eazybank.account.reposiotry.AccountRepository;
import com.eazybank.account.reposiotry.CustomerRepository;
import com.eazybank.account.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        boolean isCustomerExisted = customerRepository.existsByMobileNumber(customerDto.getMobileNumber());
        if(!isCustomerExisted){
            Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
//            customer.setCreatedAt(LocalDateTime.now());
//            customer.setCreatedBy("Anonymous");
            Customer savedCustomer = customerRepository.save(customer);
            accountRepository.save(createNewAccount(customer));
        }
        else {
            throw new CustomerAlreadyExistedException("Customer already registered with the given mobile number "
                    +customerDto.getMobileNumber());
        }

    }
    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("Anonymous");

        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer","mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("Account","customerId", ""+customer.getCustomerId()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountDto accountDto = AccountMapper.mapToAccountsDto(account, new AccountDto());
        customerDto.setAccountDto(accountDto);

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountDto();
        if(accountsDto !=null ){
            Account account = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", ""+accountsDto.getAccountNumber())
            );
            AccountMapper.mapToAccounts(accountsDto, account);
//            account.setUpdatedAt(LocalDateTime.now());
//            account.setUpdatedBy("Anonymous");
            account = accountRepository.save(account);

            int customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", ""+customerId)
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
//            customer.setUpdatedAt(LocalDateTime.now());
//            customer.setUpdatedBy("Anonymous");
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
                );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
