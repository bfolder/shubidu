package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class URLControllerTest {
    private MockMvc mockMvc;
    private URLService mockService;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".html");

        mockService = mock(URLService.class);
        URLController controller = new URLController(mockService);
        mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testPostLink() throws Exception {
        URL url = new URL();
        url.setDate(new Date());
        url.setId(1L);
        url.setLink("http://www.google.de");
        url.setShortLink("b");

        given(mockService.getUrlByLink(url.getLink())).willReturn(null);
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content("{'link':'http://www.google.de'}")).andExpect(status().isOk());
    }

    @Test
    public void testLinkValidation() throws Exception {
    }
}