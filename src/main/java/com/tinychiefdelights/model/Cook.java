package com.tinychiefdelights.model;

import java.security.SecureRandom;
import java.util.List;

public class Cook extends User {

    enum CookType{}
    float rating;
    boolean cookStatus;
    List<Review> reviewList;
    String aboutCook;

}
