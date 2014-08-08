package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class WebControllerTest {
    private MockMvc mockMvc;
    private URLService mockService;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".html");

        mockService = mock(URLService.class);
        WebController controller = new WebController(mockService);
        mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testShowHome() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }

    @Test
    public void testHandleURLNotFound() throws Exception {
        given(mockService.getURLByShortLink("xyz")).willThrow(new URLService.URLNotFoundException());
        mockMvc.perform(get("/xyz")).andExpect(status().is(404));

    }

    @Test
    public void testRedirect() throws Exception {
        URL url = new URL();
        url.setDate(new Date());
        url.setId(1L);
        url.setLink("http://www.google.de");
        url.setShortLink("b");

        given(mockService.getURLByShortLink("b")).willReturn(url);
        mockMvc.perform(get("/b")).andExpect(view().name("redirect:http://www.google.de"));
    }
}
