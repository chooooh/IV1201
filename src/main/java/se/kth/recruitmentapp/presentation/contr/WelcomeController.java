package se.kth.recruitmentapp.presentation.contr;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Scope("session")
public class WelcomeController {
    static final String ROOT_PAGE_URL       = "/";
    static final String WELCOME_PAGE_URL    = "welcome";
    static final String LOGIN_PAGE_URL      = "login-user";

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


}
