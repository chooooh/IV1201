package se.kth.recruitmentapp.presentation.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.presentation.forms.CompetenceForm;
import se.kth.recruitmentapp.presentation.forms.CreateAccountForm;
import se.kth.recruitmentapp.presentation.forms.LoginForm;

import javax.validation.Valid;

@Controller
public class ApplicationController {
    static final String ADD_COMPETENCE_POST_URL = "add-competence";

    @PostMapping("/" + ADD_COMPETENCE_POST_URL)
    public String processCompetence(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println("competence bindings has errors");
        }

        System.out.println("Selected comp:" + competenceForm.getSelectedCompetence());
        System.out.println("Years: " + competenceForm.getYearsOfExperience());

        competenceForm.addCompetence(competenceForm.getSelectedCompetence(), String.valueOf(competenceForm.getYearsOfExperience()));

        model.addAttribute("competenceForm", new CompetenceForm(competenceForm.getCompetences(), competenceForm.getCompetenceList()));
        return NavController.APPLY_PAGE_URL;
    }

}
