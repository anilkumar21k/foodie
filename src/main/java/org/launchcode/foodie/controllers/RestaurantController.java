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

import javax.validation.Valid;

@Controller
@RequestMapping("restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private CuisineDao cuisineDao;

    @RequestMapping(value ="")
    public String index(Model model) {

        model.addAttribute("restaurants", restaurantDao.findAll());
        model.addAttribute("title", "Welcome to Foodie.com");

        return "restaurant/index";
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
}
