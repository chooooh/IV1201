package se.kth.recruitmentapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoConfiguration
class CompetenceServiceTest {
    @Autowired
    private MockMvc mockMvc; // does not start server

    @Test
    void getAllCompetences() {

    }

    @Test
    void saveSelectedCompetences() {
    }

    @Test
    void getCompetenceByName() {
    }
}