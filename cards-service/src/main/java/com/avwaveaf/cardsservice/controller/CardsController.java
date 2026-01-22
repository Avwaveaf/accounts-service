package com.avwaveaf.cardsservice.controller;

import com.avwaveaf.cardsservice.constants.NetConst;
import com.avwaveaf.cardsservice.dto.CardsDTO;
import com.avwaveaf.cardsservice.dto.ResponseDTO;
import com.avwaveaf.cardsservice.helper.OpsResHelper;
import com.avwaveaf.cardsservice.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD Rest API for Card Service",
        description = "CRUD Rest API controller for card detail operations"
)
@RestController
@RequestMapping("/api/v1/cards")
@AllArgsConstructor
@Validated
public class CardsController {

    private final ICardService cardService;

    // ================= CREATE =================

    @Operation(
            summary = "Create a new card",
            description = "Registers a new card for a given mobile number",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Card created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Card already exists", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> createCard(
            @Valid
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{12})", message = "Mobile number must be 12 digits")
            String mobileNumber
    ) {
        cardService.registerCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(NetConst.S_CREATED, NetConst.M_CREATED));
    }

    // ================= READ =================

    @Operation(
            summary = "Get card by mobile number",
            description = "Fetch card details using customer's mobile number",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Card retrieved successfully",
                            content = @Content(schema = @Schema(implementation = CardsDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Card not found", content = @Content)
            }
    )
    @GetMapping
    public ResponseEntity<CardsDTO> getCard(
            @Valid @RequestParam
            @Pattern(regexp = "(^$|[0-9]{12})", message = "Mobile number must be 12 digits") String mobileNumber
    ) {
        return ResponseEntity.ok(cardService.findByPhoneNumber(mobileNumber));
    }


//    // ================= UPDATE =================

    @Operation(
            summary = "Update card details",
            description = "Update existing card information by mobile number",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Card updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Card not found", content = @Content)
            }
    )
    @PutMapping
    public ResponseEntity<ResponseDTO> updateCard(
            @Valid @RequestBody CardsDTO cardsDTO
    ) {
        boolean isUpdated = cardService.updateCard(cardsDTO);
        return OpsResHelper.handleOperations(isUpdated);
    }

//    // ================= DELETE =================

    @Operation(
            summary = "Delete a card",
            description = "Delete card details using mobile number",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Card deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Card not found", content = @Content)
            }
    )
    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteCard(
            @Valid @RequestParam
            @Pattern(regexp = "(^$|[0-9]{12})", message = "Mobile number must be 12 digits") String mobileNumber
    ) {
        boolean isDeleted = cardService.deleteCard(mobileNumber);
        return OpsResHelper.handleOperations(isDeleted);
    }
}