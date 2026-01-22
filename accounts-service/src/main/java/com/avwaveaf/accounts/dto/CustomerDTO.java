package com.avwaveaf.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account Information"
)
public class CustomerDTO {
    @Schema(
            description = "Holds Customer Name",
            examples = "John Doe"
    )
    private String name;

    @Schema(
            description = "Customer email address [cannot be null]",
            examples = "john@doe.com"
    )
    @NotEmpty(message = "Customer Name Cannot be empty")
    @Size(min = 3, max = 50, message = "Customer Email must be between 3 and 25 characters")
    @Email(message = "Invalid Email Address")
    private String email;

    @Schema(
            description = "Customer Phone number [Cannot be null & must be Digit max 10]",
            examples = "1234567891"
    )
    @NotEmpty(message = "Phone Number Cannot be empty")
    @Digits(fraction = 0, integer = 12, message = "Invalid Phone Number")
    private String mobileNumber;

    @Schema(
            description = "Accounts Detail for customer"
    )
    private AccountsDTO accounts;
}

