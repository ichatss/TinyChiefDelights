package com.tinychiefdelights.service;

import com.tinychiefdelights.model.User;
import org.thymeleaf.standard.expression.TextLiteralExpression;

import java.beans.Expression;

public class UserService {

    // Методы
    public void changePassword(User user, String newPass){ // Сменить пароль ()
        String currentPass = user.getPassword();

        if(currentPass != newPass && newPass.length() >= 6){

            user.setPassword(newPass);

        }else{

            throw new IllegalArgumentException("Пароли совпадают" +
                    "или введеный вами пароль меньше 6 символов");
        }


    }
}