package com.cards.game.services;

import com.cards.game.models.PlayerAttackMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerAttackMoveService extends BaseService <PlayerAttackMove> {

    public PlayerAttackMoveService(JpaRepository<PlayerAttackMove, Long> repository) {
        super(repository);
    }
}
