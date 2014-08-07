package com.boxedfolder.shubidu.persistence.service;

import com.boxedfolder.shubidu.persistence.domain.helper.Base62;
import com.boxedfolder.shubidu.persistence.domain.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class URLServiceImpl implements URLService {
    @Autowired
    private URLRepository urlRepository;

    public URLServiceImpl(){}

    public URLServiceImpl(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public URL addURL(URL url) {
        int calc = Base62.toBase10(url.getLink());
        url.setShortLink(String.valueOf(calc));
        url.setDate(new Date());
        try {
            url = getURLByShortLink(url.getShortLink());
        } catch (Exception e) {
            urlRepository.save(url);
        }
        return url;
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
}
