package com.avwaveaf.accounts.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountsDTO {

    @NotEmpty(message = "Customer Account Number cannot be null")
    @Digits(fraction = 0, integer = 12, message = "Invalid Account Number")
    private Long accountNumber;

    @NotEmpty(message = "Account Type cannot be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address cannot be null")
    private String branchAddress;
}
