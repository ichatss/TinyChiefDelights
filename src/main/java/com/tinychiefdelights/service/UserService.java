package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Fields
    //
    private UserRepository userRepository;

    // Injects are here
    //
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Методы
    //
    // Сменить пароль !!!!!!!!!!!!!!! РАЗОБРАТЬСЯ ЧТО ТАК А ЧТО НЕТ
    public void changePassword(String login, String newPass) {

        User user = userRepository.getByLogin(login);

        try {
            user.setPassword(newPass);
            userRepository.save(user);
        } catch (NullPointerException e) {
            throw new NullPointerException("Ошибка! Указанный Вами логин не существует!"); // Надо посмотреть в чем дело
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (NotFoundException e) {
            throw new NotFoundException(user.getId());
        }
    }
}