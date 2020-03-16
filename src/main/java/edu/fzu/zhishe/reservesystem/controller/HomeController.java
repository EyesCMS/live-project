package edu.fzu.zhishe.reservesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xjliang
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/task";
    }

    @RequestMapping("/index")
    public String index() {
        return "redirect:/task";
    }
}
