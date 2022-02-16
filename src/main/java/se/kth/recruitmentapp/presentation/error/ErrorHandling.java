package se.kth.recruitmentapp.presentation.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import se.kth.recruitmentapp.domain.IllegalCompetenceException;
import se.kth.recruitmentapp.domain.PersonAlreadyExistsException;
import se.kth.recruitmentapp.presentation.controller.RegistrationController;
import se.kth.recruitmentapp.presentation.forms.CreateAccountForm;

/**
 * Handles all errors.
 */
@Controller
@ControllerAdvice
public class ErrorHandling {
    private static final String GENERIC_ERROR_URL = "error";
    private static final String PERSON_EXISTS_ERROR = "personExists";

    public static final String ERROR_TYPE_KEY = "errorType";

    @ExceptionHandler(IllegalCompetenceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(IllegalCompetenceException exception, Model model) {
        System.out.println("exception message: " + exception.getMessage());
        if (exception.getMessage().contains("already")) {
            model.addAttribute(ERROR_TYPE_KEY, "you have already submitted the competence!");
        }
        return GENERIC_ERROR_URL;
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(PersonAlreadyExistsException exception, Model model) {
        model.addAttribute(RegistrationController.CREATE_ACCT_FORM_OBJ_NAME, new CreateAccountForm());
        model.addAttribute(ERROR_TYPE_KEY, PERSON_EXISTS_ERROR);
        return RegistrationController.SIGNUP_PAGE_URL;
    }

    /**
     * Generic exception handler, that handles all exceptions of type Exception
     * @param exception The exception
     * @return The generic error page.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception, Model model) {
        model.addAttribute(ERROR_TYPE_KEY, exception.getMessage());
        return GENERIC_ERROR_URL;
    }
}
