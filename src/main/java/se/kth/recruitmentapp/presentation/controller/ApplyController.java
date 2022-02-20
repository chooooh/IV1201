package se.kth.recruitmentapp.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import se.kth.recruitmentapp.domain.*;
import se.kth.recruitmentapp.presentation.forms.CompetenceForm;
import se.kth.recruitmentapp.service.CompetenceService;
import se.kth.recruitmentapp.service.ProfileService;

import javax.validation.Valid;
import java.util.List;
/**
 * Handles all HTTP routes to all application related operations
 */
@Controller
@Scope("session")
public class ApplyController {
    static final String ADD_COMPETENCE_POST_URL = "add-competence";
    static final String APPLY_PAGE_URL          = "apply";
    private List<Profile> profiles;


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
        profiles = profileService.getProfilesByPerson(person);

        LOGGER.debug("Competence list: " + competenceList);
        CompetenceForm competenceForm = new CompetenceForm();
        competenceForm.setCompetenceList(competenceList);

        model.addAttribute("profiles", profiles);
        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

    /**
     * Handles request of adding competence & years of expertise
     * to the Competence form view.
     *
     * @param competenceForm        ,the contents of the competence form.
     * @param bindingResult         ,validation of the competence form.
     * @param model                 ,model objects used by the page.
     * @return
     */
    @PostMapping(value="/" + ADD_COMPETENCE_POST_URL, params = "action=add-competence")
    public String updateCompetenceForm(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {
        LOGGER.info("POST /" + ADD_COMPETENCE_POST_URL + "/add-competence");
        LOGGER.trace("Competence form data: " + competenceForm);

        if (bindingResult.hasErrors()) {
            System.out.println("competence bindings has errors");
        }
        //Fetch Competence and person
        Competence comp = competenceService.getCompetenceByName(competenceForm.getSelectedCompetence());

        Profile profile = new Profile();
        profile.setCompetence(comp);
        profile.setYoe(competenceForm.getYearsOfExperience());
        profiles.add(profile);

        model.addAttribute("profiles", profiles);
        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

    /**
     * Handles request of removing a selected competence(s) & years of expertise
     *
     * @param competenceForm ,the contents of the competence form.
     * @param bindingResult  ,validation of the competence form.
     * @param model          ,model objects used by the page.
     * @return
     */
    @PostMapping(value = "/" + ADD_COMPETENCE_POST_URL, params = "action=remove")
    public String removeCompetence(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {
        LOGGER.info("POST/" + ADD_COMPETENCE_POST_URL+"/action=remove");
        LOGGER.info("Profiles to be removed: " + competenceForm.getToBeRemovedProfiles());
        profileService.removeProfiles(competenceForm.getToBeRemovedProfiles());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person =  (Person) auth.getPrincipal();
        profiles = profileService.getProfilesByPerson(person);
        model.addAttribute("profiles", profiles);
        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

    /**
     * Handles request of saving selected competences & years of expertise
     *
     * @param competenceForm ,the contents of the competence form.
     * @param bindingResult  ,validation of the competence form.
     * @param model          ,model objects used by the page.
     * @return
     */
    @PostMapping(value = "/" + ADD_COMPETENCE_POST_URL, params = "action=save")
    public String saveCompetenceForm(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {
        LOGGER.info("POST/" + ADD_COMPETENCE_POST_URL+"/action=save");
        LOGGER.info("competence form" + competenceForm);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person =  (Person) auth.getPrincipal();
        for(Profile profile: profiles){
            profile.setPerson(person);
            profileService.createProfile(profile);
        }

        return "login-user";
    }

}
