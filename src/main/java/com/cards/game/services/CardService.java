package com.cards.game.services;


import com.cards.game.models.Card;
import com.cards.game.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card save(Card card) {
        return cardRepository.save(card);
    }
}
