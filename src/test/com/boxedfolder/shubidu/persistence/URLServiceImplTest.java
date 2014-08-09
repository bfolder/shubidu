package com.boxedfolder.shubidu.persistence;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.encoding.Base62Encoder;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;
import com.boxedfolder.shubidu.persistence.service.URLService;
import com.boxedfolder.shubidu.persistence.service.URLServiceImpl;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class URLServiceImplTest {
    private URLService urlService;
    private URLRepository mockRepository;
    private URL url;

    @Before
    public void setup() {
        mockRepository = mock(URLRepository.class);
        Base62Encoder mockEncoder = mock(Base62Encoder.class);
        urlService = new URLServiceImpl(mockRepository, mockEncoder);

        url = new URL();
        url.setDate(new Date());
        url.setId(1L);
        url.setLink("http://www.google.de");
        url.setShortLink("b");
    }

    @Test
    public void testAddURL() {
        given(mockRepository.save(url)).willReturn(url);
        URL returnUrl = urlService.addURL(url);
        assertThat(returnUrl, equalTo(url));
    }

    @Test
    public void testGetURLByShortLink() {
        given(mockRepository.findUrlByShortLink(url.getShortLink())).willReturn(url);
        URL returnUrl = urlService.getURLByShortLink(url.getShortLink());
        assertThat(returnUrl, equalTo(url));
    }

    @Test
    public void testGetUrlByLink() {
        given(mockRepository.findUrlByLink(url.getLink())).willReturn(url);
        URL returnUrl = urlService.getUrlByLink(url.getLink());
        assertThat(returnUrl, equalTo(url));
    }

    @Test
    public void testGetRootPath() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        given(request.getContextPath()).willReturn("");
        given(request.getScheme()).willReturn("http");
        given(request.getServerName()).willReturn("google.de");
        given(request.getServerPort()).willReturn(8080);
        String path = urlService.getRootPath(request);
        assertThat(path, equalTo("http://google.de:8080/"));
    }
}
