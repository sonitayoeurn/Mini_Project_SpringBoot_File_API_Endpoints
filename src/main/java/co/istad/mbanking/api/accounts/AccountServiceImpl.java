package co.istad.mbanking.api.accounts;

import co.istad.mbanking.api.accounttype.AccountType;
import co.istad.mbanking.api.accounttype.AccountTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountMapStruct accountMapStruct;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto createNewAccount(CreateAccountDto createAccountDto) {
        Accounts account = accountMapStruct.createAccountDtoToAccount(createAccountDto);
        accountMapper.inertAccountNew(account);
        System.out.println(account);
        return accountMapStruct.accountToAccountDto(account);
    }

    @Override
    public List<AccountDto> findAll() {
        List<Accounts> accounts = accountMapper.select();
//        System.out.println(accounts.get(0).getAccountNo());
        return accountMapStruct.toDtoList(accounts);
    }

}














