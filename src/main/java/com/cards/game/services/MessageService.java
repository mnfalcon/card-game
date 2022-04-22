package com.cards.game.services;

import com.cards.game.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends BaseService<Message>{

    public MessageService(JpaRepository<Message, Long> repository) {
        super(repository);
    }
}
