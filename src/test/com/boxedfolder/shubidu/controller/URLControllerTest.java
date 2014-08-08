package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.encoding.Base62Encoder;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;
import com.boxedfolder.shubidu.persistence.service.URLService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class URLControllerTest {
    private MockMvc mockMvc;
    private URLService mockService;
    private URL url;
    private String content;

    @Before
    public void setup() throws Exception{
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".html");

        mockService = mock(URLService.class);
        URLController controller = new URLController(mockService);
        mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver).build();
        url = new URL();
        url.setDate(new Date());
        url.setId(1L);
        url.setLink("http://www.google.de");
        url.setShortLink("b");

        ObjectMapper mapper = new ObjectMapper();
        content = mapper.writeValueAsString(url);
    }

    @Test
    public void testPostLink() throws Exception {
        given(mockService.addURL(url)).willReturn(url);
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(content));
    }

    @Test
    public void testGetURL() throws Exception {
        given(mockService.getURLByShortLink(url.getShortLink())).willReturn(url);
        mockMvc.perform(get("/get/b")).andExpect(status().isOk())
                .andExpect(content().string(content));
    }
}