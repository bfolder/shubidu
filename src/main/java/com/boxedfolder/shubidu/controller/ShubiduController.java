package com.boxedfolder.shubidu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShubiduController {
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }
}
