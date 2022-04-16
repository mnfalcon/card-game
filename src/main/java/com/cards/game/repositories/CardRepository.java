package com.cards.game.repositories;

import com.cards.game.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
