package com.boxedfolder.shubidu.persistence.service;

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
    public void addURL(URL url) {
        url.setDate(new Date());
        urlRepository.save(url);
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

    @Transactional(readOnly = true)
    @Override
    public URL getURLById(Long id) throws URLNotFoundException {
        URL url = urlRepository.findOne(id);
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
