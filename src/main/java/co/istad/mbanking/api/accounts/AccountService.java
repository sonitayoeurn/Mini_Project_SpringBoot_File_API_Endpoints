package co.istad.mbanking.api.accounts;

import co.istad.mbanking.api.accounttype.AccountTypeDto;
import co.istad.mbanking.api.accounttype.CreateAccountTypeDto;

import java.util.List;

public interface AccountService {
    AccountDto createNewAccount(CreateAccountDto createAccountDto);
    List<AccountDto> findAll();
}
