package co.istad.mbanking.api.accounttype;



import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.UserDto;

import java.util.List;

public interface AccountTypeService {
  List<AccountTypeDto> findAll();
  AccountTypeDto createNewAccountType(CreateAccountTypeDto createAccountTypeDto);
  AccountTypeDto findAccountTypeById(Integer id);
  Integer deleteAccountTypeById(Integer id);
  AccountTypeDto update(Integer id, CreateAccountTypeDto createAccountTypeDto);



}
