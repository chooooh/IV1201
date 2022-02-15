package se.kth.recruitmentapp.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kth.recruitmentapp.domain.IllegalCompetenceException;
import se.kth.recruitmentapp.service.PersonService;

import static se.kth.recruitmentapp.presentation.controller.NavController.LOGIN_PAGE_URL;

@Controller
public class TestController {
    @Autowired
    private PersonService personService;

    @GetMapping("/" + "test")
    public String showTestPage(Model model) throws IllegalCompetenceException {
        personService.saveCompetenceTest();
        return LOGIN_PAGE_URL;
    }
}
