package se.kth.recruitmentapp.presentation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(RecruitmentController.class)
class RecruitmentControllerTest {
    @Test
    void exampleTest(){
        assertEquals(1,1);
    }
}
