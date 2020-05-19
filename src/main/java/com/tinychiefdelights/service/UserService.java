package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.exceptions.MainNotFound;
import com.tinychiefdelights.exceptions.MainNullPointer;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    // Сменить пароль
    public User changePassword(String login, String newPass) {

        try {

            User user = userRepository.getByLogin(login);
            // Тут мы проверяем, чтобы пользователь ввел свой логин, а не чужой
            if (user.getLogin() == User.getCurrentUser().getLogin()) {

                user.setPassword(passwordEncoder.encode(newPass));
                return userRepository.save(user);
            } else {
                // Иначе стреляем ошибкой IllegalArgument
                throw new MainIllegalArgument("Вы ввели неверный логин!");
            }
        } catch (NullPointerException e) { // try{} catch() нужен всего лишь для того, чтобы обрабоать nullPoint при инициализации user
            throw new MainNullPointer();
        }
    }


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