package com.boxedfolder.shubidu.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class URLControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        URLController controller = new URLController();
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testIndexPage() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }

    @Test
    public void testResultPage() throws Exception {
        mockMvc.perform(get("/result")).andExpect(view().name("result"));
    }
}
