package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

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
        /**НПЕ из того что не находит юзера с таким логином**/
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserDataByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException("не нашли" + username);
        }
        return user;
    }

    private User getUserDataByLogin(String login) {
        User user = userRepository.getByLogin(login);
        return user;
    }
}