package com.gemicle.messagingsender.service;

import org.springframework.stereotype.Service;

import com.gemicle.messagingsender.document.User;
import com.gemicle.messagingsender.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isChatInBase(String chatId) {
        try {
            return userRepository.existsByChatId(chatId);
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public boolean isUserInBase(String userId) {
        try {
            return userRepository.existsByUserId(userId);
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public User findUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId);
    }

}