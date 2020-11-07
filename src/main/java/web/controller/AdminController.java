package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    @GetMapping(value = "/")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping(value = "/users")
    public String allUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "/users";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("allRoles", userService.getAllRoles());
        User user = new User();
        model.addAttribute("user", user);
        return "/adduser";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Integer id) {
        model.addAttribute("allRoles", userService.getAllRoles());
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/edituser";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete")
    public String deleteUserById(@RequestParam("id") Integer id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "/show/";
    }

}
