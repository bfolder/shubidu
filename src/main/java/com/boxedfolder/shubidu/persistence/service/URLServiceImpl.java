package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class URLServiceImpl implements URLService {
    @Override
    public URL addURL(String url) throws URLNotProvidedException{
        if (url == null || url.equals("")) {
            throw new URLNotProvidedException();
        }

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public URL getURLById(Long id) throws URLNotFoundException{
        URL url = null;

        if (url == null) {
            throw new URLNotFoundException();
        }

        return url;
    }
}
