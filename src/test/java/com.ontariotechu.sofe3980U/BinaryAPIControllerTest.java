package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add")
                .param("operand1", "111")
                .param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }

    @Test
    public void addJson() throws Exception {
        this.mvc.perform(get("/add_json")
                .param("operand1", "111")
                .param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.operand1").value("111"))
            .andExpect(jsonPath("$.operator").value("add"))
            .andExpect(jsonPath("$.operand2").value("1010"))
            .andExpect(jsonPath("$.result").value("10001"));
    }

    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/mul")
                .param("operand1", "101")
                .param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }

    @Test
    public void andOperation() throws Exception {
        this.mvc.perform(get("/and")
                .param("operand1", "1010")
                .param("operand2", "1100"))
            .andExpect(status().isOk())
            .andExpect(content().string("1000"));
    }

    @Test
    public void orOperation() throws Exception {
        this.mvc.perform(get("/or")
                .param("operand1", "1010")
                .param("operand2", "1100"))
            .andExpect(status().isOk())
            .andExpect(content().string("1110"));
    }
}
