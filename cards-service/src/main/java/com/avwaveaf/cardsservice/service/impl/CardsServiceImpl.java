package com.avwaveaf.cardsservice.service.impl;

import com.avwaveaf.cardsservice.dto.CardsDTO;
import com.avwaveaf.cardsservice.entity.Cards;
import com.avwaveaf.cardsservice.exception.ResourceAlreadyExistException;
import com.avwaveaf.cardsservice.exception.ResourceNotFoundException;
import com.avwaveaf.cardsservice.mapper.CardMapper;
import com.avwaveaf.cardsservice.repository.CardsRepository;
import com.avwaveaf.cardsservice.service.ICardService;
import com.avwaveaf.cardsservice.service.handler.CardGenerationHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardService {
    private final CardsRepository cardsRepository;
    private final CardGenerationHandler cardGenerationHandler;

    /**
     * Create / Register cards
     *
     * @param phoneNumber - Customer Phone Number
     *
     */
    @Override
    public void registerCard(String phoneNumber) {
        Optional<Cards> found = cardsRepository.findByMobileNumber(phoneNumber);

        if (found.isPresent()) {
            throw new ResourceAlreadyExistException("Cards", "mobileNumber", phoneNumber);
        }

        Cards newCard = cardGenerationHandler.generateNewCard(phoneNumber);
        cardsRepository.save(newCard);
    }

    /**
     * find Cards by phone Number
     *
     * @param phoneNumber - Customer Phone Number
     *
     */
    @Override
    public CardsDTO findByPhoneNumber(String phoneNumber) {
        Cards found = findByPhoneNumberOrThrowNotFound(phoneNumber);
        return CardMapper.toDTO(found);
    }

    /**
     * Update Cards Information
     *
     * @param cardsDTO - CardsDTO Object
     *
     */
    @Override
    public boolean updateCard(CardsDTO cardsDTO) {
        Cards found = findByPhoneNumberOrThrowNotFound(cardsDTO.getMobileNumber());
        CardMapper.toEntity(found, cardsDTO);
        cardsRepository.save(found);
        return true;
    }

    /**
     * Delete Cards by Phone Number
     *
     * @param phoneNumber - Customer Phone Number
     *
     */
    @Override
    public boolean deleteCard(String phoneNumber) {
        Cards found = findByPhoneNumberOrThrowNotFound(phoneNumber);
        cardsRepository.deleteById(found.getCardId());
        return true;
    }

    private Cards findByPhoneNumberOrThrowNotFound(String query) {
        return cardsRepository.findByMobileNumber(query).orElseThrow(
                () -> new ResourceNotFoundException("Cards", "mobileNumber", query)
        );
    }
}
