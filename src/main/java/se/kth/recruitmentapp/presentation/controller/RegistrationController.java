package se.kth.recruitmentapp.presentation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private PersonDTO person;

    @GetMapping
    public String registerForm() {
        return "sign-up";
    }

    @PostMapping("/" + REGISTER_APPLICANT_URL)
    public String processRegistration(@Valid CreateAccountForm createAccountForm, BindingResult bindingResult, Model model) {
        //System.out.println(form.getPassword());
        //personRepository.save(form.toPerson(passwordEncoder));
        if(bindingResult.hasErrors()){
            System.out.println("Binding result has errors!");
            model.addAttribute("createAccountForm", new CreateAccountForm());
            return "sign-up";
        }

        person = personService.findAccountByUsername(createAccountForm.getUsername());
        if(person == null){
            System.out.println("No such person found!");
        } else {
            System.out.println("Person found");
        }






        return "redirect:/login-user";
    }


}




