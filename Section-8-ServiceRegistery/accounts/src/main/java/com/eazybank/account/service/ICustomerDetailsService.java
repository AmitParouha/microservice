package com.eazybank.account.service;

import com.eazybank.account.dto.CustomerDetailsDto;

public interface ICustomerDetailsService {

    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
