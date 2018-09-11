package org.launchcode.foodie.controllers;


import org.launchcode.foodie.models.Reservation;

import org.launchcode.foodie.models.data.ReservationDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private ReservationDao reservationDao;

    //@RequestMapping(value="")
    //@ResponseBody
   /* public String index() {
        //return "Welcome to Reservations";
        return "reservation/reservationForm";
    } */

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayReservationForm(Model model){
        model.addAttribute("title", "Add Reservation");
        model.addAttribute(new Reservation());

        return "reservation/reservationForm";
    }

   @RequestMapping(value= "reservationForm", method = RequestMethod.POST)
    public String processReservationForm(@ModelAttribute @Valid Reservation newReservation,
                                         Errors errors,  Model model) {
      /* if (errors.hasErrors()) {
           model.addAttribute("title", "Add Reservation");
           return "reservation/reservationForm";
       } */


       reservationDao.save(newReservation);
       return "reservation/reservationConfirmation";
   }
}


