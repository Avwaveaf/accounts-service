package com.avwaveaf.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(
        name = "Response",
        description = "Response Schema for successful operation"
)
public class ResponseDTO {
    @Schema(
            description = "Http Status Code",
            examples = "200"
    )
    private String statusCode;
    @Schema(
            description = "Information Operation Message",
            examples = "Accounts Creation Success!"
    )
    private String statusMessage;
}
