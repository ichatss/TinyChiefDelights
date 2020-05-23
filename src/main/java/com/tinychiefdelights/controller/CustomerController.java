package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.CustomerRepository;
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
    public CustomerController(CustomerRepository customerRepository,
                              CustomerService customerService,
                              UserService userService) {

        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.userService = userService;
    }


    // Fields
    // Injects into constructor
    //
    private final CustomerRepository customerRepository;

    private final CustomerService customerService;

    private final UserService userService;


    // GET
    //
    // Посмотреть меню
    @GetMapping("/menu")
    List<Dish> getMenu(){
        return customerService.getMenu();
    }


    // POST
    //
    // Заполнить корзину
    @PostMapping("/basket")
    public void setBasket(@RequestParam List<Long> dishList) {
        customerService.setBasket(dishList);
    }

    @GetMapping("/cooks")
    public List<Cook> getFreeCooks(){
        return customerService.getFreeCooks();
        //Ты как-то делал чтоб некотрые поля игнорировались при отправке на страницу,
        // но я забыл и в коде не нашел, завтра обсудим
    }

    //Возвращаем пользователю информацию о том каких поваров ему нужно назначить
    @GetMapping("types")
    public String getTypes(Long basketId){
        boolean[] flags = customerService.generateFlags(basketId);
        return "CONFECTIONER- " + flags[0] + ", FISH_COOK- " + flags[1] + ", MEAT_COOK- " + flags[2];
    }

    //Оформление заказа с самостоятельным добавлением поваров
    @PostMapping("/make/order/")
    public void makeOrder(String address, String phoneNumber, Long basketId, @RequestParam List<Long> cooksId, @RequestParam("dateInput")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateInput) {

        customerService.makeOrder(address, phoneNumber, basketId, dateInput, cooksId);
    }

    // Сделать заказ
    @PostMapping("/make/order/auto")
    public void makeOrderAuto(String address, String phoneNumber, Long basketId, @RequestParam("dateInput")
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateInput) {

        customerService.makeOrder(address, phoneNumber, basketId, dateInput, null);
    }


    // Оставить отзыв
    @PostMapping("/set/{id}/review/")
    public Review setReview(@PathVariable Long id, @RequestParam String text, @RequestParam int rate) {

        return customerService.setReview(id, rate, text);
    }


    // PUT
    //
    // Заказчик может редактировать свою карточку (поиск по ID)
    @PutMapping("/edit")
    Customer editCustomer(@RequestParam String login,
                          @RequestParam String name, @RequestParam String lastName) {

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