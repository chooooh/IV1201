package se.kth.recruitmentapp.presentation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.presentation.forms.CreateAccountForm;
import se.kth.recruitmentapp.presentation.forms.LoginForm;
import se.kth.recruitmentapp.domain.Role;
import se.kth.recruitmentapp.service.PersonService;
import javax.validation.Valid;


/**
 * Handles all HTTP routes to all registration related operations.
 */
@Controller
public class RegistrationController {
    private static final String REGISTER_APPLICANT_URL = "sign-up";
    private static final String CREATE_ACCT_FORM_OBJ_NAME = "createAcctForm";

    @Autowired
    private PersonService personService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Post request that handles user registration.
     *
     * @param createAccountForm The content of the account form.
     * @param bindingResult     The result of validation for the form.
     * @param model             Objects required for the account creation.
     * @return Login page URL in case account creation succeeds.
     */
    @PostMapping("/" + REGISTER_APPLICANT_URL)
    public String processRegistration(@Valid CreateAccountForm createAccountForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(CREATE_ACCT_FORM_OBJ_NAME, new CreateAccountForm());
            return REGISTER_APPLICANT_URL;
        }

        Person person = personService.findAccountByUsername(createAccountForm.getUsername());
        Role role = personService.getRole(PersonService.UserRole.APPLICANT);

        if(person == null){
            System.out.println("No such person found!");
            personService.save(createAccountForm.toPerson(passwordEncoder, role));
        } else {
            System.out.println("Person found");
        }

        model.addAttribute("loginForm", new LoginForm());

        return NavController.LOGIN_PAGE_URL;
    }


}




