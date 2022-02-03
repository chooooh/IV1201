/*package se.kth.recruitmentapp.presentation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.kth.recruitmentapp.domain.RegistrationForm;
import se.kth.recruitmentapp.repository.PersonRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public String registerForm() {
        return "sign-up";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        //System.out.println(form.getPassword());
        //personRepository.save(form.toPerson(passwordEncoder));

        return "redirect:/login-user";
    }


}




 */