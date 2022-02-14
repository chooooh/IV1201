package se.kth.recruitmentapp.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.Competence;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.presentation.forms.CompetenceForm;
import se.kth.recruitmentapp.presentation.forms.CreateAccountForm;
import se.kth.recruitmentapp.presentation.forms.LoginForm;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ApplicationController {
    static final String ADD_COMPETENCE_POST_URL = "add-competence";
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @PostMapping("/" + ADD_COMPETENCE_POST_URL)
    public String processCompetence(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {


        LOGGER.info("Competence form" + "\n"
                + "SELECTED competence: " + competenceForm.getSelectedCompetence() +"\n"
                + "SELECTED years: "+ competenceForm.getYearsOfExperience());
        if (bindingResult.hasErrors()) {
            System.out.println("competence bindings has errors");
        }

        competenceForm.addCompetence(competenceForm.getSelectedCompetence(), String.valueOf(competenceForm.getYearsOfExperience()));
        List<Competence> competenceList =
        model.addAttribute("competences", competenseList);
        model.addAttribute("competenceForm", new CompetenceForm(competenceForm.getCompetences(), competenceForm.getCompetenceList()));
        return NavController.APPLY_PAGE_URL;
    }

}
