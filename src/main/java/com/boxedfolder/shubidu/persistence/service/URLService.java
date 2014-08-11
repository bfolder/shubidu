package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.encoding.Encoder;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;

import javax.servlet.http.HttpServletRequest;

public interface URLService {
    class URLNotFoundException extends RuntimeException {}

    URL addURL(URL url, HttpServletRequest request);
    URL getURLByHash(String hash, HttpServletRequest request) throws URLNotFoundException;
    URL getURLByLink(String link, HttpServletRequest request) throws URLNotFoundException;
}
