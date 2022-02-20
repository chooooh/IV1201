package se.kth.recruitmentapp.presentation.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;


@Controller
@ControllerAdvice
public class ExceptionHandlers implements ErrorController {
    public static final String ERROR_PAGE_URL = "error";
    public static final String ERROR_TYPE_KEY = "errorType";
    public static final String GENERIC_ERROR = "generic";
    static final String ERROR_PATH = "failure";

    /*
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(CompetenceAlreadyExistsException exception, RedirectAttributes redirAttrs){
        //redirAttrs.addFlashAttribute("error", exception.getMessage());

        return "apply";
    }*/


//    /**
//     * The generic exception handler. It is used when no other handler is suitable.
//     * @param exception
//     * @param model
//     * @return
//     */
//    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleException(Exception exception, Model model) {
//        model.addAttribute(ERROR_TYPE_KEY, GENERIC_ERROR);
//        return ERROR_PAGE_URL;
//    }
//
//    @GetMapping("/" + ERROR_PATH)
//    public String handleHttpError(HttpServletRequest request, HttpServletResponse response, Model model) {
//        String statusCode = extractHttpStatusCode(request);
//        model.addAttribute(ERROR_TYPE_KEY, statusCode);
//        response.setStatus(Integer.parseInt(statusCode));
//        return ERROR_PAGE_URL;
//    }
//
//    private String extractHttpStatusCode(HttpServletRequest request) {
//        return request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
//    }

}
