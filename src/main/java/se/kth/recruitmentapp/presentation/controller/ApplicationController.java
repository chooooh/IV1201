package se.kth.recruitmentapp.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private CompetenceService competenceService;

    /**
     * A get request for the application page.
     * @param model Model objects used by the page.
     * @return the applicant form url.
     */
    @GetMapping("/" + APPLY_PAGE_URL)
    public String showApplyPageView(Model model){
        LOGGER.info("GET /"+ APPLY_PAGE_URL);
        List<Competence> competenceList = competenceService.getAllCompetences();
        LOGGER.debug("Competence list: " + competenceList);
        CompetenceForm competenceForm = new CompetenceForm();
        competenceForm.setCompetenceList(competenceList);
        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

    /**
     * Handles request of adding competence & years of expertise
     * to the Competence form view.
     *
     * @param competenceForm ,the contents of the competence form.
     * @param bindingResult  ,validation of the competence form.
     * @param model          ,model objects used by the page.
     * @return
     */
    @PostMapping("/" + ADD_COMPETENCE_POST_URL)
    public String updateCompetenceForm(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {
        LOGGER.info("POST /" + ADD_COMPETENCE_POST_URL);
        LOGGER.trace("Competence form data: " + competenceForm);
        if (bindingResult.hasErrors()) {
            System.out.println("competence bindings has errors");
        }

        competenceForm.addCompetence(competenceForm.getSelectedCompetence(), competenceForm.getYearsOfExperience());

        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

}
