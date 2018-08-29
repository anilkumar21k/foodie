package org.launchcode.foodie.controllers;

import org.launchcode.foodie.models.Cuisine;
import org.launchcode.foodie.models.Restaurant;
import org.launchcode.foodie.models.RestaurantType;
import org.launchcode.foodie.models.data.CuisineDao;
import org.launchcode.foodie.models.data.RestaurantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private CuisineDao cuisineDao;

    @RequestMapping(value="")
    public String homePage() {

        return "restaurant/home";
    }

    @RequestMapping(value ="list")
    public String index(Model model) {

        model.addAttribute("restaurants", restaurantDao.findAll());
        model.addAttribute("title", "Welcome to Foodie.com");

        return "restaurant/list";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddRestaurantForm(Model model) {
        model.addAttribute("title", "Add Restaurant");
        model.addAttribute(new Restaurant());
        model.addAttribute("cuisines", cuisineDao.findAll());
        return "restaurant/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddRestaurantForm(@ModelAttribute @Valid Restaurant newRestaurant,
                                           Errors errors, @RequestParam int cuisineId, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Restaurant");
            return "restaurant/add";
        }
        Cuisine qisine= cuisineDao.findOne(cuisineId);
        newRestaurant.setCuisine(qisine);
        restaurantDao.save(newRestaurant);
        return "redirect:";
    }

    @RequestMapping(value="home", method = RequestMethod.POST)
    //@ResponseBody
    public String searchRestaurant(Model model, @RequestParam String searchTerm) {
        //String searchTerm = request.getParameter("searchTerm");
        System.out.println(searchTerm);
        List<Restaurant> sameAddresses = restaurantDao.findByAddressContaining(searchTerm);
        model.addAttribute("sameAddresses", sameAddresses );

        return "restaurant/search";

    }

    /*@RequestMapping(value="list", method = RequestMethod.POST)

    public String searchRestaurantByCuisine(Model model, @RequestParam int cuisineId) {
        //String searchTerm = request.getParameter("searchTerm");
        System.out.println(cuisineId);
        List<Restaurant> byCuisines = restaurantDao.findByCuisine(cuisineId);
        model.addAttribute("byCuisines", byCuisines);

        return "restaurant/search";

    } */

/*
    @RequestMapping(value = "home", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute @Valid Restaurant restaurant,) {
        List<Restaurant> result = restaurantDao.findByAddress(restaurant.getAddress());
        if (result.contains(restaurant.getAddress())) {
            model.addAttribute("restaurant", "restaurant");
            restaurantDao.save(restaurant);

        }
        return "restaurant/list";
    } */

    @RequestMapping(value = "cuisine", method = RequestMethod.GET)
    public String cuisine(Model model, @RequestParam int id) {

        Cuisine q = cuisineDao.findOne(id);
        List<Restaurant> restaurants = q.getRestaurants();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("title", "Restaurants with Cuisine: " + q.getName());
        return "restaurant/list";
    }
}
