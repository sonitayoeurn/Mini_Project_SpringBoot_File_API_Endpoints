package co.istad.mbanking.api.accounts;

import co.istad.mbanking.api.accounttype.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateAccountDto(@NotBlank String accountNo, @NotBlank String accountName,
                               String profile, @NotNull Integer pin, @NotBlank String password,
                               @NotBlank String phoneNumber, AccountType accountType) {
}
