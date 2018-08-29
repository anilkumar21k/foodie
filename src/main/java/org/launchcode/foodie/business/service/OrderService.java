package org.launchcode.foodie.business.service;

import org.launchcode.foodie.models.data.CuisineDao;
import org.launchcode.foodie.models.data.RestaurantDao;
import org.launchcode.foodie.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private RestaurantDao restaurantDao;
    private CuisineDao cuisineDao;
    private UserDao userDao;

    @Autowired
    public OrderService(RestaurantDao restaurantDao, CuisineDao cuisineDao, UserDao userDao) {
        this.restaurantDao = restaurantDao;
        this.cuisineDao = cuisineDao;
        this.userDao = userDao;
    }
}

