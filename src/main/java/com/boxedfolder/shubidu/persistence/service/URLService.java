package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;

public interface URLService {
    public class LinkNotProvidedException extends RuntimeException {}
    public class URLNotFoundException extends RuntimeException {}

    void addURL(URL url) throws LinkNotProvidedException;
    URL getURLById(Long id) throws URLNotFoundException;
}
