package com.cards.game.repositories;

import com.cards.game.models.PlayerAttackMove;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerAttackMoveRepository extends JpaRepository<PlayerAttackMove, Long> {
}
