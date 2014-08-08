package com.boxedfolder.shubidu.persistence;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.helper.encoding.Base62Encoder;
import com.boxedfolder.shubidu.persistence.domain.helper.validation.URLNotNullValidator;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;
import com.boxedfolder.shubidu.persistence.service.URLService;
import com.boxedfolder.shubidu.persistence.service.URLServiceImpl;
import org.junit.Assert;
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
    private URLNotNullValidator mockValidator;

    @Before
    public void setup() {
        mockRepository = mock(URLRepository.class);
        mockEncoder = mock(Base62Encoder.class);
        mockValidator = mock(URLNotNullValidator.class);
        urlService = new URLServiceImpl(mockRepository, mockEncoder, mockValidator);
    }

    @Test
    public void testAddingURL() {
        URL url = new URL();
        url.setId(1L);
        url.setDate(new Date());
        url.setLink("http://www.google.de");

        given(mockRepository.save(url)).willReturn(url);
        given(mockValidator.validate(null)).willThrow(new URLService.URLNotFoundException());
        assertThat(urlService.addURL(url), equalTo(url));
    }
}
