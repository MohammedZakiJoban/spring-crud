package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;
import domain.User;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<User> listuser = service.listAll();
        model.addAttribute("listuser", listuser);
        System.out.print("Get / ");
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User std) {
        service.save(std);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        User std = service.get(id);
        mav.addObject("user", std);
        return mav;

    }
    @RequestMapping("/delete/{id}")
    public String deleteuser(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }

}
