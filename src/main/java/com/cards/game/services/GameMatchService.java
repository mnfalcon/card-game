package com.cards.game.services;

import com.cards.game.models.GameMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GameMatchService extends BaseService<GameMatch> {

    public GameMatchService(JpaRepository<GameMatch, Long> repository) {
        super(repository);
    }
}
