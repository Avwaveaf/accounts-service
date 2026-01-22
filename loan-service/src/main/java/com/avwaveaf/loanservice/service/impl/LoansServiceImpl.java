package com.avwaveaf.loanservice.service.impl;

import com.avwaveaf.loanservice.constants.LoansConstants;
import com.avwaveaf.loanservice.dto.LoansDTO;
import com.avwaveaf.loanservice.entity.Loans;
import com.avwaveaf.loanservice.exception.ResourceAlreadyExistException;
import com.avwaveaf.loanservice.exception.ResourceNotFoundException;
import com.avwaveaf.loanservice.mapper.LoansMapper;
import com.avwaveaf.loanservice.repository.LoansRepository;
import com.avwaveaf.loanservice.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private final LoansRepository loansRepository;
    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceAlreadyExistException("Loans", "mobileNumber", mobileNumber)
        );
        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDTO fetchLoan(String mobileNumber) {
        Loans found = findByPhoneNumberOrThrowNotFound(mobileNumber);
        return LoansMapper.toDTO(found);
    }

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDTO loansDto) {
        Loans found = findByPhoneNumberOrThrowNotFound(loansDto.getMobileNumber());
        LoansMapper.toEntity(loansDto, found);
        return true;
    }

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans found = findByPhoneNumberOrThrowNotFound(mobileNumber);
        loansRepository.deleteById(found.getLoanId());
        return true;
    }

    private Loans findByPhoneNumberOrThrowNotFound(String query) {
        Loans found = loansRepository.findByMobileNumber(query).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "mobileNumber", query)
        );
        return found;
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
