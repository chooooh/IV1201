package se.kth.recruitmentapp.presentation.contr;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Scope("session")
public class ExampleController {
    static final String ROOT_PAGE_URL = "/";


    @GetMapping(ROOT_PAGE_URL)
    public String showRootPage(){
        return "example.html";
    }

}
