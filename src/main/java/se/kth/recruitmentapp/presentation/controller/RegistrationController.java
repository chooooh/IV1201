package se.kth.recruitmentapp.presentation.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.service.PersonService;

import javax.validation.Valid;

/**
 * Handles all HTTP routes to all registration related operations
 */
@Controller
public class RegistrationController {
    private final String REGISTER_APPLICANT_URL = "sign-up";

    @Autowired
    private PersonService personService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm() {
        return REGISTER_APPLICANT_URL;
    }

    /**
     * Post route that handles user registration
     * @param createAccountForm The model representing the form to create an account
     * @param bindingResult used for form validation
     * @param model Model objects used by the page
     * @return the registration page URL
     */
    @PostMapping("/" + REGISTER_APPLICANT_URL)
    public String processRegistration(@Valid CreateAccountForm createAccountForm, BindingResult bindingResult, Model model) {
        System.out.println(createAccountForm.getPassword());
        Person person = personService.findAccountByUsername(createAccountForm.getUsername());
        if(person == null){
            System.out.println("No such person found!");
            personService.save(createAccountForm.toPerson(passwordEncoder));
        } else {
            System.out.println("Person found");
        }
        //if(bindingResult.hasErrors()){
        //System.out.println("Binding result has errors!");
            //model.addAttribute("createAccountForm", createAccountForm);

        //}

        return NavController.LOGIN_PAGE_URL;
    }


}




