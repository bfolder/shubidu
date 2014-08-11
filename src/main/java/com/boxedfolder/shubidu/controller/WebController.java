package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
    @Autowired
    URLService urlService;

    public WebController(){}

    public WebController(URLService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model model) {
        model.addAttribute("url", new URL());
        return "index";
    }

    @RequestMapping(value = "/{hash}", method = RequestMethod.GET)
    public String redirectToLink(@PathVariable("hash") String hash, HttpServletRequest request) {
        URL url = urlService.getURLByHash(hash, request);
        return "redirect:" + url.getLink();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(URLService.URLNotFoundException.class)
    public String handleURLNotFoundException() {
        return "errors/404";
    }
}
