package com.boxedfolder.shubidu.controller;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ShubiduControllerTest {
    @Test
    public void testIndexPage() throws Exception{
        ShubiduController controller = new ShubiduController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }
}
