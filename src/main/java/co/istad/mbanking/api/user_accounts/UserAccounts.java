package co.istad.mbanking.api.user_accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class UserAccounts {
    private Integer id;
    private Integer userId;
    private Integer accountId;
    private LocalDateTime createAt;
    private boolean isDisabled;
}














