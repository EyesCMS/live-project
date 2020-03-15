package edu.fzu.zhishe.reservesystem.controller;

import edu.fzu.zhishe.reservesystem.generator.AdminUser;
import edu.fzu.zhishe.reservesystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "username") String username,
        @RequestParam(value = "password") String password) {
        AdminUser adminUser = adminService.findByNameAndPassword(username, password);
        if (adminUser == null) {
            return "login";
        } else {
            return "admin";
        }
    }
}
