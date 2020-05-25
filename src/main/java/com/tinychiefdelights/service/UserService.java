package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.exceptions.MainNullPointer;
import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.CustomerRepository;
import com.tinychiefdelights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {


    // Fields
    //
    private CustomerRepository customerRepository;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    // Injects are here
    //
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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
    // Регистрация
    public Customer registration(String name, String lastName, String login, String password, String password2) {

        User newUser = new User();

        newUser.setRole(User.ROLE_CUSTOMER);
        newUser.setName(name);
        newUser.setLastName(lastName);
        if (!userRepository.existsByLogin(login)) {
            newUser.setLogin(login);
        } else {
            throw new MainIllegalArgument("Пользователем с таким логином уже имеется!");
        }

        if (password.equals(password2)) { // Делаем проверку, чтобы пароли совпадали
            newUser.setPassword(passwordEncoder.encode(password));
        } else {
            throw new MainIllegalArgument("Пароли не совпадают!");
        }
        Customer newCustomer = new Customer();
        newCustomer.setUser(newUser);
        newCustomer.setWallet(0);

        userRepository.save(newUser);

        return customerRepository.save(newCustomer);
    }


    // Сменить пароль
    public User changePassword(String login, String newPass) {

        try {

            User user = userRepository.getByLogin(login);
            // Тут мы проверяем, чтобы пользователь ввел свой логин, а не чужой
            if (user.getLogin().equals(User.getCurrentUser().getLogin())) {

                user.setPassword(passwordEncoder.encode(newPass));
                return userRepository.save(user);

            } else {
                // Иначе стреляем ошибкой IllegalArgument
                throw new MainIllegalArgument("Вы ввели неверный логин!");
            }
        } catch (NullPointerException e) { // try{} catch() нужен всего лишь для того,
                                            // чтобы обрабоать nullPoint при инициализации user
            throw new MainNullPointer();
        }
    }


    // Тут берем пользователя по логину
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