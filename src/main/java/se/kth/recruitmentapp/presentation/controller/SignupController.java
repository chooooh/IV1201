package se.kth.recruitmentapp.presentation.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.domain.PersonAlreadyExistsException;
import se.kth.recruitmentapp.domain.RoleNotFoundException;
import se.kth.recruitmentapp.presentation.forms.CreateAccountForm;
import se.kth.recruitmentapp.presentation.forms.LoginForm;
import se.kth.recruitmentapp.domain.Role;
import se.kth.recruitmentapp.service.PersonService;
import javax.validation.Valid;


/**
 * Handles all HTTP routes to all registration related operations.
 */
@Controller
public class SignupController {
    static final String REGISTER_APPLICANT_URL = "sign-up";
    public static final String CREATE_ACCT_FORM_OBJ_NAME = "createAccountForm";
    public static final String SIGNUP_PAGE_URL     = "sign-up";
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyController.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * A get request for the Create Account Page.
     * @param model Model objects used by the page.
     * @return the sign-up page url.
     */
    @GetMapping("/" + SIGNUP_PAGE_URL)
    public String showSignupPageView(Model model){
        LOGGER.info("GET /" + SIGNUP_PAGE_URL);
        model.addAttribute("createAccountForm", new CreateAccountForm());
        model.addAttribute(CREATE_ACCT_FORM_OBJ_NAME, new CreateAccountForm());
        return SIGNUP_PAGE_URL;
    }
    /**
     * Post request that handles user registration.
     *
     * @param createAccountForm The content of the account form.
     * @param bindingResult     The result of validation for the form.
     * @param model             Objects required for the account creation.
     * @return Login page URL in case account creation succeeds.
     */
    @PostMapping("/" + REGISTER_APPLICANT_URL)
    public String processRegistration(@Valid CreateAccountForm createAccountForm, BindingResult bindingResult, Model model) throws PersonAlreadyExistsException, RoleNotFoundException {
        LOGGER.info("POST /" + REGISTER_APPLICANT_URL);
            LOGGER.info("create account form: " + createAccountForm);
        if (bindingResult.hasErrors()) {
            LOGGER.error("Binding result has errors in createAccountForm");
            model.addAttribute(CREATE_ACCT_FORM_OBJ_NAME, new CreateAccountForm());
            return REGISTER_APPLICANT_URL;
        }

        Role role = personService.getRole(PersonService.UserRole.RECRUITER);
        personService.createPerson(createAccountForm.toPerson(passwordEncoder, role));

        model.addAttribute("loginForm", new LoginForm());

        return LoginController.LOGIN_PAGE_URL;
    }


}




