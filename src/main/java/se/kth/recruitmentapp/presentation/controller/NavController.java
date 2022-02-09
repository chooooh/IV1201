package se.kth.recruitmentapp.presentation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is a controller which maps HTTP requests made from the navigation bar.
 * Each requests returns the corresponding html.
 */
@Controller
@Scope("session")
public class NavController {
    static final String ROOT_PAGE_URL       = "/";
    static final String WELCOME_PAGE_URL    = "welcome";
    static final String LOGIN_PAGE_URL      = "login-user";
    static final String SIGNUP_PAGE_URL     = "sign-up";

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
        return SIGNUP_PAGE_URL;
    }

}
