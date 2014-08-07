package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

public interface URLService {
    class URLNotFoundException extends RuntimeException {}

    void addURL(URL url);
    URL getURLByShortLink(String shortLink) throws URLNotFoundException;
    URL getURLById(Long id) throws URLNotFoundException;

    String getRootPath(HttpServletRequest request);
}
