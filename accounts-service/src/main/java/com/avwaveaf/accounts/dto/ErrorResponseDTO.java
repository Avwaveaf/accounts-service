package com.avwaveaf.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@Schema(
        title = "Error Response",
        description = "Error Response Schema, contains error information on some operation"
)
public class ErrorResponseDTO {
    @Schema(
            description = "Relate path during invocation",
            examples = "/api/v1/delete"
    )
    private String apiPath;
    @Schema(
            description = "Http Status Code",
            examples = "500"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Descriptive Error Message during opeartion failure",
            examples = "Cannot delete User with id = 1"
    )
    private String errorMessage;
    @Schema(
            description = "error timestamp."
    )
    private LocalDateTime timestamp;
}
