package se.kth.recruitmentapp.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.Competence;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.domain.Profile;
import se.kth.recruitmentapp.presentation.forms.CompetenceForm;
import se.kth.recruitmentapp.service.CompetenceService;
import se.kth.recruitmentapp.service.ProfileService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles all HTTP routes to all application related operations
 */
@Controller
public class ApplyController {
    static final String ADD_COMPETENCE_POST_URL = "add-competence";
    static final String APPLY_PAGE_URL          = "apply";

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyController.class);

    @Autowired
    private CompetenceService competenceService;
    @Autowired
    private ProfileService profileService;

    /**
     * A get request for the application page.
     * @param model Model objects used by the page.
     * @return the applicant form url.
     */
    @GetMapping("/" + APPLY_PAGE_URL)
    public String showApplyPageView(Model model){
        LOGGER.info("GET /"+ APPLY_PAGE_URL);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person =  (Person) auth.getPrincipal();

        List<Competence> competenceList = competenceService.getAllCompetences();
        List<Profile> profiles = profileService.getProfilesByPerson(person);

        LOGGER.debug("Competence list: " + competenceList);
        CompetenceForm competenceForm = new CompetenceForm();
        competenceForm.setCompetenceList(competenceList);
        competenceForm.setProfiles(profiles);
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
    @PostMapping(value="/" + ADD_COMPETENCE_POST_URL, params = "action=add-competence")
    public String updateCompetenceForm(@Valid @ModelAttribute CompetenceForm competenceForm, BindingResult bindingResult, Model model) {
        LOGGER.info("POST /" + ADD_COMPETENCE_POST_URL + "/add-competence");
        LOGGER.trace("Competence form data: " + competenceForm);

        if (bindingResult.hasErrors()) {
            System.out.println("competence bindings has errors");
        }
        //Fetch Competence and person
        Competence comp = competenceService.getCompetenceByName(competenceForm.getSelectedCompetence());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person =  (Person) auth.getPrincipal();
        //Create profile
        Profile profile = new Profile();
        profile.setPerson(person);
        profile.setCompetence(comp);
        profile.setYoe(competenceForm.getYearsOfExperience());

        //Ask service to save the profile
        profileService.createProfile(profile);
        //Get all profiles associated with this person
        List<Profile> profiles = profileService.getProfilesByPerson(person);
        //Provide found profiles to the form
        competenceForm.setProfiles(profiles);

        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

    /**
     * Handles request of removing selected competences & years of expertise
     *
     * @param competenceForm ,the contents of the competence form.
     * @param bindingResult  ,validation of the competence form.
     * @param model          ,model objects used by the page.
     * @return
     */
    @PostMapping(value = "/" + ADD_COMPETENCE_POST_URL, params = "action=remove")
    public String saveCompetenceForm(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {
        LOGGER.info("POST/" + ADD_COMPETENCE_POST_URL+"/action=remove");
        LOGGER.info("competence form" + competenceForm);

        return APPLY_PAGE_URL;
    }

}
