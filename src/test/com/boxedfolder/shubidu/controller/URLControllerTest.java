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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
    public void testShowHome() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }

    @Test
    public void testShowResult() throws Exception {
        mockMvc.perform(get("/result")).andExpect(view().name("result"));
    }

    @Test
    public void testPostLink() throws Exception {
        URL url = new URL();
        url.setLink("http://www.google.de");
        mockMvc.perform(post("/").param("link", url.getLink()))
                .andExpect(view().name("redirect:/result"))
                .andExpect(flash().attributeExists("url"))
                .andExpect(flash().attribute("url", equalTo(url)));
    }

    @Test
    public void testHandleURLNotFound() throws Exception {
        given(mockService.getURLByShortLink("xyz")).willThrow(new URLService.URLNotFoundException());
        mockMvc.perform(get("/xyz")).andExpect(status().is(404));
    }

    @Test
    public void testHandleLinkNotProvided() throws Exception {
        URL url = new URL();
        url.setLink("");
        doThrow(new URLService.LinkNotProvidedException()).when(mockService).addURL(url);
        mockMvc.perform(post("/").param("link", ""))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/"));
    }
}