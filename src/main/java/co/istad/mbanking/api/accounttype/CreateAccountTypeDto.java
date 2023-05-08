package co.istad.mbanking.api.accounttype;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateAccountTypeDto(@NotBlank(message = "Name is required...")  String name) {
}
