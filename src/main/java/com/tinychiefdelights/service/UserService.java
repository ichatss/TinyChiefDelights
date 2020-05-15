package com.tinychiefdelights.service;

import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class UserService implements UserDetailsService {


    // Fields
    //
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    // Injects are here
    //
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    // Методы
    //
//    // Сменить пароль !!!!!!!!!!!!!!! РАЗОБРАТЬСЯ ЧТО ТАК А ЧТО НЕТ
//    public User changePassword(String login, String newPass) throws UsernameNotFoundException {
//        User user = getUserDataByLogin(login);
//        if (user == null) {
//            throw new UsernameNotFoundException("не нашли" + login);
//        } else {
//            user.setPassword(passwordEncoder.encode(newPass));
//            return userRepository.save(user);
//        }
//    }



    private User getUserDataByLogin(String login) {
        User user = userRepository.getByLogin(login);
        return user;
    }


    // Загрузка пользователя по login (метод от интерфейса)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getUserDataByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("не нашли" + login);
        }
        return user;
    }
}