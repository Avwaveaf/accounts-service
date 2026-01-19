package com.avwaveaf.accounts.controller;

import com.avwaveaf.accounts.constants.AccountConstants;
import com.avwaveaf.accounts.dto.CustomerDTO;
import com.avwaveaf.accounts.dto.ResponseDTO;
import com.avwaveaf.accounts.helper.OpsResHelper;
import com.avwaveaf.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(
            @RequestBody CustomerDTO customerDTO
    ) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.S_CREATED, AccountConstants.M_CREATED));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> getAccountsDetail(
            @RequestParam String mobileNumber
    ){
        CustomerDTO customerDTO = accountService.getAccountDetail(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTO);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccountsDetail(
            @RequestBody CustomerDTO customerDTO
    ) {
        boolean isUpdated = accountService.updateAccount(customerDTO);
        return OpsResHelper.handleOperations(isUpdated);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccounts(
            @RequestParam(name = "mobileNumber") String phoneNumber
    ){
        boolean isDeleted = accountService.deleteAccount(phoneNumber);
        return OpsResHelper.handleOperations(isDeleted);
    }
}
