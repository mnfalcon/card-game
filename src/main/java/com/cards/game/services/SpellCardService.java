package com.cards.game.services;

import com.cards.game.models.SpellCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SpellCardService extends BaseService<SpellCard> {

    @Autowired
    public SpellCardService(JpaRepository<SpellCard, Long> repository) {
        super(repository);
    }
}
