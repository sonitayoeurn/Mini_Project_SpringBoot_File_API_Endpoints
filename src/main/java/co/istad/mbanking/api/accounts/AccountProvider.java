package co.istad.mbanking.api.accounts;

import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {
//    public record CreateAccountDto(@NotBlank Integer accountNo, @NotBlank String accountName,
//                                   String profile, @NotBlank Integer pin, @NotBlank String password,
//                                   @NotBlank Integer phoneNumber, @NotBlank Integer accountType) {
//    }


    private static final String tableAccount = "accounts";

    public String buildInsertAccountSql(){     //    C = Create New Account Type
        return new SQL(){{
            INSERT_INTO(tableAccount);
            VALUES("account_no", "#{u.accountNo}");
            VALUES("account_name", "#{u.accountName}");
            VALUES("profile", "#{u.profile}");
            VALUES("pin", "#{u.pin}");
            VALUES("password", "#{u.password}");
            VALUES("phone_number", "#{u.phoneNumber}");
            VALUES("account_type", "#{u.accountType}");
        }}.toString();
    }


    public String buildSelectAccountSql(){
        return new SQL(){{
            // TODO:
            SELECT ("*");
            FROM(tableAccount);
        }}.toString();
    }

}



















