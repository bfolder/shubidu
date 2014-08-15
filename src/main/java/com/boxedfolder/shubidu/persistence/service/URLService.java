package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;

public interface URLService {
    class URLNotFoundException extends RuntimeException {
    }

    URL addURL(URL url);
    URL getURLByHash(String hash) throws URLNotFoundException;
    URL getURLByLink(String link) throws URLNotFoundException;
}
