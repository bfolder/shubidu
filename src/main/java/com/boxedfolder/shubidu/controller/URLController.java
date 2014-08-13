package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class URLController {
    private URLService urlService;

    public URLController(){}

    @Autowired
    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public URL createURL(@RequestBody URL url, HttpServletRequest request) {
        return urlService.addURL(url, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/get/{hash}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public URL getURL(@PathVariable("hash") String hash, HttpServletRequest request) {
        return urlService.getURLByHash(hash, request);
    }
}