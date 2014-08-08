package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.encoding.Encoder;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;

import javax.servlet.http.HttpServletRequest;

public interface URLService {
    class URLNotFoundException extends RuntimeException {}

    URL addURL(URL url);
    URL getURLByShortLink(String shortLink) throws URLNotFoundException;
    URL getUrlByLink(String link) throws URLNotFoundException;
    String getRootPath(HttpServletRequest request);
}
