package se.kth.recruitmentapp.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.kth.recruitmentapp.domain.exceptions.IllegalCompetenceException;
import se.kth.recruitmentapp.domain.exceptions.PersonAlreadyExistsException;

/**
 * Handles all errors.
 */
@Controller
@ControllerAdvice
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private enum LogType {
        ERROR,
        DEBUG
    }

    private static final String GENERIC_ERROR_URL = "error";
    // these error values should map to the corresponding values in Message.properties
    private static final String PERSON_EXISTS_ERROR = "person-exists";
    private static final String COMPETENCE_EXISTS_ERROR = "competence-exists";
    private static final String GENERIC_ERROR = "generic";

    public static final String ERROR_TYPE_KEY = "errorType";

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);


    /**
     * A get request for the error page.
     * @return error page.
     */
    @GetMapping("/error")
    public String returnError(){
        return "error";
    }

    /**
     * Handles business rule exceptions regarding the competence domain.
     * @param exception The exception to handle
     * @param model Model containing error information
     * @return Generic error page.
     */
    @ExceptionHandler(IllegalCompetenceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(IllegalCompetenceException exception, Model model) {
        logException(exception, LogType.DEBUG);
        model.addAttribute(ERROR_TYPE_KEY, COMPETENCE_EXISTS_ERROR);
        return GENERIC_ERROR_URL;
    }

    /**
     * Handles exceptions where the person already exists.
     * @param exception The exception to handle
     * @param model Model containing error information
     * @return Sign up page.
     */
    @ExceptionHandler(PersonAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleException(PersonAlreadyExistsException exception, RedirectAttributes attr, Model model) {
        logException(exception, LogType.DEBUG);
        //model.addAllAttributes(attr.getFlashAttributes());
        model.addAttribute(ERROR_TYPE_KEY, PERSON_EXISTS_ERROR);
        //return RegistrationController.SIGNUP_PAGE_URL; this does not work "Error creating bean with name "Neither BindingResult nor plain target object for bean name 'createAccountForm'..
        return GENERIC_ERROR_URL;
    }

    /**
     * Generic exception handler, that handles all exceptions to type Exception
     * @param exception The exception to handle, if not handled above.
     * @return The generic error page.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception, Model model) {
        logException(exception, LogType.ERROR);
        model.addAttribute(ERROR_TYPE_KEY, GENERIC_ERROR);
        return GENERIC_ERROR_URL;
    }

    private void logException(Exception exception, LogType logType) {
        String exceptionClassName = exception.getClass().getName();
        String exceptionMessage = exception.getMessage();
        String msg = "Error handler received exception {}: {}";
        switch (logType) {
            case DEBUG:
                LOGGER.debug(msg, exceptionClassName, exceptionMessage, exception);
                break;
            case ERROR:
                LOGGER.error(msg, exceptionClassName, exceptionMessage, exception);
                break;
        }
    }
}
