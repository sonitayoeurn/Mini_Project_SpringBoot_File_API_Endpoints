package co.istad.mbanking.api.accounttype;


import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {
    List<AccountTypeDto> toDtoList(List<AccountType> model);
    AccountTypeDto accountTypeToAccountTypeDto(AccountType accountType);
    AccountType createAccountTypeDtoToAccountType(CreateAccountTypeDto createAccountTypeDto);

}
