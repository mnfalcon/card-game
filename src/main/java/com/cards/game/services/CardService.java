package com.cards.game.services;


import com.cards.game.models.Card;
import com.cards.game.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CardService extends BaseService<Card> {

    @Autowired
    public CardService(CardRepository cardRepository) {
        super(cardRepository);
    }
}
