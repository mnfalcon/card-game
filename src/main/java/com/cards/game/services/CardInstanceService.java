package com.cards.game.services;

import com.cards.game.models.CardInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CardInstanceService extends BaseService<CardInstance> {

    public CardInstanceService(JpaRepository<CardInstance, Long> repository) {
        super(repository);
    }
}
