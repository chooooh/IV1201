package se.kth.recruitmentapp.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.models.Competence;
import se.kth.recruitmentapp.domain.models.Person;
import se.kth.recruitmentapp.domain.models.Profile;
import se.kth.recruitmentapp.presentation.forms.CompetenceForm;
import se.kth.recruitmentapp.service.CompetenceService;
import se.kth.recruitmentapp.service.ProfileService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Handles all HTTP routes to all application related operations
 */
@Controller
@Scope("session")
public class ApplyController {
    static final String ADD_COMPETENCE_POST_URL = "add-competence";
    static final String APPLY_PAGE_URL          = "apply";
    private List<Profile> profiles;
    private Person currentPerson;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyController.class);

    @Autowired
    private CompetenceService competenceService;
    @Autowired
    private ProfileService profileService;

    /**
     * A get request for the application page.
     * This method is locale-sensitive, as it requires the selected language stored in locale to fetch
     * the appropriate competences by language.
     *
     * @param model Model objects used by the page.
     * @return the applicant form url.
     */
    @GetMapping("/" + APPLY_PAGE_URL)
    public String showApplyPageView(Model model){
        LOGGER.info("GET /"+ APPLY_PAGE_URL);

        //Get current person
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        currentPerson =  (Person) auth.getPrincipal();

        //Check selected language
        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();
        //search for competences by language
        List<Competence> competenceList = competenceService.getAllCompetencesByLanguage(language);

        profiles = profileService.getProfilesByPerson(currentPerson);

        LOGGER.debug("Competence list: " + competenceList);
        CompetenceForm competenceForm = new CompetenceForm();
        competenceForm.setCompetenceList(competenceList);

        model.addAttribute("profiles", profiles);
        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

    /**
     * Handles request of adding a new profile to the local list of added profiles.
     * The profile added, provided in the competenceForm, consists of a competence name and years of experience.
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
        boolean addCompetence = true;

        if (bindingResult.hasErrors()) {
           LOGGER.error("Binding errors in apply/action=add-competence");
           model.addAttribute("competenceForm", competenceForm);
           model.addAttribute("profiles", profiles);
           return APPLY_PAGE_URL;
        }
        //Fetch Competence and person
        Competence comp = competenceService.getCompetenceByName(competenceForm.getSelectedCompetence());

        //Check for duplicate profiles
        for(Profile profile: profiles){
            if(profile.getCompetence().getName().equals(comp.getName())){
                addCompetence = false; //Don't create new profile and add this competence
                LOGGER.info(currentPerson.getUsername() + " attempted to add "+ comp.getName() +" twice");
                model.addAttribute("errorDuplicateProfiles", "Error, you can not add the same competence twice");
            }
        }
        //Add new profile, if allowed
        if(addCompetence){
            Profile profile = new Profile();
            profile.setCompetence(comp);
            profile.setYoe(new BigDecimal(competenceForm.getYearsOfExperience()));
            profiles.add(profile);
        }

        model.addAttribute("profiles", profiles);
        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

    /**
     * Handles request of removing a profile from the local list of profiles.
     * The profile to be removed may be a "true" profile with all of its fields set, loaded from the db
     * or the profile only has competence name and years of experience set.
     *
     * @param competenceForm ,the contents of the competence form.
     * @param bindingResult  ,validation of the competence form.
     * @param model          ,model objects used by the page.
     * @return
     */
    @PostMapping(value = "/" + ADD_COMPETENCE_POST_URL, params = "action=remove")
    public String removeCompetence(@Valid CompetenceForm competenceForm, BindingResult bindingResult, Model model) {
        LOGGER.info("POST/" + ADD_COMPETENCE_POST_URL+"/action=remove");
        LOGGER.info("Profiles to be removed: " + competenceForm.getToBeRemovedProfileNames());

        if(bindingResult.hasErrors()){
            LOGGER.error("Binding errors in apply/action=remove");
        }

        removeProfile(competenceForm.getToBeRemovedProfileNames());

        model.addAttribute("profiles", profiles);
        model.addAttribute("competenceForm", competenceForm);
        return APPLY_PAGE_URL;
    }

    /**
     * This method is private and is used for removing profiles from both db and the local profiles list.
     *
     * @param toBeRemovedProfileNames , the names of the profiles to be removed.
     */
    private void removeProfile(List<String> toBeRemovedProfileNames) {
        List<Profile> toBeRemoved = new ArrayList<>();
        for(int i = 0; i < profiles.size(); i++){
            for(int j = 0; j < toBeRemovedProfileNames.size(); j++){
                if(profiles.get(i).getCompetence().getName().equals(toBeRemovedProfileNames.get(j))){
                    toBeRemoved.add(profiles.get(i));
                }
            }
        }

        for(Profile profile: toBeRemoved){
            if(profile.getId() != 0){
                profileService.removeProfile(profile);
            }
            profiles.remove(profile);
        }
    }

    /**
     * Handles request of saving the selected profiles in the local profiles list.
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

        if(bindingResult.hasErrors()){
            LOGGER.error("Binding result has error in apply/action=save");
            model.addAttribute("competenceForm", competenceForm);
            model.addAttribute("profiles", profiles);
            return APPLY_PAGE_URL;
        }

        for(Profile profile: profiles){
            profile.setPerson(currentPerson);
            profileService.createProfile(profile);
        }

        return "redirect:/" + APPLY_PAGE_URL;
    }

}
