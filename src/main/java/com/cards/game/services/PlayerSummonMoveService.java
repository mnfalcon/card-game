package com.cards.game.services;

import com.cards.game.models.PlayerSummonCardMove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerSummonMoveService extends BaseService<PlayerSummonCardMove> {

    @Autowired
    public PlayerSummonMoveService(JpaRepository<PlayerSummonCardMove, Long> repository) {
        super(repository);
    }
}
