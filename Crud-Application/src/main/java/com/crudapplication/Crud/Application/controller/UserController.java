package com.example.crud.restapp.controller;


import com.example.crud.restapp.domain.User;
import com.example.crud.restapp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    private UserServices service;


    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<User> listuser= service.listAll();
        model.addAttribute("listuser", listuser);

        return "index";
    }
    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        User user = service.get(id);
        mav.addObject("user", user);
        return mav;

    }
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}
