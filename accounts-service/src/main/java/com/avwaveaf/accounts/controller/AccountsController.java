package com.avwaveaf.accounts.controller;

import com.avwaveaf.accounts.constants.NetConst;
import com.avwaveaf.accounts.dto.CustomerDTO;
import com.avwaveaf.accounts.dto.ErrorResponseDTO;
import com.avwaveaf.accounts.dto.ResponseDTO;
import com.avwaveaf.accounts.helper.OpsResHelper;
import com.avwaveaf.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jdk.jfr.ContentType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD Api for Accounts",
        description = "CRUD Api controller for account detail operations."
)
@ApiResponse(
        responseCode = "201",
        description = "201 Accounts Created"
)
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {

    private IAccountService accountService;

    @Operation(
            summary = "Create new Accounts"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(
            @RequestBody @Valid CustomerDTO customerDTO
    ) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(NetConst.S_CREATED, NetConst.M_CREATED));
    }

    @Operation(
            summary = "Get Accounts by Phone Number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "200 OK - Operation success"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> getAccountsDetail(
            @RequestParam
            @Digits(fraction = 0, integer = 12, message = "Invalid Phone Number")
            String mobileNumber
    ) {
        CustomerDTO customerDTO = accountService.getAccountDetail(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTO);
    }

    @Operation(
            summary = "Update Accounts Detail Information"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "200 OK - Operation success"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "500 - Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountsDetail(
            @RequestBody @Valid CustomerDTO customerDTO
    ) {
        boolean isUpdated = accountService.updateAccount(customerDTO);
        return OpsResHelper.handleOperations(isUpdated);
    }

    @Operation(
            summary = "Delete Accounts by Phone Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "200 OK - Operation success"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "500 - Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccounts(
            @RequestParam(name = "mobileNumber")
            @Digits(fraction = 0, integer = 12, message = "Invalid Phone Number")
            String phoneNumber
    ) {
        boolean isDeleted = accountService.deleteAccount(phoneNumber);
        return OpsResHelper.handleOperations(isDeleted);
    }
}
