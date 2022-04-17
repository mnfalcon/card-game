package com.cards.game.repositories;

import com.cards.game.models.SpellCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellCardRepository extends JpaRepository<SpellCard, Long> {
}
