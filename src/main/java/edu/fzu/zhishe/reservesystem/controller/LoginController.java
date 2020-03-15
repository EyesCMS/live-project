package edu.fzu.zhishe.reservesystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
