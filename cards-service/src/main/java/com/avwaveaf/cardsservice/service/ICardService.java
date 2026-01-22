package com.avwaveaf.cardsservice.service;

import com.avwaveaf.cardsservice.dto.CardsDTO;

public interface ICardService {

    /**
     * Create / Register cards
     * @param phoneNumber - Customer Phone Number
     * */
    void registerCard(String phoneNumber);

    /**
     *  find Cards by phone Number
     * @param phoneNumber - Customer Phone Number
     * */
    CardsDTO findByPhoneNumber(String phoneNumber);

    /**
     * Update Cards Information
     * @param cardsDTO - CardsDTO Object
     * */
    boolean updateCard(CardsDTO cardsDTO);

    /**
     *  Delete Cards by Phone Number
     * @param phoneNumber - Customer Phone Number
     * */
    boolean deleteCard(String phoneNumber);
}
