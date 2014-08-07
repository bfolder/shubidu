package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

public interface URLService {
    class LinkNotProvidedException extends RuntimeException {}

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class URLNotFoundException extends RuntimeException {}

    void addURL(URL url) throws LinkNotProvidedException;
    URL getURLByShortLink(String shortLink) throws URLNotFoundException;

    String getRootPath(HttpServletRequest request);
}
