package se.kth.recruitmentapp.presentation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.recruitmentapp.domain.PersonDTO;

import javax.validation.Valid;

@Controller
@Scope("session")
public class NavController {
    static final String ROOT_PAGE_URL       = "/";
    static final String WELCOME_PAGE_URL    = "welcome";
    static final String LOGIN_PAGE_URL      = "login-user";
    static final String SIGNUP_PAGE_URL     = "sign-up";
    static final String ON_LOGIN_REQUEST_SOME_URL = "login";  //What should the request be when a user clicks login?

    @Autowired
    private PersonDTO currentUser;

    /**
     * If no page is specified, redirect to the welcome page.
     * @return A response that redirects the browser to the welcome page.
     */
    @GetMapping(ROOT_PAGE_URL)
    public String showRootPage(){
        return "redirect:" + WELCOME_PAGE_URL;
    }

    /**
     * A get request for the welcome page.
     * @return The welcome page url.
     */
    @GetMapping("/"+ WELCOME_PAGE_URL)
    public String showWelcomePageView(){
        return WELCOME_PAGE_URL;
    }

    /**
     * A get request for the Login page.
     * @return the login page url.
     */
    @GetMapping("/" + LOGIN_PAGE_URL)
    public String showLoginPageView(LoginForm loginForm){ return LOGIN_PAGE_URL; }

    /**
     * A get request for the Create Account Page.
     * @param createAccountForm content of the Create account form
     * @return
     */
    @GetMapping("/" + SIGNUP_PAGE_URL)
    public String showSignupPageView(CreateAccountForm createAccountForm, Model model){
        //model.addAttribute("createAccountForm", createAccountForm);
        return SIGNUP_PAGE_URL;
    }

    /**
     *
     * @param loginForm         content of the login form
     * @param bindingResult     validation result for the create account form.
     * @param model             Model objects used by the login form
     * @return UNKNOWN!!!!, what page should be returned on failed login vs successful login?
     */
    @PostMapping("/" + ON_LOGIN_REQUEST_SOME_URL)
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("loginForm", new LoginForm());
            return LOGIN_PAGE_URL;
        }
        //currentUser = service.findUser()
        // SHOULD NOT RETURN THE SAME URL
        return LOGIN_PAGE_URL;
    }
}
