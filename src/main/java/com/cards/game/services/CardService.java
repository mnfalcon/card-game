package com.cards.game.services;


import com.cards.game.models.Card;
import com.cards.game.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public Card getById(Long id) {
        return cardRepository.getById(id);
    }

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    public Page getByPage(int page, int limit) {
        PageRequest r = PageRequest.of(page, limit);
        return cardRepository.findAll(r);
    }

    public Card update(Card card, Long id) {
        if (cardRepository.existsById(id)) {
            card.setId(id);
            return cardRepository.save(card);
        } else {
            return null;
        }
    }
}
