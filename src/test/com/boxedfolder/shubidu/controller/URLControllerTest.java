package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.persistence.service.URLService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    }

    @Test
    public void testLinkValidation() throws Exception {
    }
}