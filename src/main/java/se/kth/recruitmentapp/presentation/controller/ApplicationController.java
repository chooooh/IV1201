package se.kth.recruitmentapp.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.Competence;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.presentation.forms.CompetenceForm;
import se.kth.recruitmentapp.presentation.forms.CreateAccountForm;
import se.kth.recruitmentapp.presentation.forms.LoginForm;
import se.kth.recruitmentapp.service.CompetenceService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
public class ApplicationController {
    static final String ADD_COMPETENCE_POST_URL = "add-competence";
    static final String APPLY_PAGE_URL      = "apply";

    @Autowired
    private CompetenceService competenceService;

    private List<Competence> competenceList;

    @PostConstruct
    public void init(){
        competenceList = competenceService.getAllCompetences();
    }

    /**
     * A get request for the application page.
     * @param model Model objects used by the page.
     * @return the applicant form url.
     */
    @GetMapping("/" + APPLY_PAGE_URL)
    public String showApplyPageView(Model model){
       // List<Competence> competenceList = competenceService.getAllCompetences();
        CompetenceForm competenceForm = new CompetenceForm();
        competenceForm.setCompetenceList(competenceList);

        //model.addAttribute("competences", competenceList);
        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

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

        if (bindingResult.hasErrors()) {
            System.out.println("competence bindings has errors");
        }
        String compName = competenceForm.getSelectedCompetence();
        double noYears = competenceForm.getYearsOfExperience();
        HashMap<String, String> competences = competenceForm.getCompetences();
        System.out.println("competences " + competences);

        CompetenceForm  compForm = new CompetenceForm();
        compForm.setCompetenceList(competenceList);
        compForm.setCompetences(competenceForm.getCompetences());
        compForm.addCompetence(compName, String.valueOf(noYears));
        model.addAttribute("competenceForm", compForm);
        return APPLY_PAGE_URL;
    }

}
