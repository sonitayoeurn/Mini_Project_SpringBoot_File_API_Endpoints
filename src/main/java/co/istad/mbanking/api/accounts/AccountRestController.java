package co.istad.mbanking.api.accounts;

import co.istad.mbanking.api.accounttype.AccountTypeDto;
import co.istad.mbanking.api.accounttype.CreateAccountTypeDto;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Builder
@RequestMapping("/api/v1/accounts")
public class AccountRestController {
    private final AccountService accountService;

    //    Create new account
    @PostMapping
    public BaseRest<?> createNewAccount (@RequestBody @Valid CreateAccountDto createAccountDto){
        AccountDto accountDto = accountService.createNewAccount(createAccountDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been created")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }


    @GetMapping
    public BaseRest<?> findAll(){
        var accountDtoList = accountService.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been found")
                .timestamp(LocalDateTime.now())
                .data(accountDtoList)
                .build();

    }

}





























