package com.avwaveaf.loanservice.mapper;

import com.avwaveaf.loanservice.dto.LoansDTO;
import com.avwaveaf.loanservice.entity.Loans;

public class LoansMapper {
    public static LoansDTO toDTO(Loans loans) {
        // Maps loan data to the DTO representation
        return LoansDTO.builder()
                .loanNumber(loans.getLoanNumber())
                .loanType(loans.getLoanType())
                .totalLoan(loans.getTotalLoan())
                .mobileNumber(loans.getMobileNumber())
                .amountPaid(loans.getAmountPaid())
                .outstandingAmount(loans.getOutstandingAmount())
                .build();
    }

    /**
     * Updates entity from DTO; returns updated entity
     */
    public static void toEntity(LoansDTO loansDTO, Loans loans) {
        loans.setLoanNumber(loansDTO.getLoanNumber());
        loans.setLoanType(loansDTO.getLoanType());
        loans.setTotalLoan(loansDTO.getTotalLoan());
        loans.setMobileNumber(loansDTO.getMobileNumber());
        loans.setAmountPaid(loansDTO.getAmountPaid());
        loans.setOutstandingAmount(loansDTO.getOutstandingAmount());
    }
}
