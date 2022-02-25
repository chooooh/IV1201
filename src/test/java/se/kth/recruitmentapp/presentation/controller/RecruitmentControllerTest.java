package se.kth.recruitmentapp.presentation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecruitmentController.class)
class RecruitmentControllerTest {
    @Autowired
    private MockMvc mockMvc; // does not start server

    @Test
    public void testRecruitmentPage() throws Exception {
        mockMvc.perform(get("/" + RecruitmentController.RECRUITMENT_URL)) // allowed to get static fields from classes in main?
                .andExpect(status().isOk())
                .andExpect(view().name("recruitment"))
                .andExpect(content().string(
                        containsString("applicants")
                ));
    }
}