package co.istad.mbanking.api.accounts;

import co.istad.mbanking.api.accounttype.AccountType;

public record AccountDto(String accountNo, String accountName, String profile,
                         String phoneNumber, Integer transferLimit, AccountType accountType) {

}
