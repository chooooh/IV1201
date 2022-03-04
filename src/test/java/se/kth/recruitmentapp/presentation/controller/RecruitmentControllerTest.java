package se.kth.recruitmentapp.presentation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecruitmentController.class)
//@SpringBootTest
@AutoConfigureMockMvc
class RecruitmentControllerTest {
    // private RecruitmentController controller;
    private static final String REDIRECT = "redirect:/";
    private static final String LOCALHOST = "http://localhost/";
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
    @WithMockUser(authorities = {"recruiter"})
    public void testRecruitmentPageRedirectNegativePageNumber() throws Exception {
        mockMvc.perform(get("/" + RecruitmentController.RECRUITMENT_URL + "?page=-5")) // allowed to get static fields from classes in main?
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(containsString(REDIRECT + RecruitmentController.RECRUITMENT_URL)));
    }

    @Test
    @WithMockUser(authorities = {"recruiter"})
    public void testRecruitmentPageRedirectAboveMaxPageNumber() throws Exception {
        mockMvc.perform(get("/" + RecruitmentController.RECRUITMENT_URL + "?page=99999999")) // allowed to get static fields from classes in main?
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(containsString(REDIRECT + RecruitmentController.RECRUITMENT_URL)));
    }

    @Test
    @WithMockUser(authorities = {"applicant"})
    public void testRecruitmentRedirectPageApplicantNonAuthorized() throws Exception {
        mockMvc.perform(get("/" + RecruitmentController.RECRUITMENT_URL))
                .andExpect(status().is4xxClientError())
                .andExpect(forwardedUrl("/" + LoginController.LOGIN_PAGE_URL));
    }

    // spring security..
    @Test
    public void testRecruitmentRedirectPageNonAuthorized() throws Exception {
        mockMvc.perform(get("/" + RecruitmentController.RECRUITMENT_URL))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(LOCALHOST + LoginController.LOGIN_PAGE_URL));
    }


}
