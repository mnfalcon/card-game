package com.cards.game.services;

import com.cards.game.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService extends BaseService<Player> {

    public PlayerService(JpaRepository<Player, Long> repository) {
        super(repository);
    }
}
