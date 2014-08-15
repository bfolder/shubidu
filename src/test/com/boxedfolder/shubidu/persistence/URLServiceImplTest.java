package com.boxedfolder.shubidu.persistence;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.encoding.Base62Encoder;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;
import com.boxedfolder.shubidu.persistence.service.URLService;
import com.boxedfolder.shubidu.persistence.service.URLServiceImpl;
import org.junit.Before;
import org.junit.Test;

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
        URL returnUrl = urlService.addURL(url);
        assertThat(returnUrl, equalTo(url));
    }

    @Test
    public void testGetURLByShortLink() {
        given(mockRepository.findUrlByHash(url.getHash())).willReturn(url);
        URL returnUrl = urlService.getURLByHash(url.getHash());
        assertThat(returnUrl, equalTo(url));
    }

    @Test
    public void testGetUrlByLink() {
        given(mockRepository.findUrlByLink(url.getLink())).willReturn(url);
        URL returnUrl = urlService.getURLByLink(url.getLink());
        assertThat(returnUrl, equalTo(url));
    }

    @Test
    public void testGetHash() {
        given(mockRepository.findUrlByHash("b")).willReturn(url);
        url = urlService.getURLByHash(url.getHash());
        assertThat(url.getHash(), equalTo("b"));

        given(mockRepository.findUrlByLink("http://www.google.com")).willReturn(url);
        url = urlService.getURLByLink(url.getLink());
        assertThat(url.getHash(), equalTo("b"));
    }
}
