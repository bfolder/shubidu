package com.boxedfolder.shubidu.persistence;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.helper.encoding.Base62Encoder;
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
    private Base62Encoder mockEncoder;

    @Before
    public void setup() {
        mockRepository = mock(URLRepository.class);
        mockEncoder = mock(Base62Encoder.class);
        urlService = new URLServiceImpl(mockRepository, mockEncoder);
    }

    @Test
    public void testAddURL() {
        URL url = new URL();
        url.setId(1L);
        url.setDate(new Date());
        url.setLink("http://www.google.de");

        given(mockRepository.save(url)).willReturn(url);
        assertThat(urlService.addURL(url), equalTo(url));
    }
}
