package io.done3app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FirstTestController {

    @RequestMapping(value = "/ftest", method = RequestMethod.GET)
    public String firstTestCheck(ModelMap model) {
        model.addAttribute("message", "congrats, this is the beginning!");
        return "firstTest";
    }

    @RequestMapping(value = "/chat-test", method = RequestMethod.GET)
    public String chatTest() {
        return "chat-test";
    }
}
