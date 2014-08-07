package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;

public interface URLService {
    public class URLNotFoundException extends RuntimeException {}
    public class URLNotProvidedException extends RuntimeException {}

    URL addURL(String url) throws URLNotProvidedException;
    URL getURLById(Long id) throws URLNotFoundException;
}
