package se.kth.recruitmentapp.presentation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.domain.PersonDTO;
import se.kth.recruitmentapp.domain.RegistrationForm;
import se.kth.recruitmentapp.repository.PersonRepository;
import se.kth.recruitmentapp.service.PersonService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final String REGISTER_APPLICANT_URL = "create-account";

    @Autowired
    private PersonService personService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //private PersonDTO person;

    @GetMapping
    public String registerForm() {
        return "sign-up";
    }

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

        return "sign-up";
    }


}




