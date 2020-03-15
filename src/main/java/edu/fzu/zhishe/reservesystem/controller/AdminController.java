package edu.fzu.zhishe.reservesystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}
