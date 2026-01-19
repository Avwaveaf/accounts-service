package com.avwaveaf.accounts.helper;

import com.avwaveaf.accounts.constants.AccountConstants;
import com.avwaveaf.accounts.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public class OpsResHelper {
    public static ResponseEntity<ResponseDTO> handleOperations(boolean isSuccess) {
        if (isSuccess) {
            return ResponseEntity
                    .ok()
                    .body(
                            new ResponseDTO(
                                    AccountConstants.S_REQ_SUCCESS,
                                    AccountConstants.M_REQ_SUCCESS
                            )
                    );
        } else {
            return ResponseEntity
                    .internalServerError()
                    .body(
                            new ResponseDTO(
                                    AccountConstants.S_INT_ERR,
                                    AccountConstants.M_INT_ERR
                            )
                    );
        }
    }
}
