package com.tinychiefdelights;

import java.util.Date;

public class Order {

    // Поля
    private int number; // Номер заказа

    private Customer customer; // Заказчик

    private String address; // Адрес доставки

    private Date date; // Это поле для даты(посмотрю как именно он работает)

    private Date time; // Это поле для времени(посмотрю как именно он работает)

    private Cook cook; // Повар

    private ArrayList<Dish> dish; // Блюдо / Коллекция создана вслучае выбора нескольких блюд
    //


    // Методы
    private long priceCalculation(){ // Метод для расчета стоимсоти заказа
        return 0;
    }
    //
}