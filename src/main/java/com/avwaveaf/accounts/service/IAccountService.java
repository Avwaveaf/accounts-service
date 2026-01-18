package com.avwaveaf.accounts.service;

import com.avwaveaf.accounts.dto.CustomerDTO;

public interface IAccountService {
    /**
     * Create Account Service
     * @param customerDTO - Customer DTO Object
     */
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO getAccountDetail(String mobileNumber);
}
