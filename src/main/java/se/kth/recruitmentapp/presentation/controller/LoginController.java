package se.kth.recruitmentapp.presentation.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.presentation.forms.LoginForm;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    static final String LOGIN_PAGE_URL      = "login-user";
    static final String LOGIN_SUCCESS_URL    = "login-success";
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
     * A get request for the welcome page.
     * @return The welcome page url.
     */
    @GetMapping("/"+ LOGIN_SUCCESS_URL)
    public String showWelcomePageView(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person) auth.getPrincipal();

        if(person.getRole().getName().equals("applicant")){
            return "redirect:/" + ApplicationController.APPLY_PAGE_URL;
        } else if (person.getRole().getName().equals("recruiter")){
            return "redirect:/" + RecruitmentController.RECRUITMENT_URL;
        }
        return "error";
    }
}
