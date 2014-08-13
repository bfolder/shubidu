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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;

public class URLServiceImplTest {
    private URLService urlService;
    private URLRepository mockRepository;
    private URL url;
    private HttpServletRequest request;

    @Before
    public void setup() {
        mockRepository = mock(URLRepository.class);
        request = mock(HttpServletRequest.class);
        urlService = new URLServiceImpl(mockRepository, mock(Base62Encoder.class));

        url = new URL();
        url.setDate(new Date());
        url.setId(1L);
        url.setLink("http://www.google.com");
        url.setHash("b");
    }

    @Test
    public void testAddURL() {
        given(mockRepository.save(url)).willReturn(url);
        URL returnUrl = urlService.addURL(url, request);
        assertThat(returnUrl, equalTo(url));
    }

    @Test
    public void testGetURLByShortLink() {
        given(mockRepository.findUrlByHash(url.getHash())).willReturn(url);
        URL returnUrl = urlService.getURLByHash(url.getHash(), request);
        assertThat(returnUrl, equalTo(url));
    }

    @Test
    public void testGetUrlByLink() {
        given(mockRepository.findUrlByLink(url.getLink())).willReturn(url);
        URL returnUrl = urlService.getURLByLink(url.getLink(), request);
        assertThat(returnUrl, equalTo(url));
    }

    @Test
    public void testGetShortLink() {
        given(mockRepository.findUrlByHash("b")).willReturn(url);
        given(request.getContextPath()).willReturn("");
        given(request.getScheme()).willReturn("http");
        given(request.getServerName()).willReturn("www.google.com");
        given(request.getServerPort()).willReturn(8080);

        url = urlService.getURLByHash(url.getHash(), request);
        assertThat(url.getShortLink(), equalTo("http://www.google.com:8080/b"));

        given(mockRepository.findUrlByLink("http://www.google.com")).willReturn(url);
        url = urlService.getURLByLink(url.getLink(), request);
        assertThat(url.getShortLink(), equalTo("http://www.google.com:8080/b"));
    }
}
