package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserService() {
    }

    // Методы
    public void changePassword(String login, String newPass){ // Сменить пароль ()

        User user = userRepository.getByLogin(login);

        try {
            user.setPassword(newPass);
            userRepository.save(user);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            throw new NotFoundException(user.getId());
        }
    }
}