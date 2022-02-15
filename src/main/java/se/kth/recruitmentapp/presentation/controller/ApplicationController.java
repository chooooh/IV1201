package se.kth.recruitmentapp.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import se.kth.recruitmentapp.service.CompetenceService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
public class ApplicationController {
    static final String ADD_COMPETENCE_POST_URL = "add-competence";
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private CompetenceService competenceService;

    /**
     * Handles request of adding competence & years of expertise
     * to the Competence form view.
     *
     *
     * @param competenceForm ,the contents of the competence form.
     * @param bindingResult  ,validation of the competence form.
     * @param model          ,model objects used by the page.
     * @return
     */
    @PostMapping("/" + ADD_COMPETENCE_POST_URL)
    public String updateCompetenceForm(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {

        LOGGER.info("SELECTED competence: " + competenceForm.getSelectedCompetence() +"\n"
                  + "SELECTED years: "+ competenceForm.getYearsOfExperience());
        if (bindingResult.hasErrors()) {
            System.out.println("competence bindings has errors");
        }

        competenceForm.addCompetence(competenceForm.getSelectedCompetence(), String.valueOf(competenceForm.getYearsOfExperience()));
        List<Competence> competenceList = competenceService.getAllCompetences();
        LOGGER.info("COMPETENCE LIST" + new CompetenceForm(competenceForm.getCompetences(), competenceList));
        model.addAttribute("competenceForm", competenceForm);
        return NavController.APPLY_PAGE_URL;
    }

}
