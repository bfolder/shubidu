package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class URLController {
    @Autowired
    private URLService urlService;

    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model model) {
        model.addAttribute("url", new URL());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postLink(@ModelAttribute("url") URL url, RedirectAttributes model) {
        urlService.addURL(url);
        model.addFlashAttribute("url", url);
        return "redirect:/result";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String showResult() {
        return "result";
    }

    @ExceptionHandler(URLService.URLNotFoundException.class)
    public String handleURLNotFoundException() {
        return "error/404";
    }

    @ExceptionHandler(URLService.LinkNotProvidedException.class)
    public String handleLinkNotProvidedException(RedirectAttributes model) {
        model.addFlashAttribute("errorMessage", "No link provided");
        return "redirect:/";
    }
}
