package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    @GetMapping(value = "/user")
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "showusers";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "/show";
    }
}