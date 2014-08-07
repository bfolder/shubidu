package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class URLController {
    @Autowired
    private URLService urlService;

    public URLController(){}

    public URLController(URLService urlService) {
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
        return "redirect:" + url.getLink();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postLink(@Valid URL url, Errors errors) {
        if (errors.hasErrors()) {
            return "index";
        }
        urlService.addURL(url);
        return "redirect:/result/" + url.getId();
    }

    @RequestMapping(value = "/result/{id}", method = RequestMethod.GET)
    public String showResult(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        URL url = urlService.getURLById(id);
        model.addAttribute("url", url);
        model.addAttribute("root", urlService.getRootPath(request));
        return "result";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(URLService.URLNotFoundException.class)
    public String handleURLNotFoundException() {
        return "errors/404";
    }
}