package co.istad.mbanking.api.accounts;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {
    List<AccountDto> toDtoList(List<Accounts> accounts);
    AccountDto accountToAccountDto(Accounts accounts);
    Accounts createAccountDtoToAccount(CreateAccountDto createAccountDto);
}
