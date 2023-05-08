package co.istad.mbanking.api.accounttype;

import co.istad.mbanking.api.user.web.UserDto;
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
@RequestMapping("/api/v1/account-types")
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;


    @GetMapping
    public BaseRest<?> findAll(){
        var accountTypeDtoList = accountTypeService.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account types have been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoList)
                .build();

    }

//    Create new account type
    @PostMapping
    public BaseRest<?> createNewAccountType(@RequestBody @Valid CreateAccountTypeDto createAccountTypeDto){
        AccountTypeDto accountTypeDto = accountTypeService.createNewAccountType(createAccountTypeDto);
        return BaseRest.builder().status(true).code(HttpStatus.OK.value()).message("Account type have been created").timestamp(LocalDateTime.now())
                .data(accountTypeDto).build();
    }

//    Search account type by id
    @GetMapping("/{id}")
    public BaseRest<?> findAccountTypeById(@PathVariable("id") Integer id) {
        AccountTypeDto accountTypeDto = accountTypeService.findAccountTypeById(id);
        return BaseRest.builder().status(true).code(HttpStatus.OK.value()).message("Account type have been found").timestamp(LocalDateTime.now())
                .data(accountTypeDto).build();
    }

//    Update account type by id

    @PutMapping("/{id}")
    public BaseRest<?> updateAccountTypeById(@PathVariable("id") Integer id, @RequestBody @Valid CreateAccountTypeDto createAccountTypeDto){
        AccountTypeDto accountTypeDto = accountTypeService.update(id, createAccountTypeDto);
        return BaseRest.builder().status(true).
                code(HttpStatus.OK.value()).
                message("Account type have been updated ").
                timestamp(LocalDateTime.now()).data(accountTypeDto).build();
    }

//    Delete account type by id

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteAccountTypeById(@PathVariable("id") Integer id) {
        Integer deletedId = accountTypeService.deleteAccountTypeById(id);
        return BaseRest.builder().status(true).code(HttpStatus.OK.value()).message("Account type have been deleted").timestamp(LocalDateTime.now())
                .data(deletedId).build();
    }






}
