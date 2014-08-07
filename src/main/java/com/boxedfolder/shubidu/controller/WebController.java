package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/{shortLink}", method = RequestMethod.GET)
    public String redirectToLink(@PathVariable("shortLink") String shortLink, Model model) {
        URL url = urlService.getURLByShortLink(shortLink);
        return "forward:" + url.getLink();
    }
}
