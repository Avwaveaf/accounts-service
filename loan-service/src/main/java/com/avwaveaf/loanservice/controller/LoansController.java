package com.avwaveaf.loanservice.controller;

import com.avwaveaf.loanservice.dto.LoansDTO;
import com.avwaveaf.loanservice.dto.ResponseDTO;
import com.avwaveaf.loanservice.helper.OpsResHelper;
import com.avwaveaf.loanservice.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Loans Service",
        description = "APIs for managing customer loan accounts"
)
@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
@Validated
public class LoansController {

    private final ILoansService loansService;

    private static final String MOBILE_REGEX = "^08[1-9][0-9]{7,10}$";

    @Operation(
            summary = "Create a new loan",
            description = "Creates a loan for a given customer mobile number",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Loan created successfully"),
                    @ApiResponse(responseCode = "409", description = "Loan already exists"),
                    @ApiResponse(responseCode = "400", description = "Invalid mobile number")
            }
    )
    @PostMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDTO> createLoan(
            @Parameter(
                    description = "Customer mobile number",
                    example = "081234567890",
                    required = true
            )
            @PathVariable
            @Pattern(regexp = MOBILE_REGEX, message = "Invalid mobile number format")
            String mobileNumber
    ) {
        loansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO("201", "Loan created successfully"));
    }

    @Operation(
            summary = "Fetch loan details",
            description = "Fetch loan details by customer mobile number",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Loan details fetched",
                            content = @Content(schema = @Schema(implementation = LoansDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Loan not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid mobile number")
            }
    )
    @GetMapping("/{mobileNumber}")
    public ResponseEntity<LoansDTO> fetchLoan(
            @PathVariable
            @Pattern(regexp = MOBILE_REGEX, message = "Invalid mobile number format")
            String mobileNumber
    ) {
        return ResponseEntity.ok(loansService.fetchLoan(mobileNumber));
    }

    @Operation(
            summary = "Update loan details",
            description = "Updates loan details for an existing customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Loan updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Loan not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid request payload")
            }
    )
    @PutMapping
    public ResponseEntity<ResponseDTO> updateLoan(
            @Valid @RequestBody LoansDTO loansDTO
    ) {
        boolean isUpdated = loansService.updateLoan(loansDTO);
        return OpsResHelper.handleOperations(isUpdated);
    }

    @Operation(
            summary = "Delete loan",
            description = "Deletes loan details by mobile number",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Loan deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Loan not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid mobile number")
            }
    )
    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDTO> deleteLoan(
            @PathVariable
            @Pattern(regexp = MOBILE_REGEX, message = "Invalid mobile number format")
            String mobileNumber
    ) {
        boolean isDeleted = loansService.deleteLoan(mobileNumber);
        return OpsResHelper.handleOperations(isDeleted);
    }
}