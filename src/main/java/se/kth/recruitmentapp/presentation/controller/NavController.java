package se.kth.recruitmentapp.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kth.recruitmentapp.domain.Competence;
import se.kth.recruitmentapp.presentation.forms.ApplicationForm;
import se.kth.recruitmentapp.presentation.forms.CreateAccountForm;
import se.kth.recruitmentapp.presentation.forms.LoginForm;
import se.kth.recruitmentapp.service.CompetenceService;

import java.util.List;

/**
 * This is a controller which maps all HTTP requests made from the navigation bar.
 * Each requests returns the corresponding html.
 */
@Controller
@Scope("session")
public class NavController {
    static final String WELCOME_PAGE_URL    = "welcome";
    static final String LOGIN_PAGE_URL      = "login-user";
    static final String SIGNUP_PAGE_URL     = "sign-up";
    static final String APPLY_PAGE_URL      = "apply";

    @Autowired
    private CompetenceService competenceService;

    /**
     * A get request for the welcome page.
     * @return The welcome page url.
     */
    @GetMapping("/"+ WELCOME_PAGE_URL)
    public String showWelcomePageView(){ return WELCOME_PAGE_URL;}

    /**
     * A get request for the Login page.
     * @param model Model objects used by the page
     * @return the login page url.
     */
    @GetMapping("/" + LOGIN_PAGE_URL)
    public String showLoginPageView(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return LOGIN_PAGE_URL;
    }

    /**
     * A get request for the Create Account Page.
     * @param model Model objects used by the page.
     * @return the sign-up page url.
     */
    @GetMapping("/" + SIGNUP_PAGE_URL)
    public String showSignupPageView(Model model){
        model.addAttribute("createAccountForm", new CreateAccountForm());
        return SIGNUP_PAGE_URL;
    }

    /**
     * A get request for the application page.
     * @param model Model objects used by the page.
     * @return the applicant form url.
     */
    @GetMapping("/" + APPLY_PAGE_URL)
    public String showApplyPageView(Model model){
        List<Competence> competenceList = competenceService.getAllCompetences();
        model.addAttribute("competences", competenceList);
        model.addAttribute("applicationForm", new ApplicationForm());
        return APPLY_PAGE_URL;
    }

}
