package com.cards.game.repositories;

import com.cards.game.models.GameChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameChatRepository extends JpaRepository<GameChat, Long> {
}
