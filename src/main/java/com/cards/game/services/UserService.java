package com.cards.game.services;

import com.cards.game.models.User;
import com.cards.game.repositories.UserRepository;
import com.cards.game.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }
}