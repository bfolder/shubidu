package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.helper.encoding.Encoder;
import com.boxedfolder.shubidu.persistence.domain.helper.validation.Validator;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class URLServiceImpl implements URLService {
    @Autowired
    private Validator<URL> notNullValidator;

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private Encoder<String, Long> encoder;

    public URLServiceImpl() {
    }

    public URLServiceImpl(URLRepository urlRepository,
                          Encoder<String, Long> encoder,
                          Validator<URL> notNullValidator) {
        this.urlRepository = urlRepository;
        this.encoder = encoder;
        this.notNullValidator = notNullValidator;
    }

    @Override
    public URL addURL(URL url) {
        url.setDate(new Date());
        try {
            url = getUrlByLink(url.getLink());
        } catch (URLNotFoundException e) {
            url = urlRepository.save(url);
            url.setShortLink(encoder.encode(url.getId()));
        }
        return urlRepository.save(url);
    }

    @Transactional(readOnly = true)
    @Override
    public URL getURLByShortLink(String shortLink) throws URLNotFoundException {
        URL url = urlRepository.findUrlByShortLink(shortLink);
        notNullValidator.validate(url);
        return url;
    }

    @Transactional(readOnly = true)
    @Override
    public URL getUrlByLink(String link) throws URLNotFoundException {
        URL url = urlRepository.findUrlByLink(link);
        notNullValidator.validate(url);
        return url;
    }

    @Override
    public String getRootPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
    }
}
