package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    //
    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

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


    // Загрузка пользователя по login (метод от интерфейса)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getUserDataByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("не нашли" + login);
        }
        return user;
    }


    private User getUserDataByLogin(String login) {
        User user = userRepository.getByLogin(login);
        return user;
    }
}