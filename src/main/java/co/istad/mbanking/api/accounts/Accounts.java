package co.istad.mbanking.api.accounts;

import co.istad.mbanking.api.accounttype.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Accounts {
    private Integer id;
    private String accountNo; //1
    private String accountName;  //2
    private String profile;  //3
    private Integer pin;
    private String password;
    private String phoneNumber;  //4
    private Integer transferLimit;  //5
    private AccountType accountType;  //6
}














