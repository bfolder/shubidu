package com.boxedfolder.shubidu.controller;

import com.boxedfolder.shubidu.config.WebConfig;
import com.boxedfolder.shubidu.config.jpa.JPAEmbeddedDatabaseConfig;
import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.repository.URLRepository;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class URLControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".html");

        URLService mockService = mock(URLService.class);
        URLController controller = new URLController(mockService);
        mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testIndexPage() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }

    @Test
    public void testResultPage() throws Exception {
        mockMvc.perform(get("/result")).andExpect(view().name("result"));
    }

    @Test
    public void testPostURL() throws Exception {
        URL url = new URL();
        url.setLink("http://www.google.de");
        mockMvc.perform(post("/").param("link", "http://www.google.de"))
                .andExpect(view().name("redirect:/result"))
                .andExpect(flash().attributeExists("url"))
                .andExpect(flash().attribute("url", equalTo(url)));
    }
}