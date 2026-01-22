package com.avwaveaf.loanservice.helper;

import com.avwaveaf.loanservice.constants.NetConst;
import com.avwaveaf.loanservice.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public class OpsResHelper {
    public static ResponseEntity<ResponseDTO> handleOperations(boolean isSuccess) {
        if (isSuccess) {
            return ResponseEntity
                    .ok()
                    .body(
                            new ResponseDTO(
                                    NetConst.S_REQ_SUCCESS,
                                    NetConst.M_REQ_SUCCESS
                            )
                    );
        } else {
            return ResponseEntity
                    .internalServerError()
                    .body(
                            new ResponseDTO(
                                    NetConst.S_INT_ERR,
                                    NetConst.M_INT_ERR
                            )
                    );
        }
    }
}
