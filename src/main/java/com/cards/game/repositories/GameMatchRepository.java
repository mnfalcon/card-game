package com.cards.game.repositories;

import com.cards.game.models.GameMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameMatchRepository extends JpaRepository<GameMatch, Long> {
}
