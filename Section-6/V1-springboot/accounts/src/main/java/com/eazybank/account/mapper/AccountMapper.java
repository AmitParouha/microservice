package com.eazybank.account.mapper;

import com.eazybank.account.dto.AccountDto;
import com.eazybank.account.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountsDto(Account account, AccountDto accountsDto) {
        accountsDto.setAccountNumber(account.getAccountNumber());
        accountsDto.setAccountType(account.getAccountType());
        accountsDto.setBranchAddress(account.getBranchAddress());
        return accountsDto;
    }

    public static Account mapToAccounts(AccountDto accountsDto, Account accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
