package se.kth.recruitmentapp.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.Competence;
import se.kth.recruitmentapp.presentation.forms.CompetenceForm;
import se.kth.recruitmentapp.service.CompetenceService;
import javax.validation.Valid;
import java.util.List;
/**
 * Handles all HTTP routes to all application related operations
 */
@Controller
public class ApplicationController {
    static final String ADD_COMPETENCE_POST_URL = "add-competence";
    static final String APPLY_PAGE_URL          = "apply";
    static final String ADD_COMP_FORM_OBJ_NAME  = "competenceForm";

    @Autowired
    private CompetenceService competenceService;

    /**
     * A get request for the application page.
     * @param model Model objects used by the page.
     * @return the applicant form url.
     */
    @GetMapping("/" + APPLY_PAGE_URL)
    public String showApplyPageView(Model model){
        List<Competence> competenceList = competenceService.getAllCompetences();
        CompetenceForm competenceForm = new CompetenceForm();
        competenceForm.setCompetenceList(competenceList);

        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

    /**
     * Handles request of adding competence & years of expertise
     * to the Competence form view.
     *
     * @param competenceForm The contents of the competence form.
     * @param bindingResult  Validation of the competence form.
     * @param model          Model objects used by the page.
     * @return Apply page URL in case a competence is successfully added.
     */
    @PostMapping("/" + ADD_COMPETENCE_POST_URL)
    public String updateCompetenceForm(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(ADD_COMP_FORM_OBJ_NAME, new CompetenceForm());
            return ADD_COMPETENCE_POST_URL;
        }

        competenceForm.addCompetence(competenceForm.getSelectedCompetence(), competenceForm.getYearsOfExperience());

        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

}
