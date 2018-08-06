package org.launchcode.foodie.controllers;

import org.launchcode.foodie.models.User;
import org.launchcode.foodie.models.data.CuisineDao;
import org.launchcode.foodie.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "add")
    public String add(Model model) {
        model.addAttribute("title", "User Signup");
        User user = new User();
        model.addAttribute(new User());
        return "user/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, String verify) {
        List<User> sameName = userDao.findByUsername(user.getUsername());
        if (!errors.hasErrors() && user.getPassword().equals(verify) && sameName.isEmpty()) {
            model.addAttribute("user", "user");
            userDao.save(user);
            return "user/index";
        }
        /*if (verify.equals(user.getPassword())){
            userDao.save(user);
            return "user/index";
        } */
        else {
            model.addAttribute("user", user);
            model.addAttribute("email", user.getEmail());
            model.addAttribute("title", "User Signup");

            if (!user.getPassword().equals(verify)) {
                model.addAttribute("message", "Passwords do not match");
                user.setPassword("");
            }

            if (!sameName.isEmpty()) {
                model.addAttribute("message", "Username is taken, please select another one");
            }
            return "user/add";
        }
    }
        /*if (!sameName.isEmpty()) {
            model.addAttribute("message", "Username is taken, please select another one");

        } */
    @RequestMapping(value = "login")
    public String loginForm(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute(new User());
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
        public String add(Model model, @ModelAttribute User user, HttpServletResponse response ){
            List<User> u = userDao.findByUsername(user.getUsername());
            if(u.isEmpty()) {
                model.addAttribute("message","Invalid Username" );
                model.addAttribute("title", "Login");
                return "user/login";
            }

            User loggedIn = u.get(0);
            if(loggedIn.getPassword().equals(user.getPassword())){

                Cookie c = new Cookie("user", user.getUsername());
                c.setPath("/");
                response.addCookie(c);
                return "redirect:/cheese";

            } else {
                model.addAttribute("message","Invalid Password");
                model.addAttribute("title", "Login");
                return "user/login";
            }


        }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);
        }
        return  "user/login";
    }

}

