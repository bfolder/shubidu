package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;

import java.util.Date;

@Service
public class URLServiceImpl implements URLService {
    @Autowired
    private URLRepository urlRepository;

    @Override
    public void addURL(URL url) throws LinkNotProvidedException {
        if (url == null || url.getLink().equals("")) {
            throw new LinkNotProvidedException();
        }
        url.setDate(new Date());
        urlRepository.save(url);
    }

    @Transactional(readOnly = true)
    @Override
    public URL getURLById(Long id) throws URLNotFoundException {
        URL url = urlRepository.findOne(id);
        if (url == null) {
            throw new URLNotFoundException();
        }

        return url;
    }
}
