package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.encoding.Encoder;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
    public URL addURL(URL url, HttpServletRequest request) {
        url.setDate(new Date());
        try {
            url = getURLByLink(url.getLink(), request);
        } catch (URLNotFoundException e) {
            // Generate id (If anyone knows a way to easily generate the id without saving the object, let me know)
            url = urlRepository.save(url);
            url.setHash(encoder.encode(url.getId())); // Encode id
            url = urlRepository.save(url); // Save url with encoded id
            url.setShortLink(getRootPath(request) + url.getHash()); // Add transient shortlink
        }

        return url;
    }

    @Transactional(readOnly = true)
    @Override
    public URL getURLByHash(String hash, HttpServletRequest request) throws URLNotFoundException {
        URL url = urlRepository.findUrlByHash(hash);
        return refreshURL(url, request);
    }

    @Transactional(readOnly = true)
    @Override
    public URL getURLByLink(String link, HttpServletRequest request) throws URLNotFoundException {
        URL url = urlRepository.findUrlByLink(link);
        return refreshURL(url, request);
    }

    private URL refreshURL(URL url, HttpServletRequest request) {
        if (url == null) {
            throw new URLService.URLNotFoundException();
        }
        url.setShortLink(getRootPath(request) + url.getHash());
        return url;
    }

    private String getRootPath(HttpServletRequest request) {
        return request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort() + "/"
                + request.getContextPath();
    }
}
