package org.launchcode.foodie.controllers;

import org.launchcode.foodie.models.Cuisine;
import org.launchcode.foodie.models.data.CuisineDao;
import org.launchcode.foodie.models.data.RestaurantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("cuisine")
public class CuisineController {

    @Autowired
    private CuisineDao cuisineDao;

    @Autowired
    private RestaurantDao restaurantDao;

    @RequestMapping(value ="")
    public String index(Model model) {
        model.addAttribute("cuisines", cuisineDao.findAll());
        model.addAttribute("title", "Cuisines");
        return "cuisine/index";
    }

    @RequestMapping(value ="add", method = RequestMethod.GET)
    public String displayAddCuisineForm(Model model){
        model.addAttribute("title", "Add Cuisine");
        model.addAttribute(new Cuisine());
        return "cuisine/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCuisineForm(@ModelAttribute @Valid Cuisine newCuisine, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Cuisine");
            return "cuisine/add";
        }
        cuisineDao.save(newCuisine);
        return "redirect:";
    }

    @RequestMapping(value="view/{cuisineId)", method = RequestMethod.GET)
    public String searchByCuisine(Model model, @PathVariable int cuisineId){
        Cuisine cuisine = cuisineDao.findOne(cuisineId);
        model.addAttribute("title", cuisine.getName());
        model.addAttribute("Restaurants", cuisine.getRestaurants());
        model.addAttribute("cuisineId", cuisine.getId());

        return "cuisine/view";
    }




}
