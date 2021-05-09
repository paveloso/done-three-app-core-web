package io.done3app.web.controller;

import io.done3app.web.entity.User;
import io.done3app.web.service.UserService;
import io.done3app.web.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/form")
    public String showRegistrationForm(Model theModel) {
        theModel.addAttribute("appUser", new AppUser());

        return "register";
    }

    @PostMapping("/do")
    public String processRegistrationForm(@Valid @ModelAttribute("appUser") AppUser appUser,
                                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User dbUser = userService.findByEmail(appUser.getEmail());
        if (dbUser != null) {
            model.addAttribute("appUser", new AppUser());
            model.addAttribute("registrationError", "User with this email already exists.");

            return "register";
        }

        userService.save(appUser);

        return "registration-success";
    }

}
