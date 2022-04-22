package com.cards.game.repositories;

import com.cards.game.models.CardInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardInstanceRepository extends JpaRepository<CardInstance, Long> {
}
