package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.helper.Encoder;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class URLServiceImpl implements URLService {
    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private Encoder encoder;

    public URLServiceImpl() {
    }

    public URLServiceImpl(URLRepository urlRepository, Encoder encoder) {
        this.urlRepository = urlRepository;
        this.encoder = encoder;
    }

    @Override
    public URL addURL(URL url) {
        url.setShortLink(encoder.encode(url.getLink()));
        url.setDate(new Date());
        return validateAndSave(url);
    }

    @Transactional(readOnly = true)
    @Override
    public URL getURLByShortLink(String shortLink) throws URLNotFoundException {
        URL url = urlRepository.findUrlByShortLink(shortLink);
        if (url == null) {
            throw new URLNotFoundException();
        }
        return url;
    }

    @Override
    public String getRootPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
    }

    private URL appendHttp(URL url) {
        // Append http inside this service method to keep the model class thin.
        String link = url.getLink();
        if (!link.startsWith("http")) {
            url.setLink("http://" + link);
        }
        return url;
    }

    private URL validateAndSave(URL url) {
        appendHttp(url);
        try {
            url = getURLByShortLink(url.getShortLink());
        } catch (Exception e) {
            urlRepository.save(url);
        }
        return url;
    }
}
