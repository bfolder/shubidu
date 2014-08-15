package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class URLController {
    private URLService urlService;

    public URLController() {
    }

    @Autowired
    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public URL createURL(@RequestBody URL url) {
        return urlService.addURL(url);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/get/{hash}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public URL getURL(@PathVariable("hash") String hash) {
        return urlService.getURLByHash(hash);
    }
}