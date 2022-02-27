package se.kth.recruitmentapp.presentation.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(RecruitmentController.class)
@SpringBootTest
@AutoConfigureMockMvc
class RecruitmentControllerTest {
    // private RecruitmentController controller;
    @Autowired
    private MockMvc mockMvc; // does not start server

    @Test
    @WithMockUser(authorities = {"recruiter"})
    public void testRecruitmentPageRecruiterAuthorized() throws Exception {
        mockMvc.perform(get("/" + RecruitmentController.RECRUITMENT_URL)) // allowed to get static fields from classes in main?
                .andExpect(status().isOk())
                .andExpect(view().name("applicants"))
                .andExpect(content().string(
                        containsString("Recruitment Application")
                ));
    }

    @Test
    @WithMockUser(authorities = {"applicant"})
    public void testRecruitmentPageApplicantNonAuthorized() throws Exception {
        mockMvc.perform(get("/" + RecruitmentController.RECRUITMENT_URL))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(
                        containsString("Login")
                ));
    }

    @Test
    public void testRecruitmentPageNonAuthorized() throws Exception {
        mockMvc.perform(get("/" + RecruitmentController.RECRUITMENT_URL))
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(
                        containsString("Login")
                ));
    }
}