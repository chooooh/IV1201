package se.kth.recruitmentapp.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kth.recruitmentapp.config.SecurityConfig;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.domain.Role;
import se.kth.recruitmentapp.presentation.forms.LoginForm;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles all HTTP routes to all login related operations
 */
@Controller
public class LoginController {
    static final String LOGIN_PAGE_URL      = "login-user";
    static final String LOGIN_SUCCESS_URL    = "login-success";

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyController.class);

    /**
     * A get request for the Login page.
     * @param model Model objects used by the page
     * @return the login page url.
     */
    @GetMapping("/" + LOGIN_PAGE_URL)
    public String showLoginPageView(Model model){
        LOGGER.info("GET /" + LOGIN_PAGE_URL);
        model.addAttribute("loginForm", new LoginForm());
        return LOGIN_PAGE_URL;
    }

    /**
     * A get request issued on successful login, see Security configuration {@link SecurityConfig}.
     * This method either redirects client to the apply page or recruitment page.
     * The redirect is dependent on the client Role {@link Role}.
     *
     * @param request
     * @return The apply page url or recruitment page url, if neither then error page.
     */
    @GetMapping("/"+ LOGIN_SUCCESS_URL)
    public String showWelcomePageView(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person) auth.getPrincipal();
        LOGGER.info("GET /" + LOGIN_SUCCESS_URL);
        LOGGER.info("Person: " + person.getId() + " " + person.getUsername() + " ROLE: " + person.getRole().getName());

        if(person.getRole().getName().equals("applicant")){
            LOGGER.info("redirect:/" + ApplyController.APPLY_PAGE_URL);
            return "redirect:/" + ApplyController.APPLY_PAGE_URL;
        } else if (person.getRole().getName().equals("recruiter")){
            LOGGER.info("redirect:/" + RecruitmentController.RECRUITMENT_URL);
            return "redirect:/" + RecruitmentController.RECRUITMENT_URL;
        }
        LOGGER.error("ERROR successful login but no role set");
        return "error";
    }
}
