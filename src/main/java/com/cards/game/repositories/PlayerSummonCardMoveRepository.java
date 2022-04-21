package com.cards.game.repositories;

import com.cards.game.models.PlayerSummonCardMove;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerSummonCardMoveRepository extends JpaRepository<PlayerSummonCardMove, Long> {
}
