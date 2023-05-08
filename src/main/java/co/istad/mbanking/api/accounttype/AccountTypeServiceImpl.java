package co.istad.mbanking.api.accounttype;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RequiredArgsConstructor
@Service
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;
    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes = accountTypeMapper.select();
        System.out.println(accountTypes.get(0).getName());
        return accountTypeMapStruct.toDtoList(accountTypes);
    }

    @Override
    public AccountTypeDto createNewAccountType(CreateAccountTypeDto createAccountTypeDto) {
        AccountType accountType = accountTypeMapStruct.createAccountTypeDtoToAccountType(createAccountTypeDto);
        accountTypeMapper.inertAccountTypeNew(accountType);
        return accountTypeMapStruct.accountTypeToAccountTypeDto(accountType);
    }

    @Override
    public AccountTypeDto findAccountTypeById(Integer id) {
        AccountType accountType = accountTypeMapper.selectAccountTypeById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("AccountType with id %d not found", id)));
        return accountTypeMapStruct.accountTypeToAccountTypeDto(accountType);
    }

    @Override
    public Integer deleteAccountTypeById(Integer id) {
        boolean isAccountTypeExist = accountTypeMapper.isAccountTypeExist(id);
        System.out.println(isAccountTypeExist);
        if (isAccountTypeExist) {
            accountTypeMapper.deleteAccountTypeById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("AccountType with id %d not found", id));
    }

    @Override
    public AccountTypeDto update(Integer id, CreateAccountTypeDto createAccountTypeDto) {
        if (accountTypeMapper.isAccountTypeExist(id)){
            AccountType accountType = accountTypeMapStruct.createAccountTypeDtoToAccountType(createAccountTypeDto);
            accountType.setId(id);
            accountTypeMapper.updateAccountTypeById(accountType);
            return this.findAccountTypeById(id);
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Account Type with id %d not found",id));
    }


}
