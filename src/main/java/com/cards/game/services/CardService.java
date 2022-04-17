package com.cards.game.services;


import com.cards.game.models.Card;
import com.cards.game.repositories.CardRepository;
import com.cards.game.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService extends BaseService<Card> {

    @Autowired
    public CardService(CardRepository cardRepository) {
        super(cardRepository);
    }

    @Override
    public Card update(Card card, Long id) {
        if (repository.existsById(id)) {
            card.setId(id);
            return repository.save(card);
        }
        throw new NotFoundException(entityName);
    }
}
