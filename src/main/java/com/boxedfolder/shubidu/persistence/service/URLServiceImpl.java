package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.encoding.Encoder;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class URLServiceImpl implements URLService {
    private URLRepository urlRepository;
    private Encoder<String, Long> encoder;

    public URLServiceImpl() {
    }

    @Autowired
    public URLServiceImpl(URLRepository urlRepository,
                          Encoder<String, Long> encoder) {
        this.urlRepository = urlRepository;
        this.encoder = encoder;
    }

    @Override
    public URL addURL(URL url) {
        url.setDate(new Date());
        try {
            url = getURLByLink(url.getLink());
        } catch (URLNotFoundException e) {
            // Generate id (If anyone knows a way to easily generate the id without saving the object, let me know)
            url = urlRepository.save(url);
            url.setHash(encoder.encode(url.getId())); // Encode id
            url = urlRepository.save(url); // Save url with encoded id
        }

        return url;
    }

    @Transactional(readOnly = true)
    @Override
    public URL getURLByHash(String hash) throws URLNotFoundException {
        URL url = urlRepository.findUrlByHash(hash);
        if (url == null) {
            throw new URLService.URLNotFoundException();
        }
        return url;
    }

    @Transactional(readOnly = true)
    @Override
    public URL getURLByLink(String link) throws URLNotFoundException {
        URL url = urlRepository.findUrlByLink(link);
        if (url == null) {
            throw new URLService.URLNotFoundException();
        }
        return url;
    }
}
