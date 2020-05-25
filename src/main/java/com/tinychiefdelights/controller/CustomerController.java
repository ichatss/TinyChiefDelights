package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.model.*;
import com.tinychiefdelights.service.CustomerService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

import static com.tinychiefdelights.model.User.ROLE_CUSTOMER;


@Api(value = "Работа с Заказчиком", tags = {"Заказчик"})
@RestController
@RequestMapping("/customer")
@RolesAllowed({ROLE_CUSTOMER})
public class CustomerController {

    //Constructor
    //
    // Injects через конструктор
    @Autowired
    public CustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }


    // Fields
    // Injects into constructor
    //
    private final CustomerService customerService;

    private final UserService userService;


    // GET
    //
    // Посмотреть меню
    @GetMapping("/menu")
    List<Dish> getMenu() {
        return customerService.getMenu();
    }


    // Возвращаем пользователю информацию о том каких поваров ему нужно назначить
    @GetMapping("/types")
    public String getTypes(Long basketId) {
        boolean[] flags = customerService.generateFlags(basketId);
        return "CONFECTIONER- " + flags[0] + ", FISH_COOK- " + flags[1] + ", MEAT_COOK- " + flags[2];
    }


    // POST
    //
    // Заполнить корзину
    @PostMapping("/basket")
    public void setBasket(@RequestParam List<Long> dishList) {
        customerService.setBasket(dishList);
    }


    // Оформление заказа с самостоятельным добавлением поваров
    @PostMapping("/make/order/")
    public void makeOrder(@RequestParam String address, @RequestParam String phoneNumber,
                          @RequestParam Long basketId, @RequestParam List<Long> cooksId,
                          @RequestParam("dateInput")
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateInput) {

        // Валидация
        if (address.length() < 10) {
            throw new MainIllegalArgument("Пожалуйста! Укажите город доставки, улицу и номер дома!");
        }
        if (phoneNumber.length() != 11) {
            throw new MainIllegalArgument("Номер телефона должен содержать 11 цифр. Пример: 8-*** или 7-***");
        }
        Date date = new Date();
        if (!dateInput.after(date)) {
            throw new MainIllegalArgument("Неверно указана дата!");
        }
        //

        customerService.makeOrder(address, phoneNumber, basketId, dateInput, cooksId);
    }


    // Сделать заказ
    @PostMapping("/make/order/auto")
    public void makeOrderAuto(@RequestParam String address, @RequestParam String phoneNumber,
                              @RequestParam Long basketId, @RequestParam("dateInput")
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateInput) {

        // Валидация
        if (address.length() < 10) {
            throw new MainIllegalArgument("Пожалуйста! Укажите город доставки, улицу и номер дома!");
        }
        if (phoneNumber.length() != 11) {
            throw new MainIllegalArgument("Номер телефона должен содержать 11 цифр. Пример: 8-*** или 7-***");
        }
        //

        customerService.makeOrder(address, phoneNumber, basketId, dateInput, null);
    }


    // Оставить отзыв
    @PostMapping("/set/{id}/review/")
    public void setReview(@PathVariable Long id, @RequestParam String text, @RequestParam byte rate) {

        if (rate > 5 || rate < 1) {
            throw new MainIllegalArgument("Рейтинг повара должен быть не более 5 и не менее 1!");
        }
        if (text.length() <= 15) {
            throw new MainIllegalArgument("Минимальное количество символов должно быть 15");
        }

        customerService.setReview(id, rate, text);
    }


    // PUT
    //
    // Заказчик может редактировать свою карточку (поиск по ID)
    @PutMapping("/edit")
    Customer editCustomer(@RequestParam String login,
                          @RequestParam String name,
                          @RequestParam String lastName) {

        // Валидация
        if (name.length() <= 1 || name.length() >= 27) {
            throw new MainIllegalArgument("Имя должно быть не менее 2 символов и не более 26!");
        }
        if (lastName.length() <= 1 || lastName.length() >= 27) {
            throw new MainIllegalArgument("Имя должно быть не менее 2 символов и не более 26!");
        }
        if (login.length() <= 3 || login.length() >= 23) {
            throw new MainIllegalArgument("Логин должен быть не менее 4 символов и не более 24!");
        }
        //

        return customerService.editCustomer(login, name, lastName);
    }


    // Снять деньги со своего депозита (Заказчик)
    @PutMapping("/withdraw/{money}")
    void withdrawMoney(@RequestParam double money) {
        customerService.withdrawMoney(money);
    }


    // Изменить свой пароль
    @PutMapping("/change/password")
    void changePassword(@RequestParam String login, @RequestParam String newPass) {

        // Валидация
        if (newPass.length() <= 5 || newPass.length() >= 16) {
            throw new MainIllegalArgument("Пароль должен быть не менее 6 символов и не более 18!");
        }
        //

        userService.changePassword(login, newPass);
    }


    // Внести деньги на счет (Заказчик)
    @PutMapping("/deposit/money")
    public void depositMoney(@RequestParam double money) {
        customerService.depositMoney(money);
    }


    // Отменить Заказ
    @PutMapping("/cancel/order/{id}")
    public void cancelOrder(@PathVariable Long id) {
        customerService.cancelOrder(id);
    }


    // DELETE MAPPING
    //
}